<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard - EnrolPro</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="CSS/style.css">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
</head>
<body>

    <!-- Include the menu -->
    <jsp:include page="menu.jsp" />

    <div class="container my-5">
        <h1 class="text-center mb-4">Admin Dashboard</h1>

        <!-- Add Course Section -->
        <section class="mb-5">
            <h2>Add Course</h2>
            <form id="add-course-form" action="AddCourseServlet" method="post">
                <div class="form-row">
                    <div class="form-group col-md-4">
                        <label for="courseID">Course ID</label>
                        <input type="text" class="form-control" id="courseID" name="courseID" required>
                    </div>
                    <div class="form-group col-md-4">
                        <label for="courseName">Course Name</label>
                        <input type="text" class="form-control" id="courseName" name="courseName" required>
                    </div>
                    <div class="form-group col-md-4">
                        <label for="capacity">Capacity</label>
                        <input type="number" class="form-control" id="capacity" name="capacity" required>
                    </div>
                    <div class="form-group col-md-4">
                        <label for="programId">Program Name</label>
                        <select class="form-control" id="programId" name="programId" required>
                            <option value="">Select a Program</option>
                            <!-- 动态加载选项 -->
                        </select>
                    </div>
                    <div class="form-group col-md-4">
                        <label for="Instructor">Instructor</label>
                        <input type="text" class="form-control" id="instructor" name="instructor" required>
                    </div>
                    <div class="form-group col-md-4">
                        <label for="Location">Location</label>
                        <input type="text" class="form-control" id="location" name="location" required>
                    </div>
                </div>
                <button type="submit" class="btn btn-success">Add Course</button>
            </form>
        </section>

        <!-- Update/Delete Courses Section -->
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
                            <th>Program Name</th>
                            <th>Instructor</th>
                            <th>Location</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <!-- 动态加载课程信息 -->
                        <c:forEach var="course" items="${courses}">
                            <tr>
                                <td>${course.courseId}</td>
                                <td>${course.courseName}</td>
                                <td>${course.capacity}</td>
                                <td>${course.programName}</td>
                                <td>${course.instructor}</td>
                                <td>${course.location}</td>
                                <td>
                                    <button class="btn btn-warning btn-sm update-course" 
                                            data-id="${course.courseId}" 
                                            data-name="${course.courseName}" 
                                            data-capacity="${course.capacity}" 
                                            data-program="${course.programName}" 
                                            data-instructor="${course.instructor}" 
                                            data-location="${course.location}">
                                        Update
                                    </button>
                                    <form action="DeleteCourseServlet" method="post" style="display:inline;">
                                        <input type="hidden" name="courseID" value="${course.courseId}">
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
                <form id="update-course-form" action="UpdateCourseServlet" method="post">
                    <div class="modal-header">
                        <h5 class="modal-title" id="updateCourseModalLabel">Update Course</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                            <label for="updateCourseID">Course ID</label>
                            <input type="text" class="form-control" id="updateCourseID" name="courseID" required>
                        </div>
                        <div class="form-group">
                            <label for="updateCourseName">Course Name</label>
                            <input type="text" class="form-control" id="updateCourseName" name="courseName" required>
                        </div>
                        <div class="form-group">
                            <label for="updateCapacity">Capacity</label>
                            <input type="number" class="form-control" id="updateCapacity" name="capacity" required>
                        </div>
                        <div class="form-group">
                            <label for="updateProgramId">Program Name</label>
                            <select class="form-control" id="updateProgramId" name="programId" required>
                                <!-- 动态加载选项 -->
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="updateInstructor">Instructor</label>
                            <input type="text" class="form-control" id="updateInstructor" name="instructor" required>
                        </div>
                        <div class="form-group">
                            <label for="updateLocation">Location</label>
                            <input type="text" class="form-control" id="updateLocation" name="location" required>
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
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

    <!-- Modal Interaction -->
    <script>
    document.addEventListener("DOMContentLoaded", async function () {
        // 加载 program 数据
        try {
            const programResponse = await fetch("http://localhost:8080/EnrollmentSystem/program");
            if (!programResponse.ok) {
                console.error("Failed to fetch programs:", programResponse.statusText);
                return;
            }

            const programs = await programResponse.json();
            console.log("Programs via Fetch API:", programs);

            // 填充 Program 下拉菜单
            const programSelect = document.getElementById("programId");
            programSelect.innerHTML = '<option value="">Select a Program</option>';
            programs.forEach(program => {
                const option = document.createElement("option");
                option.value = program.programId;
                option.textContent = program.programName;
                programSelect.appendChild(option);
            });
        } catch (error) {
            console.error("Failed to load programs via Fetch API:", error);
            alert("Failed to load programs. Please try again later.");
        }
        
          });
       </script>
        
 <script>
document.addEventListener("DOMContentLoaded", async function () {
        // 加载 course 数据
        try {
            const courseResponse = await fetch("http://localhost:8080/EnrollmentSystem/course?action=getAll");
            if (!courseResponse.ok) {
                console.error("Failed to fetch courses:", courseResponse.statusText);
                return;
            }

            const courses = await courseResponse.json();
            console.log("Courses via Fetch API:", courses);

            // 填充 Course 表格
            const tableBody = document.querySelector("table tbody");
            tableBody.innerHTML = ""; // 清空表格内容
            courses.forEach(course => {
                const row = `
                    <tr>
                        <td>${course.courseId}</td>
                        <td>${course.courseName}</td>
                        <td>${course.capacity}</td>
                        <td>${course.program}</td>
                        <td>${course.instructor}</td>
                        <td>${course.location}</td>
                        <td>
                            <button class="btn btn-warning btn-sm update-course"
                                    data-id="${course.courseId}"
                                    data-name="${course.courseName}"
                                    data-capacity="${course.capacity}"
                                    data-program="${course.program}"
                                    data-instructor="${course.instructor}"
                                    data-location="${course.location}">
                                Update
                            </button>
                            <form action="DeleteCourseServlet" method="post" style="display:inline;">
                                <input type="hidden" name="courseID" value="${course.courseId}">
                                <button type="submit" class="btn btn-danger btn-sm">Delete</button>
                            </form>
                        </td>
                    </tr>
                `;
                tableBody.insertAdjacentHTML("beforeend", row);
            });
        } catch (error) {
            console.error("Error loading courses:", error);
        }
    });
       </script>
       
       
    
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
