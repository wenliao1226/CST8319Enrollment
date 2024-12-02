<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register - EnrolPro</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <style>
        /* Custom styling for the register page */
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            background-color: #f8f9fa;
        }
        .register-container {
            max-width: 450px;
            width: 100%;
            padding: 20px;
        }
        .register-box {
            background: #ffffff;
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
        }
        .register-box h1 {
            font-size: 1.5rem;
            margin-bottom: 1.5rem;
            color: #495057;
        }
        .btn-primary {
            width: 100%;
        }
        .login-link {
            font-size: 0.9rem;
            color: #6c757d;
        }
        .login-link a {
            color: #007bff;
            text-decoration: none;
        }
        .login-link a:hover {
            text-decoration: underline;
        }
        .error-banner {
            display: none; /* 默认隐藏错误信息 */
            color: #721c24;
            background-color: #f8d7da;
            border: 1px solid #f5c6cb;
            padding: 10px;
            margin-bottom: 15px;
            border-radius: 5px;
        }
        .error-banner.active {
            display: block; /* 当有错误信息时显示 */
        }
    </style>
</head>
<body>

    <!-- Register Page Content -->
    <div class="register-container">
        <div class="register-box text-center">
            <h1>Register for EnrolPro</h1>

            <!-- Error Banner -->
            <div id="error-banner" class="error-banner"></div>

            <form id="register-form" action="/EnrollmentSystem/user" method="post">
                <input type="hidden" name="action" value="register">

                <div class="form-group">
                    <label for="first-name" class="sr-only">First Name</label>
                    <input type="text" class="form-control" id="first-name" name="first-name" placeholder="First Name" required>
                </div>

                <div class="form-group">
                    <label for="last-name" class="sr-only">Last Name</label>
                    <input type="text" class="form-control" id="last-name" name="last-name" placeholder="Last Name" required>
                </div>

                <div class="form-group">
                    <label for="username" class="sr-only">Username</label>
                    <input type="text" class="form-control" id="username" name="username" placeholder="Username" required>
                </div>

                <div class="form-group">
                    <label for="email" class="sr-only">Email</label>
                    <input type="email" class="form-control" id="email" name="email" placeholder="Email" required>
                </div>

                <div class="form-group">
                    <label for="password" class="sr-only">Password</label>
                    <input type="password" class="form-control" id="password" name="password" placeholder="Password" required>
                </div>

                <div class="form-group">
                    <label for="confirm-password" class="sr-only">Confirm Password</label>
                    <input type="password" class="form-control" id="confirm-password" name="confirm-password" placeholder="Confirm Password" required>
                </div>

                <button type="submit" class="btn btn-primary mt-4">Sign Up</button>
            </form>
            <p class="login-link mt-3">Already have an account? <a href="login.jsp">Login here</a></p>
        </div>
    </div>
    
    <!-- Include the footer -->
    <jsp:include page="footer.jsp" />

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script>
        // Handle server-side error messages
        const errorMessage = '<%= request.getAttribute("error") != null ? request.getAttribute("error") : "" %>';
        if (errorMessage) {
            const errorBanner = document.getElementById('error-banner');
            errorBanner.textContent = errorMessage;
            errorBanner.classList.add('active');
        }

        document.getElementById('register-form').addEventListener('submit', function(event) {
            const email = document.getElementById('email').value.trim();
            const password = document.getElementById('password').value;
            const confirmPassword = document.getElementById('confirm-password').value;

            // Email validation
            const validDomains = ["@collegestudent.com", "@collegeadmin.com"];
            if (!validDomains.some(domain => email.endsWith(domain))) {
                const errorBanner = document.getElementById('error-banner');
                errorBanner.textContent = 'Email must end with @collegestudent.com or @collegeadmin.com.';
                errorBanner.classList.add('active');
                event.preventDefault();
                return;
            }

            // Password validation
            const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;
            if (!passwordRegex.test(password)) {
                const errorBanner = document.getElementById('error-banner');
                errorBanner.textContent = 'Password must be at least 8 characters long, include an uppercase letter, a number, and a special character.';
                errorBanner.classList.add('active');
                event.preventDefault();
                return;
            }

            // Confirm password validation
            if (password !== confirmPassword) {
                const errorBanner = document.getElementById('error-banner');
                errorBanner.textContent = 'Passwords do not match.';
                errorBanner.classList.add('active');
                event.preventDefault();
            }
        });
    </script>

</body>
</html>
