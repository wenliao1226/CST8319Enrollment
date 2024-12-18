<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
    <jsp:include page="menu.jsp" />

    <!-- Dashboard Page Content -->
    <div class="container my-5">
    <%-- Display success or error messages --%>
<c:if test="${not empty message}">
    <div class="alert alert-success alert-dismissible fade show" role="alert">
        ${message}
        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
            <span aria-hidden="true">&times;</span>
        </button>
    </div>
</c:if>
		<c:if test="${not empty error}">
    		<div class="alert alert-danger alert-dismissible fade show" role="alert">
        ${error}
        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
            <span aria-hidden="true">&times;</span>
        </button>
   		 </div>
		</c:if>
        <% 
            model.User user = (model.User) session.getAttribute("user");
            String userName = (user != null) ? user.getFirstName() + " " + user.getLastName() : "Guest";
        %>
        <h2 class="text-center">Hi <%= userName %>, welcome to EnrolPro!</h2>
        <h1 class="text-center">Student Dashboard</h1>
        
        <!-- Search Courses Section -->
        <section class="search-courses my-4">
            <h2>Search Courses</h2>
            <form action="course" method="get" class="form-row">
                <input type="hidden" name="action" value="search" />

                <!-- Course Name Filter -->
                <div class="form-group col-md-3">
                    <label for="courseName">Course Name</label>
                    <input type="text" class="form-control" id="courseName" name="courseName" placeholder="Enter Course Name">
                </div>

                <!-- Course ID Filter -->
                <div class="form-group col-md-2">
                    <label for="courseID">Course ID</label>
                    <input type="text" class="form-control" id="courseID" name="courseID" placeholder="Enter Course ID">
                </div>

                <!-- Program ID Filter -->
                <div class="form-group col-md-3">
                    <label for="programID">Program ID</label>
                    <input type="text" class="form-control" id="programID" name="programID" placeholder="Enter Program ID">
                </div>

                <!-- Search Button -->
                <div class="form-group col-md-2 align-self-end">
                    <button type="submit" class="btn btn-primary w-100">Search</button>
                </div>
            </form>
        </section>

        <!-- Search Results Section -->
        <section class="search-results my-4">
            <h3>Search Results</h3>
            <div class="table-responsive">
                <table class="table table-bordered">
                    <thead class="thead-light">
                        <tr>
                            <th>Course ID</th>
                            <th>Course Name</th>
                            <th>Program ID</th>
                            <th>Credits</th>
                            <th>Capacity</th>
                            <th>Instructor</th>
                            <th>Schedule</th>
                            <th>Location</th>
                            <th>Description</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:if test="${empty courses}">
                            <tr>
                                <td colspan="10" class="text-center">No courses found.</td>
                            </tr>
                        </c:if>
                        <c:forEach var="course" items="${courses}">
                            <tr>
                                <td>${course.courseID}</td>
                                <td>${course.name}</td>
                                <td>${course.programId}</td>
                                <td>${course.credits}</td>
                                <td>${course.capacity}</td>
                                <td>${course.instructor}</td>
                                <td>${course.schedule}</td>
                                <td>${course.location}</td>
                                <td>${course.description}</td>
                                <td>
                                    <a href="enroll_form.jsp?courseId=${course.courseID}&courseName=${course.name}" 
                                       class="btn btn-success btn-sm" target="_blank">Enroll</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </section>
        
        <div class="text-center my-4">
    <form action="enrollment" method="get" target="_blank">
        <input type="hidden" name="action" value="getbystudentid" />
        <button type="submit" class="btn btn-primary">Show Enrolled Courses</button>
    </form>
</div>

        
    <!-- Include the footer -->
    <jsp:include page="footer.jsp" />

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
