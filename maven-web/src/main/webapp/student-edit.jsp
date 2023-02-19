<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${ param.id == null ? 'Add New' : 'Edit' } Student</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>

</head>
<body>

	<div class="container pt-4">
		<h1>${ param.id == null ? 'Add New' : 'Edit' } Student</h1>
		
		<div class="mt-4 w-50">
			<c:url value="/student" var="saveUrl"></c:url>
			<form action="${ saveUrl }" method="post">
			
				<input type="hidden" name="id" value="${ param.id }" />
				
				<div class="mb-3">
					<label for="name" class="form-label">Name</label>
					<input type="text" value="${ student.name }" placeholder="Enter Name" name="name" class="form-control" id="name" />
				</div>
	
				<div class="mb-3">
					<label for="phone" class="form-label">Phone</label>
					<input type="tel" value="${ student.phone }" placeholder="Enter Phone Number" name="phone" class="form-control" id="phone" />
				</div>
	
				<div class="mb-3">
					<label for="name" class="form-label">Email</label>
					<input type="email" value="${ student.email }" placeholder="Enter Email Address" name="email" class="form-control" id="email" />
				</div>
				
				<div>
					<button class="btn btn-primary">Save Student</button>
				</div>
			</form>
		</div>
	</div>

</body>
</html>