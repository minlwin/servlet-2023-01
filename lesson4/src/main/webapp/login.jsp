<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>
</head>
<body>

	<div class="container pt-4">
		
		<div class="w-25">
			<h3>Member Login</h3>
			
			<c:url value="/j_security_check" var="login"></c:url>
			<form action="${ login }" method="post">
			
				<c:if test="${ not empty params.error }">
					<div class="alert alert-info">
						Please check login information.
					</div>
				</c:if>
				
				<div class="mb-3">
					<label for="loginId" class="form-label">Login Id</label>
					<input type="text" name="j_username" class="form-control" placeholder="Enter Login Id" />
				</div>
				
				<div class="mb-3">
					<label for="password" class="form-label">Password</label>
					<input type="password" name="j_password" class="form-control" placeholder="Enter Password" />
				</div>
				
				<div>
					<c:url value="/index.jsp" var="home"></c:url>
					<a href="${ home }" class="btn btn-primary me-1">Home</a>
					
					<button type="submit" class="btn btn-danger">Login</button>
				</div>

			</form>
		</div>
	
	</div>

</body>
</html>