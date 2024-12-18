<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard - EnrolPro</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>1

    <!-- Include the menu -->
    <jsp:include page="admin_menu.jsp" />

    <div class="container my-5">
        <h1 class="text-center mb-4">Admin Dashboard</h1>

        <!-- Display message if present -->
        <c:if test="${not empty param.message}">
            <div class="alert alert-success alert-dismissible fade show" role="alert">
                ${param.message}
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
        </c:if>

        <!-- Add Program Section -->
        <section class="mb-5">
            <h2>Add Program</h2>
            <form action="course" method="post">
                <input type="hidden" name="action" value="addProgram" />
                <div class="form-row">
                    <div class="form-group col-md-4">
                        <label for="programName">Program Name</label>
                        <input type="text" class="form-control" id="programName" name="programName" required>
                    </div>
                </div>
                <button type="submit" class="btn btn-primary">Add Program</button>
            </form>
        </section>

        <!-- Add Course Section -->
        <section class="mb-5">
            <h2>Add Course</h2>
            <form id="add-course-form" action="course" method="post">
                <input type="hidden" name="action" value="add" />
                <div class="form-row">
                    <div class="form-group col-md-4">
                        <label for="courseName">Course Name</label>
                        <input type="text" class="form-control" id="courseName" name="courseName" required>
                    </div>
                    <div class="form-group col-md-4">
                        <label for="programID">Program ID</label>
                        <input type="number" class="form-control" id="programID" name="programID" required>
                    </div>
                    <div class="form-group col-md-4">
                        <label for="credits">Credits</label>
                        <input type="number" class="form-control" id="credits" name="credits">
                    </div>
                    <div class="form-group col-md-4">
                        <label for="capacity">Capacity</label>
                        <input type="number" class="form-control" id="capacity" name="capacity" required>
                    </div>
                    <div class="form-group col-md-4">
                        <label for="instructor">Instructor</label>
                        <input type="text" class="form-control" id="instructor" name="instructor">
                    </div>
                    <div class="form-group col-md-4">
                        <label for="schedule">Schedule</label>
                        <input type="text" class="form-control" id="schedule" name="schedule">
                    </div>
                    <div class="form-group col-md-4">
                        <label for="location">Location</label>
                        <input type="text" class="form-control" id="location" name="location">
                    </div>
                    <div class="form-group col-md-12">
                        <label for="description">Description</label>
                        <textarea class="form-control" id="description" name="description"></textarea>
                    </div>
                </div>
                <button type="submit" class="btn btn-success">Add Course</button>
            </form>
        </section>

        <!-- Manage Courses Section -->
        <section>
            <h2>Manage Courses</h2>
            <div class="table-responsive">
                <table class="table table-bordered">
                    <thead class="thead-dark">
                        <tr>
                            <th>Course ID</th>
                            <th>Course Name</th>
                            <th>Capacity</th>
                            <th>Schedule</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:if test="${empty courses}">
                            <tr>
                                <td colspan="5" class="text-center">No courses available.</td>
                            </tr>
                        </c:if>
                        <c:forEach var="course" items="${courses}">
                            <tr>
                                <td>${course.courseID}</td>
                                <td>${course.name}</td>
                                <td>${course.capacity}</td>
                                <td>${course.schedule}</td>
                                <td>
                                    <!-- Update button triggers modal -->
                                    <button class="btn btn-warning btn-sm update-course" 
                                            data-id="${course.courseID}" 
                                            data-name="${course.name}" 
                                            data-capacity="${course.capacity}">
                                        Update
                                    </button>
                                    
                                    <!-- Delete form -->
                                    <form action="course" method="post" style="display:inline;">
                                        <input type="hidden" name="action" value="delete"/>
                                        <input type="hidden" name="courseID" value="${course.courseID}">
                                        <button type="submit" class="btn btn-danger btn-sm">Delete</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </section>
    </div>

    <!-- Update Course Modal -->
    <div class="modal fade" id="updateCourseModal" tabindex="-1" aria-labelledby="updateCourseModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <form id="update-course-form" action="course" method="post">
                    <div class="modal-header">
                        <h5 class="modal-title" id="updateCourseModalLabel">Update Course</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <input type="hidden" name="action" value="update"/>
                        <input type="hidden" id="updateCourseID" name="courseID">

                        <div class="form-group">
                            <label for="updateCourseName">Course Name</label>
                            <input type="text" class="form-control" id="updateCourseName" name="courseName" required>
                        </div>
                        <div class="form-group">
                            <label for="updateCapacity">Capacity</label>
                            <input type="number" class="form-control" id="updateCapacity" name="capacity" required>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        <button type="submit" class="btn btn-primary">Update Course</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    
    <!-- Include the footer -->
    <jsp:include page="footer.jsp" />

    <!-- Bootstrap and JS -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

    <!-- Modal Interaction -->
    <script>
        $(document).on('click', '.update-course', function() {
            const id = $(this).data('id');
            const name = $(this).data('name');
            const capacity = $(this).data('capacity');
            
            $('#updateCourseID').val(id);
            $('#updateCourseName').val(name);
            $('#updateCapacity').val(capacity);
            
            $('#updateCourseModal').modal('show');
        });
    </script>
</body>
</html>
