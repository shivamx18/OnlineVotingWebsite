const signUpButton = document.getElementById('sign-up-btn');
const signInButton = document.getElementById('sign-in-btn');
const container = document.querySelector('.container');


signUpButton.addEventListener('click', () => {
    container.classList.add('sign-up-mode');
});


signInButton.addEventListener('click', () => {
    container.classList.remove('sign-up-mode');
});


document.getElementById('signin-form').addEventListener('submit', function (e) {
    let isValid = true;
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;

    
    if (username === "") {
        document.getElementById('signin-username-errors').style.display = 'block';
        isValid = false;
    } else {
        document.getElementById('signin-username-errors').style.display = 'none';
    }

    if (password === "") {
        document.getElementById('signin-password-errors').style.display = 'block';
        isValid = false;
    } else {
        document.getElementById('signin-password-errors').style.display = 'none';
    }


    if (!isValid) e.preventDefault();  
});

document.getElementById('signup-form').addEventListener('submit', function (e) {
    let isValid = true;
    const firstName = document.getElementById('first_name').value;
    const lastName = document.getElementById('last_name').value;
    const username = document.getElementById('username_signup').value;
    const email = document.getElementById('email').value;
    const password = document.getElementById('password_signup').value;

    
    if (firstName === "") {
        document.getElementById('signup-firstname-errors').style.display = 'block';
        isValid = false;
    } else {
        document.getElementById('signup-firstname-errors').style.display = 'none';
    }

    if (lastName === "") {
        document.getElementById('signup-lastname-errors').style.display = 'block';
        isValid = false;
    } else {
        document.getElementById('signup-lastname-errors').style.display = 'none';
    }

    if (username === "") {
        document.getElementById('signup-username-errors').style.display = 'block';
        isValid = false;
    } else {
        document.getElementById('signup-username-errors').style.display = 'none';
    }

    if (email === "") {
        document.getElementById('signup-email-errors').style.display = 'block';
        isValid = false;
    } else {
        document.getElementById('signup-email-errors').style.display = 'none';
    }

    if (password === "" || password.length < 8) {
        document.getElementById('signup-password-errors').style.display = 'block';
        isValid = false;
    } else {
        document.getElementById('signup-password-errors').style.display = 'none';
    }

    if (!isValid) e.preventDefault();  
});