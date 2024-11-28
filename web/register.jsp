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
        .alert {
            margin-bottom: 20px;
        }
    </style>
</head>
<body>

    <!-- Show Error Message if Present -->
    <% 
        String error = request.getParameter("error");
        if (error != null && !error.isEmpty()) { 
    %>
        <div class="alert alert-danger text-center" role="alert">
            <%= error %>
        </div>
    <% 
        } 
    %>

    <!-- Register Page Content -->
    <div class="register-container">
        <div class="register-box text-center">
            <h1>Register for EnrolPro</h1>
            <form id="register-form" action="/EnrollmentSystem/user?action=register" method="post">
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

</body>
</html>
