<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Candidate - Online Voting System</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet" crossorigin="anonymous">
</head>
<body>
   
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container">
            <a class="navbar-brand" href="dashboard.jsp">Online Voting System</a>
        </div>
    </nav>

    <div class="container mt-5">
        <h1 class="text-center">Edit Candidate</h1>
             
        <form action="CandidateServlet?action=update" method="post" class="mt-4">
           
             <c:if test="${not empty candidate.candidateId}">
    			<input type="text" class="form-control" id="candidateId" name="candidateId" value="${candidate.candidateId}" required readonly>
			</c:if>

            <div class="form-group">
                <label for="first_name">First Name</label>
                <input type="text" class="form-control" id="first_name" name="first_name" value="${candidate.firstname}" required>
            </div>

            <div class="form-group">
                <label for="last_name">Last Name</label>
                <input type="text" class="form-control" id="last_name" name="last_name" value="${candidate.lastname}" required>
            </div>

            <div class="form-group">
                <label for="email">Email</label>
                <input type="email" class="form-control" id="email" name="email" value="${candidate.email}" required>
            </div>
            <c:if test="${not empty error}">
    			<div class="alert alert-danger">${error}</div>
			</c:if>

            <button type="submit" class="btn btn-primary">Update Candidate</button>

            <a href="CandidateServlet" class="btn btn-secondary">Cancel</a>
        </form>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
</body>
</html>
