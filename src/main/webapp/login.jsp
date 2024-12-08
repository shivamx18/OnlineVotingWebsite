<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <script src="https://kit.fontawesome.com/64d58efce2.js" crossorigin="anonymous"></script>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="login.css" rel="stylesheet">
    <title>Sign in Form</title>
    <style>
        .input-field {
            position: relative;
        }

        .error-messages {
            color: red;
            font-size: 0.8em;
            display: none;
            position: absolute;
            bottom: -20px;
            left: 0;
            width: 100%;
        }

        .error-message {
            display: block; 
        }

        .form-container {
            margin-bottom: 15px;
        }
    </style>
   
</head>
<body>
<div class="container">
    <div class="forms-container">
        <div class="signin-signup">
           
            <form action="LoginServlet" method="POST" class="sign-in-form" id="signin-form"  >
                <h2 class="title"><b>Sign in</b></h2>
                <div class="form-container">
                    <div class="input-field">
                        <i class="fas fa-user"></i>
                        <input type="text" class="form-control" id="username" name="username" placeholder="Username" />
                        
                        <div class="error-messages" id="signin-username-errors">
                            <span class="error-message" id="signin-username-error">Username is required</span>
                        </div>
                    </div>
                </div>
                <div class="form-container">
                    <div class="input-field">
                        <i class="fas fa-lock"></i>
                        <input type="password" class="form-control" id="password" name="password" placeholder="Password" />
                      
                        <div class="error-messages" id="signin-password-errors">
                            <span class="error-message" id="signin-password-error">Password is required</span>
                        </div>
                    </div>
                </div>
                <input type="submit" value="Login" class="btn solid" />
             
                 <c:if test="${not empty errorMessage}">
        			<div class="alert alert-danger" >
            		${errorMessage}
        			</div>
   				 </c:if>
            </form>

            
            <form action="SignupServlet" method="POST" class="sign-up-form" id="signup-form">
                <h2 class="title"><b>Sign up</b></h2>
                <div class="form-container">
                    <div class="input-field">
                        <i class="fas fa-user"></i>
                        <input type="text" id="first_name" name="first_name" placeholder="First Name" required />
                     
                        <div class="error-messages" id="signup-firstname-errors">
                            <span class="error-message" id="signup-firstname-error">First Name is required</span>
                        </div>
                    </div>
                </div>
                <div class="form-container">
                    <div class="input-field">
                        <i class="fas fa-user"></i>
                        <input type="text" id="last_name" name="last_name" placeholder="Last name" required />
                       
                        <div class="error-messages" id="signup-lastname-errors">
                            <span class="error-message" id="signup-lastname-error">Last Name is required</span>
                        </div>
                    </div>
                </div>
                <div class="form-container">
                    <div class="input-field">
                        <i class="fas fa-user"></i>
                        <input type="text" id="username_signup" name="username" placeholder="Username" required />
                        
                        <div class="error-messages" id="signup-username-errors">
                            <span class="error-message" id="signup-username-error">Username is required</span>
                        </div>
                    </div>
                </div>
                <div class="form-container">
                    <div class="input-field">
                        <i class="fas fa-envelope"></i>
                        <input type="email" id="email" name="email" placeholder="Email" required />
                        
                        <div class="error-messages" id="signup-email-errors">
                            <span class="error-message" id="signup-email-error">Email is required</span>
                        </div>
                    </div>
                </div>
                <div class="form-container">
                    <div class="input-field">
                        <i class="fas fa-lock"></i>
                        <input type="password" id="password_signup" name="password" placeholder="Password" required />
                        
                        <div class="error-messages" id="signup-password-errors">
                            <span class="error-message" id="signup-password-error">Password is required (at least 8 characters)</span>
                        </div>
                    </div>
                </div>
                <input type="submit" class="btn" value="Sign up" />
            </form>
        </div>
    </div>

    <div class="panels-container">
        <div class="panel left-panel">
            <div class="content">
                <h3>Register Now</h3>
                <br>
                <h5>Vote Better!<br>Choose Better!!</h5>
                <button class="btn transparent" id="sign-up-btn">Sign up</button>
            </div>
            <img src="log.svg" class="image" alt="" />
        </div>
        <div class="panel right-panel">
            <div class="content">
                <h3>Already registered</h3>
                <br>
                <h5>Vote Better!<br>Choose Better!!</h5>
                <button class="btn transparent" id="sign-in-btn">Sign in</button>
            </div>
            <img src="register.svg" class="image" alt="" />
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>

<script>

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
</script>

</body>
</html>