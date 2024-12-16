<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container my-5">
        <h1 class="text-center mb-4">Admin Dashboard</h1>
        <section>
            <h2>Manage Courses</h2>
            <div class="table-responsive">
                <table id="courses-table" class="table table-bordered">
                    <thead class="thead-dark">
                        <tr>
                            <th>Course ID</th>
                            <th>Course Name</th>
                            <th>Capacity</th>
                            <th>Program Name</th>
                            <th>Instructor</th>
                            <th>Location</th>
                        </tr>
                    </thead>
                    <tbody>
                        <!-- 动态加载数据 -->
                        <c:forEach var="course" items="${courses}">
                            <tr>
                                <td>${empty course.courseId ? '' : course.courseId}</td>
                                <td>${empty course.courseName ? '' : course.courseName}</td>
                                <td>${empty course.capacity ? '' : course.capacity}</td>
                                <td>${empty course.program ? '' : course.program}</td>
                                <td>${empty course.instructor ? '' : course.instructor}</td>
                                <td>${empty course.location ? '' : course.location}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </section>
    </div>
</body>
</html>