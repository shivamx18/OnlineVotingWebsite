<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Candidate View</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
 <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container">
            <a class="navbar-brand" >Online Voting System</a>
        </div>
    </nav>

    <div class="container mt-5">
        <h1 class="text-center">Candidate Details</h1>
        <div class="card">
            <div class="card-body">
                <h5 class="card-title">Personal Information</h5>
               <c:if test="${not empty candidate}">
    				<p>Voter ID: ${candidate.candidateId}</p>
    				<p>First Name: ${candidate.firstname}</p>
    				<p>Last Name: ${candidate.lastname}</p>
       				<p>Email: ${candidate.email}</p>
				</c:if>	
				<c:if test="${empty candidate}">
    				<p>No voter data available.</p>
				</c:if>

            </div>
        </div>

        <div class="mt-3">
           
            <a href="CandidateServlet" class="btn btn-secondary">Back to Candidate List</a>
            <a href="CandidateServlet?action=edit&candidate_id=${candidate.candidateId}" class="btn btn-warning">Edit Candidate</a>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
</body>
</html>
