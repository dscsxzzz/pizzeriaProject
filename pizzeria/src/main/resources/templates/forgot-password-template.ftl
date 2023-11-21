<!DOCTYPE html>
<html>
<head>
    <title>Forgot Password</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            color: #333;
            line-height: 1.6;
        }
        .container {
            max-width: 600px;
            margin: 20px auto;
            padding: 20px;
            background: #fff;
        }
        .button {
            display: inline-block;
            padding: 10px 20px;
            margin-top: 20px;
            background: #007bff;
            color: #fff;
            text-decoration: none;
            border-radius: 5px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Password Reset Request</h2>
        <p>Hi,</p>
        <p>You recently requested to reset your password for your account. Use the code below to reset it. This password reset code is only valid for the next 5 minutes.</p>
        
        <!-- Password Reset Code -->
        <h3 style="text-align: center; font-size: 24px;">${resetCode}</h3>
        
        <p>If you did not request a password reset, please ignore this email or contact support if you have questions.</p>
        <p>Thanks,<br>Pizzeria Team</p>
    </div>
</body>
</html>
