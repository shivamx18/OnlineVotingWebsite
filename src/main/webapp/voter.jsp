<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View Voter - Online Voting System</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet" crossorigin="anonymous">
</head>

<body>

   <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container">
            <a class="navbar-brand" >Online Voting System</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="VoterServlet?action=edit&voter_id=${voter.voterId}">Edit Profile</a>
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
        <h1 class="text-center">Voter Details</h1>
        <div class="card">
            <div class="card-body">
                <h5 class="card-title">Personal Information</h5>
               <c:if test="${not empty voter}">
    				<p>Voter ID: ${voter.voterId}</p>
    				<p>First Name: ${voter.firstname}</p>
    				<p>Last Name: ${voter.lastname}</p>
    				<p>UserName: ${voter.username}</p>
    				<p>Email: ${voter.email}</p>
				</c:if>	
				<c:if test="${empty voter}">
    				<p>No voter data available.</p>
				</c:if>

            </div>
        </div>

        <div class="mt-3">
            <a href="VoteServlet" class="btn btn-warning">Vote</a>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
</body>

</html>
