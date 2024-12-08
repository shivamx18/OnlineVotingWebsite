<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Vote - Online Voting System</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet" crossorigin="anonymous">
</head>

<body>

    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container">
            <a class="navbar-brand" href="dashboard.jsp">Online Voting System</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ml-auto">
                   <li class="nav-item">
					    <c:if test="${not empty sessionScope.voterId}">
					        <a class="nav-link" href="VoterServlet?action=view&voter_id=${sessionScope.voterId}">Home</a>
					    </c:if>
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
        <h1 class="text-center">Vote for Your Candidate</h1>

        <form action="VoteServlet?action=vote" method="post">
            <input type="hidden" name="action" value="vote">

            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Candidate ID</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Select</th>
                    </tr>
                </thead>
                <tbody>
                    <!-- Loop through the list of candidates and display each record -->
                    <c:forEach var="candidate" items="${candidate}">
                        <tr>
                            <td>${candidate.candidateId}</td>
                            <td>${candidate.firstname}</td>
                            <td>${candidate.lastname}</td>
                            <td>
                                <input type="radio" name="candidate_id" value="${candidate.candidateId}" required>
                                <!-- Ensure that only one candidate can be selected -->
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <button type="submit" class="btn btn-primary mt-3">Submit Vote</button>
             <c:if test="${not empty message}">
             
            <div class="alert alert-info">
                <p>${message}</p>
                
            </div>
        
        </c:if>
        </form>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
</body>

</html>
