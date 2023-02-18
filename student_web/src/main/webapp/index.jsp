<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Students</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>

</head>
<body>

	<div class="container pt-4">
		<h3>Student List</h3>
		
		<!-- Search Form -->
		<c:url var="searchUrl" value="/students"></c:url>
		<form action="${ searchUrl }" class="row my-4">
			<div class="col-auto">
				<label>Search Name</label>
			</div>
			<div class="col-4">
				<input name="searchName" type="text" value="${ param.searchName }" class="form-control" placeholder="Search Name" />
			</div>
			<div class="col">
				<button type="submit" class="btn btn-primary">Search</button>
				
				<c:url var="addNewLink" value="/student-edit"></c:url>
				<a href="${ addNewLink }" class="btn btn-danger">Add New</a>
			</div>
		</form>
		
		<!-- Search Result -->
		<c:choose>
			
			<c:when test="${ not empty students }">
				<table class="table table-striped">
					<thead>
						<tr>
							<th>Id</th>
							<th>Name</th>
							<th>Phone</th>
							<th>Email</th>
							<th>Show Details</th>
						</tr>
					</thead>
					
					<tbody>
						<c:forEach var="s" items="${ students }">
							<tr>
								<td>${ s.id }</td>
								<td>${ s.name }</td>
								<td>${ s.phone }</td>
								<td>${ s.email }</td>
								<td>
									<c:url var="detailsLink" value="/student-details">
										<c:param name="id" value="${ s.id }"></c:param>
									</c:url>
									<a href="${ detailsLink }">Details</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:when>
			
			<c:otherwise>
				<div class="alert alert-info">
					There is no Students.
				</div>
			</c:otherwise>
		</c:choose>
	</div>

</body>
</html>