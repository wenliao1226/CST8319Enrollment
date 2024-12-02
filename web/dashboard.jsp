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
        <h1 class="text-center">Student Dashboard</h1>
        
        <!-- Search Courses Section -->
        <section class="search-courses my-4">
            <h2>Search Courses</h2>
            <form action="search_courses" method="get" class="form-row">
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
                <!-- Date Filter -->
                <div class="form-group col-md-2">
                    <label for="date">Date</label>
                    <input type="date" class="form-control" id="date" name="date">
                </div>
                <!-- Program Filter -->
                <div class="form-group col-md-3">
                    <label for="program">Program</label>
                    <input type="text" class="form-control" id="program" name="program" placeholder="Enter Program">
                </div>
                <!-- Search Button -->
                <div class="form-group col-md-2 align-self-end">
                    <button type="submit" class="btn btn-primary w-100">Search</button>
                </div>
            </form>
        </section>
        
        <!-- Current Enrollments Section -->
        <section class="current-enrollments my-4">
            <h2>Your Enrolled Courses</h2>
            <div class="table-responsive">
                <table class="table table-bordered">
                    <thead class="thead-dark">
                        <tr>
                            <th>Course ID</th>
                            <th>Course Name</th>
                            <th>Capacity</th>
                            <th>Schedule</th>
                            <th>Status</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <!-- Example Course Row (To be dynamically generated based on enrolled courses) -->
                        <tr>
                            <td>101</td>
                            <td>Introduction to Java</td>
                            <td>30</td>
                            <td>Mon and Wed 10:00 AM - 12:00 PM</td>
                            <td>Enrolled</td>
                            <td>
                                <button class="btn btn-warning btn-sm" onclick="updateCourse(101)">Update</button>
                                <button class="btn btn-danger btn-sm" onclick="deleteCourse(101)">Delete</button>
                            </td>
                        </tr>
                        <!-- Repeat rows for each enrolled course -->
                    </tbody>
                </table>
            </div>
        </section>
    </div>
    
    <!-- Include the footer -->
    <jsp:include page="footer.jsp" />

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script>
        // JavaScript functions to handle Update and Delete actions
        function updateCourse(courseId) {
            // Code to handle updating the course (e.g., open update form)
            alert("Update functionality for Course ID " + courseId);
        }

        function deleteCourse(courseId) {
            // Code to handle deleting the course (e.g., confirm deletion)
            if (confirm("Are you sure you want to delete this enrollment?")) {
                // Logic to delete the course enrollment
                alert("Course ID " + courseId + " deleted.");
            }
        }
    </script>
</body>
</html>