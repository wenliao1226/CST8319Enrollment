<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard - EnrolPro</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
</head>
<body>

    <!-- Header -->
    <div class="container my-5">
        <h1 class="text-center mb-4">Admin Dashboard</h1>

        <!-- Manage Courses Section -->
        <section>
            <h2>Manage Courses</h2>
            <div class="table-responsive">
                <table class="table table-bordered" id="courses-table">
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
                        <!-- 动态加载课程数据 -->
                    </tbody>
                </table>
            </div>
        </section>
    </div>

    <!-- JavaScript -->
    <script>
        document.addEventListener("DOMContentLoaded", async function () {
            try {
                // 调用后端 API 获取课程数据
                const response = await fetch("http://localhost:8080/EnrollmentSystem/course?action=getAll");

                // 检查响应是否成功
                if (!response.ok) {
                    console.error("Failed to fetch courses:", response.statusText);
                    return;
                }

                // 解析 JSON 数据
                const courses = await response.json();
                console.log("Courses via Fetch API:", courses);

                // 渲染表格
                const tableBody = document.querySelector("#courses-table tbody");
                tableBody.innerHTML = ""; // 清空表格内容

                courses.forEach(course => {
                    const row = `
                        <tr>
                            <td>${course.courseId || ''}</td>
                            <td>${course.courseName || ''}</td>
                            <td>${course.capacity || ''}</td>
                            <td>${course.program || ''}</td>
                            <td>${course.instructor || ''}</td>
                            <td>${course.location || ''}</td>
                        </tr>
                    `;
                    tableBody.insertAdjacentHTML("beforeend", row);
                });
            } catch (error) {
                console.error("Error loading courses:", error);
            }
        });
    </script>

</body>
</html>
