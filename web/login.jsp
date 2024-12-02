<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %> 
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login - EnrolPro</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            background-color: #f8f9fa;
        }
        .login-container {
            max-width: 400px;
            width: 100%;
            padding: 20px;
        }
        .login-box {
            background: #ffffff;
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
        }
        .login-box h1 {
            font-size: 1.5rem;
            margin-bottom: 1.5rem;
            color: #495057;
        }
        .btn-primary {
            width: 100%;
        }
        .register-link {
            font-size: 0.9rem;
            color: #6c757d;
        }
        .register-link a {
            color: #007bff;
            text-decoration: none;
        }
        .register-link a:hover {
            text-decoration: underline;
        }
        .alert {
            display: none; /* 默认隐藏错误信息 */
        }
        .alert.active {
            display: block; /* 当有错误信息时显示 */
            color: #721c24;
            background-color: #f8d7da;
            border: 1px solid #f5c6cb;
            padding: 10px;
            margin-bottom: 15px;
            border-radius: 5px;
        }
    </style>
</head>
<body>

    <div class="login-container">
        <div class="login-box text-center">
            <h1>Welcome to EnrolPro</h1>
            
            <!-- 动态显示错误信息 -->
            <div id="error-banner" class="alert"></div>

            <form id="login-form" action="/EnrollmentSystem/user" method="post">
                <input type="hidden" name="action" value="login">
                <div class="form-group">
                    <label for="username" class="sr-only">Username</label>
                    <input type="text" id="username" name="username" class="form-control" placeholder="Username" required autofocus>
                </div>

                <div class="form-group mt-3">
                    <label for="password" class="sr-only">Password</label>
                    <input type="password" id="password" name="password" class="form-control" placeholder="Password" required>
                </div>

                <button type="submit" class="btn btn-primary mt-4">Login</button>
            </form>
            <p class="register-link mt-3">Don't have an account? <a href="register.jsp">Register here</a></p>
        </div>
    </div>

    <!-- Include the footer -->
    <jsp:include page="footer.jsp" />

    <!-- JavaScript -->
    <script>
        // 动态处理后端传递的错误信息
        const errorMessage = '<%= request.getAttribute("error") != null ? ((String) request.getAttribute("error")).replaceAll("'", "\\'") : "" %>';
        if (errorMessage) {
            const errorBanner = document.getElementById('error-banner');
            errorBanner.textContent = errorMessage; // 设置错误信息内容
            errorBanner.classList.add('active'); // 显示错误框
        }

        // 表单前端校验
        document.getElementById('login-form').addEventListener('submit', function(event) {
            const username = document.getElementById('username').value.trim();
            const password = document.getElementById('password').value.trim();

            if (!username) {
                alert('Username cannot be empty.');
                event.preventDefault(); // 阻止表单提交
            }
            if (!password) {
                alert('Password cannot be empty.');
                event.preventDefault(); // 阻止表单提交
            }
        });
    </script>

</body>
</html>
