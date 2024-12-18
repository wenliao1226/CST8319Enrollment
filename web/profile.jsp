<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
        .profile-picture img {
            width: 150px;
            height: 150px;
            border-radius: 50%;
            object-fit: cover;
            margin-bottom: 20px;
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
        <% 
            model.User user = (model.User) session.getAttribute("user");
            String username = user != null ? user.getUsername() : "";
            String email = user != null ? user.getEmail() : "";
            String fullName = user != null ? user.getFirstName() + " " + user.getLastName() : "";
        %>
        <div class="text-center profile-picture">
            <img src="profile-picture.png" alt="Profile Picture">
            <input type="file" class="form-control mt-2" id="profilePicture" name="profilePicture" accept="image/*">
        </div>

        <form id="profile-form" action="user" method="post">
            <input type="hidden" name="action" value="updatePassword" />

            <div class="form-group">
                <label for="username">Username</label>
                <input type="text" id="username" name="username" class="form-control" value="<%= username %>" readonly>
            </div>

            <div class="form-group">
                <label for="email">Email</label>
                <input type="email" id="email" name="email" class="form-control" value="<%= email %>" readonly>
            </div>

            <div class="form-group">
                <label for="fullName">Full Name</label>
                <input type="text" id="fullName" name="fullName" class="form-control" value="<%= fullName %>" readonly>
            </div>

            <!-- Password Update Section -->
            <div class="form-group mt-4">
                <label for="currentPassword">Current Password</label>
                <input type="password" id="currentPassword" name="currentPassword" class="form-control" placeholder="Enter Current Password" required>
            </div>

            <div class="form-group">
                <label for="newPassword">New Password</label>
                <input type="password" id="newPassword" name="newPassword" class="form-control" placeholder="Enter New Password" required>
            </div>

            <div class="form-group">
                <label for="confirmNewPassword">Confirm New Password</label>
                <input type="password" id="confirmNewPassword" name="confirmNewPassword" class="form-control" placeholder="Confirm New Password" required>
            </div>

            <button type="submit" class="btn btn-primary mt-3">Update Password</button>
            <button type="reset" class="btn btn-secondary">Cancel</button>
        </form>
    </div>
    
    <!-- Include the footer -->
    <jsp:include page="footer.jsp" />

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
