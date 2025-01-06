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
    <title>Manage Candidates - Online Voting System</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet" crossorigin="anonymous">
</head>

<body>
    
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container">
            <a class="navbar-brand" >Online Voting System</a>
        </div>
        <div class="container">
            <a class="navbar-brand" href="AdminServlet"> Home</a>
        </div>
    </nav>

    <div class="container mt-5">
        <h1 class="text-center">Candidate List</h1>

        <div class="mb-3">
            <a href="add-candidate.jsp" class="btn btn-primary">Add New Candidate</a>
        </div>

        <table class="table table-striped">
            <thead>
                <tr>
               	 	<th>Candidate ID</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Email</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                
                <c:forEach var="candidate" items="${candidates}">
                    <tr>
                    	<td>${candidate.candidateId}</td>
                    	<td>${candidate.firstname}</td>
                    	<td>${candidate.lastname}</td>
                        <td>${candidate.email}</td>
                        <td>
                           
                           <c:if test="${not empty candidate.candidateId}">
    						<a href="CandidateServlet?action=view&candidate_id=${candidate.candidateId}" class="btn btn-info">View</a>
							</c:if> 
                            <a href="CandidateServlet?action=edit&candidate_id=${candidate.candidateId}" class="btn btn-warning">Edit</a>
                       
                            <a href="CandidateServlet?action=delete&candidate_id=${candidate.candidateId}" class="btn btn-danger" onclick="return confirm('Are you sure you want to delete this voter?')">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <a href="AdminServlet" class="btn btn-secondary">Back to Dashboard</a>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
</body>

</html>
