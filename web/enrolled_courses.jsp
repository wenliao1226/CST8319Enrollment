<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Enrolled Courses</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container my-5">
        <h1 class="text-center">Your Enrolled Courses</h1>

        <div class="table-responsive">
            <table class="table table-bordered">
                <thead class="thead-dark">
                    <tr>
                        <th>Course ID</th>
                        <th>Course Name</th>
                        <th>Capacity</th>
                        <th>Schedule</th>
                        <th>Status</th>
                        <th>Enrollment Date</th>
                    </tr>
                </thead>
                <tbody>
                    <c:if test="${empty enrolledCourses}">
                        <tr>
                            <td colspan="6" class="text-center">You are not enrolled in any courses yet.</td>
                        </tr>
                    </c:if>
                    <c:forEach var="enrollment" items="${enrolledCourses}">
                        <tr>
                            <td>${enrollment.courseId}</td>
                            <td>${enrollment.course.name}</td> <!-- Assuming you have a nested 'Course' object -->
                            <td>${enrollment.course.capacity}</td>
                            <td>${enrollment.course.schedule}</td>
                            <td>${enrollment.status}</td>
                            <td>${enrollment.enrollmentDate}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>
