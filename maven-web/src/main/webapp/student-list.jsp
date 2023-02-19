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
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">
</head>
<body>

	<div class="container pt-4">
		<h3>Student List</h3>
		
		<c:url value="/student" var="search" />
		<form action="${ search }" class="row my-4">
			<div class="col-auto">
				<label>Search Name</label>
			</div>
			<div class="col-4">
				<input type="text" class="form-control" name="name" placeholder="Search Name" value="${ param.name }" />
			</div>
			<div class="col">
				<button class="btn btn-primary me-2">Search</button>
				<c:url value="/student-edit" var="edit" />
				<a href="${ edit }" class="btn btn-danger">Add New Student</a>
			</div>
		</form>

		<c:choose>
			
			<c:when test="${ not empty list }">
				<table class="table table-striped table-hover">
					<tr>
						<th>Id</th>
						<th>Name</th>
						<th>Phone</th>
						<th>Email</th>
						<th></th>
					</tr>
					
					<c:forEach var="s" items="${ list }">
						<tr>
							<td>${ s.id }</td>
							<td>${ s.name }</td>
							<td>${ s.phone }</td>
							<td>${ s.email }</td>
							<td>
								<c:url value="/student-details" var="show">
									<c:param name="id" value="${ s.id }" />
								</c:url>
								<a href="${ show }" class="btn btn-link">
									<i class="bi bi-send"></i>
								</a>
							</td>
						</tr>
					</c:forEach>
				</table>
			</c:when>
			
			<c:otherwise>
				<div class="alert alert-info">There is no students.</div>
			</c:otherwise>
		
		</c:choose>
	</div>
	


</body>
</html>