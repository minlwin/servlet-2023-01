<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>

</head>
<body>

	<div class="container pt-4">
		<h3>Student Details</h3>
		
		<div class="mt-4 w-50">
			<div class="mb-3">
				<span class="form-label">Student ID</span>
				<span class="form-control">${ student.id }</span>
			</div>
			<div class="mb-3">
				<span class="form-label">Student Name</span>
				<span class="form-control">${ student.name }</span>
			</div>
			<div class="mb-3">
				<span class="form-label">Phone Number</span>
				<span class="form-control">${ student.phone }</span>
			</div>
			<div class="mb-3">
				<span class="form-label">Email</span>
				<span class="form-control">${ student.email }</span>
			</div>
			<div>
				<c:url value="/student" var="list" />
				<a href="${ list }" class="btn btn-success me-2">Student List</a>
				
				<c:url value="/student-delete" var="delete">
					<c:param name="id" value="${ student.id }" />
				</c:url>
				<a href="${ delete }" class="btn btn-danger me-2">Delete Student</a>

				<c:url value="/student-edit" var="edit">
					<c:param name="id" value="${ student.id }" />
				</c:url>
 				<a href="${ edit }" class="btn btn-primary">Edit Student</a>
			</div>
		</div>
	</div>

</body>
</html>