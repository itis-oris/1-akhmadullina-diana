    document.getElementById('signup-form').addEventListener('submit', function (event) {
    let isValid = true;

    const gmailField = document.getElementById('gmail');
    const gmailError = document.getElementById('gmail-error');
    const gmailRegex = /^(.+)@(.\S+)$/; // Simple email regex
    if (!gmailRegex.test(gmailField.value)) {
    gmailError.textContent = 'Please enter a valid Email address.';
    isValid = false;
} else {
    gmailError.textContent = '';
}
    const usernameField = document.getElementById('username');
    const usernameError = document.getElementById('username-error');
    if (usernameField.value.length < 1) {
    usernameError.textContent = 'Username can not be empty';
    isValid = false;
} else {
    usernameError.textContent = '';
}
    const passwordField = document.getElementById('password');
    const passwordError = document.getElementById('password-error');
    if (passwordField.value.length < 4) {
    passwordError.textContent = 'Password must be at least 4 characters long.';
    isValid = false;
} else {
    passwordError.textContent = '';
}
    if (!isValid) {
    event.preventDefault();
}
});