<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Contact Us - EnrolPro</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <style>
        /* Custom styling for the contact page */
        body {
            background-color: #f8f9fa;
        }
        .contact-container {
            max-width: 600px;
            margin: 50px auto;
            padding: 20px;
            background: #ffffff;
            border-radius: 10px;
            box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
        }
        .contact-container h1 {
            font-size: 1.5rem;
            margin-bottom: 1.5rem;
            color: #495057;
            text-align: center;
        }
        .btn-primary {
            width: 100%;
        }
    </style>
</head>
<body>

    <!-- Include the menu -->
    <jsp:include page="menu.jsp" />

    <!-- Contact Us Form -->
    <div class="contact-container">
        <h1>Contact Us</h1>
        <form id="contact-form" action="submit_contact" method="post">
            <div class="form-group">
                <label for="name">Name</label>
                <input type="text" id="name" name="name" class="form-control" placeholder="Your Name" required>
            </div>

            <div class="form-group mt-3">
                <label for="email">Email</label>
                <input type="email" id="email" name="email" class="form-control" placeholder="Your Email" required>
            </div>

            <div class="form-group mt-3">
                <label for="message">Message</label>
                <textarea id="message" name="message" class="form-control" rows="5" placeholder="Your Message" required></textarea>
            </div>

            <button type="submit" class="btn btn-primary mt-4">Send Message</button>
        </form>
    </div>
    
    <!-- Include the footer -->
    <jsp:include page="footer.jsp" />

    <!-- Bootstrap JS (needed for navbar toggle) -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
