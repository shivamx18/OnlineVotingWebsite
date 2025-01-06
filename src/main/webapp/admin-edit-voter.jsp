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
    <title>Edit Voter - Online Voting System</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet" crossorigin="anonymous">
</head>
<body>
    
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container">
            <a class="navbar-brand" >Online Voting System</a>
        </div>
    </nav>

    <div class="container mt-5">
        <h1 class="text-center">Edit Voter</h1>

        <c:if test="${not empty sessionScope.errorMessage}">
    	<div class="alert alert-info">
        	<p>${sessionScope.errorMessage}</p>
    	</div>
		</c:if>

        <c:if test="${not empty sessionScope.successMessage}">
    	<div class="alert alert-info">
        	<p>${sessionScope.successMessage}</p>
    	</div>
		</c:if>

        <form action="VoterServlet?action=update" method="post" class="mt-4">
        
        	<label for="username">Voter ID</label>   
             <c:if test="${not empty voter.voterId}">
    			<input type="text" class="form-control" id="voterId" name="voterId" value="${voter.voterId}" required readonly>
			</c:if>

            <div class="form-group">
                <label for="username">UserName</label>
                <input type="text" class="form-control" id="username" name="username" value="${voter.username}" required>
            </div>
			<c:if test="${not empty error1}">
			   <div class="alert alert-danger">${error1}</div>
			</c:if>
              
            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" class="form-control" id="password" name="password" value="${voter.password}" required>
            </div>

            <div class="form-group">
                <label for="first_name">First Name</label>
                <input type="text" class="form-control" id="first_name" name="first_name" value="${voter.firstname}" required>
            </div>

            <div class="form-group">
                <label for="last_name">Last Name</label>
                <input type="text" class="form-control" id="last_name" name="last_name" value="${voter.lastname}" required>
            </div>

            <div class="form-group">
                <label for="email">Email</label>
                <input type="email" class="form-control" id="email" name="email" value="${voter.email}" required>
            </div>
			<c:if test="${not empty error2}">
			   <div class="alert alert-danger">${error2}</div>
			</c:if>
            <button type="submit" class="btn btn-primary">Update Voter</button>

            <a href="VoterServlet" class="btn btn-secondary">Cancel</a>
        </form>
        
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
</body>
</html>
