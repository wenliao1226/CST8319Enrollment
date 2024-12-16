<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Profile - EnrolPro</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <style>
        /* Custom styling for the profile page */
        body {
            background-color: #f8f9fa;
        }
        .profile-container {
            max-width: 500px;
            margin: 50px auto;
            padding: 20px;
            background: #ffffff;
            border-radius: 10px;
            box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
        }
        .form-group label {
            font-weight: bold;
        }
        .btn-primary {
            width: 100%;
        }
        .btn-secondary {
            width: 100%;
            margin-top: 10px;
        }
    </style>
</head>
<body>
    <!-- Include the menu -->
    <jsp:include page="menu.jsp" />

    <!-- Profile Page Content -->
    <div class="container profile-container">
        <h1 class="text-center">Your Profile</h1>

        <!-- Display Error or Success Messages -->
        <c:if test="${not empty error}">
            <div class="alert alert-danger">${error}</div>
        </c:if>
        <c:if test="${not empty success}">
            <div class="alert alert-success">${success}</div>
        </c:if>

        <!-- Profile Update Form -->
        <form id="profile-form" action="user?action=update" method="post">
            <input type="hidden" name="userId" value="${user.userId}">
            <div class="form-group">
                <label for="username">Username</label>
                <input type="text" id="username" name="username" class="form-control" value="${user.username}" required>
            </div>
            <div class="form-group">
                <label for="email">Email</label>
                <input type="email" id="email" name="email" class="form-control" value="${user.email}" required>
            </div>
            <div class="form-group">
                <label for="firstName">First Name</label>
                <input type="text" id="firstName" name="firstName" class="form-control" value="${user.firstName}" required>
            </div>
            <div class="form-group">
                <label for="lastName">Last Name</label>
                <input type="text" id="lastName" name="lastName" class="form-control" value="${user.lastName}" required>
            </div>
            <div class="form-group">
                <label for="address">Address</label>
                <input type="text" id="address" name="address" class="form-control" value="${user.address}">
            </div>
            <div class="form-group">
                <label for="phoneNumber">Phone Number</label>
                <input type="text" id="phoneNumber" name="phoneNumber" class="form-control" value="${user.phoneNumber}">
            </div>
            <div class="form-group">
                <label for="dateOfBirth">Date of Birth</label>
                <input type="date" id="dateOfBirth" name="dateOfBirth" class="form-control" value="${user.dateOfBirth}">
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" id="password" name="password" class="form-control" value="${user.password}" required>
            </div>
            <button type="submit" class="btn btn-primary mt-3">Update Profile</button>
        </form>
    </div>
    
    <!-- Include the footer -->
    <jsp:include page="footer.jsp" />

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
