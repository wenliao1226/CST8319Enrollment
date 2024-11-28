<%@page import="model.User"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Student Dashboard</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<%
  
    User user = (User) session.getAttribute("user");
    if (user == null || !"Student".equalsIgnoreCase(user.getType())) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<jsp:include page="menu.jsp" />

<div class="container my-5">
    <h1 class="text-center">Welcome, <%= user.getFirstName() %>!</h1>
    
    <!-- Search Courses Section -->
    <section class="search-courses my-4">
        <h2>Search Courses</h2>
        <form id="search-form" class="form-row">
            <!-- Course Name -->
            <div class="form-group col-md-3">
                <label for="courseName">Course Name</label>
                <input type="text" class="form-control" id="courseName" name="courseName" placeholder="Enter Course Name">
            </div>
            <!-- Course ID -->
            <div class="form-group col-md-3">
                <label for="courseID">Course ID</label>
                <input type="text" class="form-control" id="courseID" name="courseID" placeholder="Enter Course ID">
            </div>
            <!-- Program -->
            <div class="form-group col-md-3">
                <label for="program">Program</label>
                <input type="text" class="form-control" id="program" name="program" placeholder="Enter Program">
            </div>
            <!-- Search Button -->
            <div class="form-group col-md-3 align-self-end">
                <button type="submit" class="btn btn-primary w-100">Search</button>
            </div>
        </form>
    </section>

    <!-- Search Results -->
    <section class="search-results my-4">
        <h2>Search Results</h2>
        <div class="table-responsive">
            <table class="table table-bordered">
                <thead class="thead-dark">
                    <tr>
                        <th>Course ID</th>
                        <th>Course Name</th>
                        <th>Capacity</th>
                        <th>Schedule</th>
                        <th>Program</th>
                    </tr>
                </thead>
                <tbody id="search-results">
                    <!-- Search results will appear here -->
                </tbody>
            </table>
        </div>
    </section>
</div>

<jsp:include page="footer.jsp" />

<script>
    document.getElementById('search-form').addEventListener('submit', function(event) {
        event.preventDefault();

        const courseName = document.getElementById('courseName').value.trim();
        const courseID = document.getElementById('courseID').value.trim();
        const program = document.getElementById('program').value.trim();

        fetch(`/EnrollmentSystem/course?action=search&courseName=${encodeURIComponent(courseName)}&courseID=${encodeURIComponent(courseID)}&program=${encodeURIComponent(program)}`)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Failed to fetch courses');
                }
                return response.json();
            })
            .then(courses => {
                const tbody = document.getElementById('search-results');
                tbody.innerHTML = '';

                if (courses.length === 0) {
                    tbody.innerHTML = '<tr><td colspan="5" class="text-center">No courses found</td></tr>';
                    return;
                }

                courses.forEach(course => {
                    const row = `
                        <tr>
                            <td>${course.courseId}</td>
                            <td>${course.courseName}</td>
                            <td>${course.capacity}</td>
                            <td>${course.schedule}</td>
                            <td>${course.program || 'N/A'}</td>
                        </tr>`;
                    tbody.innerHTML += row;
                });
            })
            .catch(error => {
                console.error(error);
                alert('An error occurred while searching for courses.');
            });
    });
</script>
</body>
</html>
