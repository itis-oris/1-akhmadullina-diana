<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/html" lang="">
<head>
    <meta charset="utf-8"/>
    <link type="text/css" href="css/signin.css" rel="stylesheet">
    <title>Sign In</title>
</head>
<body>

<div class="form-container">
    <h1>Sign in</h1>

    <div id="server-error" class="error-message">
        <#if errorMessage??>${errorMessage}<#else></#if>
   </div>

    <form id="signin-form" action="signin" method="post">
        <div>
            <label for="gmail">Email</label>
            <input type="text" id="gmail" name="gmail">
            <small id="gmail-error" class="error-message"></small>
        </div>
        <div>
            <label for="password">Password</label>
            <input type="password" id="password" name="password">
            <small id="password-error" class="error-message"></small>
        </div>
        <div>
            <label>
                <input type="checkbox" name="check" value="remember">
                Remember me
            </label>
        </div>
        <div>
            <input type="submit" value="Sign in">
        </div>
    </form>

    <form action="signup" method="get">
        <div>
            <label>New one here?</label>
            <input type="submit" value="Sign up">
        </div>
    </form>
</div>

<script>
    // Validation logic
    document.getElementById('signin-form').addEventListener('submit', function (event) {
        let isValid = true;

        //Gmail validation
        const gmailField = document.getElementById('gmail');
        const gmailError = document.getElementById('gmail-error');
        const gmailRegex = /^(.+)@(\S+)$/; // Simple email regex
        if (!gmailRegex.test(gmailField.value)) {
            gmailError.textContent = 'Please enter a valid Gmail address.';
            isValid = false;
        } else {
            gmailError.textContent = '';
        }

        // Password validation
        const passwordField = document.getElementById('password');
        const passwordError = document.getElementById('password-error');
        if (passwordField.value.length < 4) {
            passwordError.textContent = 'Password must be at least 4 characters long.';
            isValid = false;
        } else {
            passwordError.textContent = '';
        }

        // Prevent form submission if validation fails
        if (!isValid) {
            event.preventDefault();
        }
    });

</script>

<#--<style>-->
<#--    .error-message {-->
<#--        color: red;-->
<#--        font-size: 0.9em;-->
<#--    }-->
<#--    body {-->
<#--        background-image: url('https://i.pinimg.com/736x/45/7a/04/457a048b99ea2db5588258674d238e5d.jpg');-->
<#--        background-size: cover;-->
<#--        background-position: top;-->
<#--        font-family: cursive;-->
<#--        margin: 0;-->
<#--        padding: 0;-->
<#--        color: white;-->
<#--    }-->

<#--    .form-container {-->
<#--        max-width: 600px;-->
<#--        margin: 50px auto;-->
<#--        padding: 20px;-->
<#--        background-color: rgba(0, 0, 0, 0.6);-->
<#--        border-radius: 10px;-->
<#--        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.5);-->
<#--    }-->

<#--    form input[type="text"],-->
<#--    form input[type="password"],-->
<#--    form input[type="checkbox"] {-->
<#--        font-family: cursive;-->
<#--        width: 98%;-->
<#--        padding: 8px;-->
<#--        margin-bottom: 16px;-->
<#--        border: none;-->
<#--        border-radius: 8px;-->
<#--        font-size: 16px;-->
<#--        background-color: #f7f7f7;-->
<#--    }-->

<#--    form input[type="checkbox"] {-->
<#--        width: auto;-->
<#--        margin-right: 10px;-->
<#--    }-->

<#--    .form-container input[type="submit"] {-->
<#--        font-family: cursive;-->
<#--        width: 100%;-->
<#--        padding: 12px;-->
<#--        border: none;-->
<#--        border-radius: 5px;-->
<#--        background-color:rgba(213, 31, 116, 0.9);-->
<#--        color: white;-->
<#--        font-size: 18px;-->
<#--    }-->
<#--</style>-->

</body>
</html>

