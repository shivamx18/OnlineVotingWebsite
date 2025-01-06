<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<%
   
    if (session.getAttribute("userRole") == null || !session.getAttribute("userRole").equals("admin")) {
       
        response.sendRedirect("login.jsp");
        return; 
    }
%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Voter - Online Voting System</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet" crossorigin="anonymous">
</head>

<body>
   
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container">
            <a class="navbar-brand">Online Voting System</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="CandidateServlet">Manage Candidates</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="VoterServlet">Manage Voters</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="ResultServlet">View Results</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="LogoutServlet">Logout</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

    <div class="container mt-5">
        <h1 class="text-center">Add New Voter</h1>
        <form action="VoterServlet?action=add" method="post">
            <div class="mb-3">
                <label for="first_name" class="form-label"><i class="fas fa-user"></i> First Name</label>
                <input type="text" class="form-control" id="first_name" name="first_name" placeholder="Enter Voter first name">
            </div>
            <div class="mb-3">
                <label for="last_name" class="form-label"><i class="fas fa-user"></i> Last Name</label>
                <input type="text" class="form-control" id="last_name" name="last_name" placeholder="Enter Voter last name">
            </div>
            <div class="mb-3">
                <label for="username_signup" class="form-label"><i class="fas fa-user"></i> Username</label>
                <input type="text" class="form-control" id="username_signup" name="username" placeholder="Enter Voter username">
            </div>
            <div class="mb-3">
                <label for="email" class="form-label"><i class="fas fa-envelope"></i> Email</label>
                <input type="email" class="form-control" id="email" name="email" placeholder="Enter Voter email">
            </div>
            <div class="mb-3">
                <label for="password_signup" class="form-label"><i class="fas fa-lock"></i> Password</label>
                <input type="password" class="form-control" id="password_signup" name="password" placeholder="Create a default password">
            </div>
            <div class="d-flex justify-content-between w-100">
    			<button type="submit" class="btn btn-primary">Add Voter</button>
             	<a href="VoterServlet" class="btn btn-secondary mt-3">Back to Voter List</a>
			</div>
            
        </form>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
</body>

</html>
