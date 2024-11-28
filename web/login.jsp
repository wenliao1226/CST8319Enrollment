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
        /* Custom styling for the login page */
        body {
            display: flex;
            flex-direction: column;
            background-color: #f8f9fa;
            min-height: 100vh;
            margin: 0;
        }
        .navbar {
            width: 100%;
            position: fixed;
            top: 0;
            left: 0;
            z-index: 1000;
        }
        .login-container {
            max-width: 400px;
            width: 100%;
            margin: 100px auto 50px; /* Adjust margin to account for fixed navbar */
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
            margin-top: 20px;
        }
    </style>
</head>
<body>

    <!-- Include the menu -->
    <jsp:include page="menu.jsp" />

    <!-- Show Success Message if Present -->
    <% 
        String message = request.getParameter("message");
        if (message != null && !message.isEmpty()) { 
    %>
        <div class="alert alert-success text-center" role="alert">
            <%= message %>
        </div>
    <% 
        } 
    %>

    <!-- Login Page Content -->
    <div class="login-container">
        <div class="login-box text-center">
            <h1 class="mb-4">Welcome to EnrolPro</h1>
            <form id="login-form" action="/EnrollmentSystem/user?action=login" method="post">
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

    <!-- Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script>
    document.getElementById('login-form').addEventListener('submit', function(event) {
        const username = document.getElementById('username').value.trim();
        const password = document.getElementById('password').value.trim();

        if (!username) {
            alert('Username cannot be empty.');
            event.preventDefault(); // Prevent form submission
        }
        if (!password) {
            alert('Password cannot be empty.');
            event.preventDefault(); // Prevent form submission
        }
    });
	</script>

</body>
</html>
