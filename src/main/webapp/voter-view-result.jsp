<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View Results - Online Voting System</title>
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
        <h1 class="text-center">Voting Results</h1>
                <table class="table table-striped">
	    <thead>
	        <tr>
	            <th>Candidate ID</th>
	            <th>Candidate Name</th>
	            <th>Total Votes</th>
	        </tr>
	    </thead>
	    <tbody>
	        <c:forEach var="candidate" items="${candidates}" varStatus="status">
	            <tr>
	                <td>${candidate.candidateId}</td> 
	                <td>${candidate.firstname} ${candidate.lastname}</td> 
	                
	                
	                <td>
	                    <c:choose>
	                        <c:when test="${not empty total_vote}">
	                            ${total_vote[status.index].totalVotes}
	                        </c:when>
	                        <c:otherwise>
	                            0
	                        </c:otherwise>
	                    </c:choose>
	                </td>
	            </tr>
	        </c:forEach>
	    </tbody>
</table>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
</body>

</html>
