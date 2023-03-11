<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Security Demo</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>
</head>
<body>

	<div class="container pt-4">
		<h3>Security Demo</h3>
		
		<c:url value="/admin" var="admin"></c:url>
		<a href="${ admin }" class="btn btn-primary me-2">Admin Home</a>

		<c:url value="/member" var="member"></c:url>
		<a href="${ member }" class="btn btn-danger me-2">Member Home</a>
		
		<c:if test="${ not empty pageContext.request.remoteUser }">
			<c:url value="/logout" var="logout"></c:url>
			<a href="${ logout }" class="btn btn-success">Logout</a>
		</c:if>
	</div>

</body>
</html>