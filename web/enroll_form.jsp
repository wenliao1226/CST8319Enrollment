<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Enroll in Course</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
    <jsp:include page="menu.jsp" />

    <div class="container my-5">
        <h1 class="text-center">Enroll in Course</h1>
        <div class="card mx-auto" style="max-width: 600px;">
            <div class="card-body">
                <h3 class="card-title text-center">${param.courseName}</h3>
                <form action="enrollment" method="post">
                    <!-- Hidden Fields -->
                    <input type="hidden" name="action" value="enroll" />
                    <input type="hidden" name="courseId" value="${param.courseId}" />

                    <!-- Student ID -->
                    <div class="form-group">
                        <label for="studentId">Your Student ID</label>
                        <input type="text" class="form-control" id="studentId" name="studentId" placeholder="Enter your Student ID" required>
                    </div>

                    <!-- Status -->
                    <div class="form-group">
                        <label for="status">Enrollment Status</label>
                        <select class="form-control" id="status" name="status">
                            <option value="Pending" selected>Pending</option>
                            <option value="Confirmed">Confirmed</option>
                        </select>
                    </div>

                    <!-- Enrollment Date -->
                    <div class="form-group">
                        <label for="enrollmentDate">Enrollment Date</label>
                        <input type="date" class="form-control" id="enrollmentDate" name="enrollmentDate" value="<%= java.time.LocalDate.now() %>" readonly>
                    </div>

                    <!-- Confirmation Button -->
                    <button type="submit" class="btn btn-primary w-100">Confirm Enrollment</button>
                </form>
            </div>
        </div>
    </div>

    <!-- Include the footer -->
    <jsp:include page="footer.jsp" />

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
