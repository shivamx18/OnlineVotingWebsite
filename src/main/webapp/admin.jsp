<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard - Online Voting System</title>

  
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
        <h1 class="text-center">Welcome, ${sessionScope.admin.username}</h1>

        
        <div class="row mt-4">
            <div class="col-md-4 mb-4">
                <div class="card text-center">
                    <div class="card-body">
                        <h5 class="card-title">Total Candidates</h5>
                        <p class="card-text">${totalCandidates}</p>
                    </div>
                </div>
            </div>
            <div class="col-md-4 mb-4">
                <div class="card text-center">
                    <div class="card-body">
                        <h5 class="card-title">Total Voters</h5>
                        <p class="card-text">${totalVoters}</p>
                    </div>
                </div>
            </div>
            <div class="col-md-4 mb-4">
                <div class="card text-center">
                    <div class="card-body">
                        <h5 class="card-title">Total Votes Cast</h5>
                        <p class="card-text">${totalVotes}</p>
                    </div>
                </div>
            </div>
        </div>

        
        <div class="mt-5">
            <h3 class="text-center">Quick Actions</h3>
            <div class="list-group">
                <a href="CandidateServlet?action=add" class="list-group-item list-group-item-action btn btn-primary">Add New Candidate</a>
                <a href="VoterServlet?action=add" class="list-group-item list-group-item-action btn btn-primary">Add New Voter</a>
                <a href="ResultServlet?action=view" class="list-group-item list-group-item-action btn btn-success">View Voting Results</a>
            </div>
        </div>

    </div>

    
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>

</body>

</html>

