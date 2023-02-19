<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${ param['id'] eq null ? 'Add New' : 'Edit' } Student</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>

</head>
<body>

	<div class="container pt-4">
		
		<h3>${ param['id'] eq null ? 'Add New' : 'Edit' } Student</h3>
		
		<c:url var="saveLink" value="/students"></c:url>
		<form action="${ saveLink }" method="post" class="w-50 mt-4">
		
			<input type="hidden" name="id" value="${param.id}" />
			
			<!-- Name -->
			<div class="form-group mb-3">
				<label class="form-label">Student Name</label>
				<input type="text" value="${ student.name }" name="name" placeholder="Enter Student Name" class="form-control" />
			</div>
			
			<!-- Phone -->
			<div class="form-group mb-3">
				<label class="form-label">Phone</label>
				<input type="tel" value="${ student.phone }" name="phone" placeholder="Enter Phone Number" class="form-control" />
			</div>
			
			<!-- Email -->
			<div class="form-group mb-3">
				<label class="form-label">Email</label>
				<input type="email" value="${ student.email }" name="email" placeholder="Enter Email" class="form-control" />
			</div>
			
			<!-- Save Button -->
			<button class="btn btn-primary" type="submit">Save Student</button>
		
		</form>
	
	</div>

</body>
</html>