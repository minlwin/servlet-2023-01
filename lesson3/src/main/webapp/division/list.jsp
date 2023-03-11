<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="/includes/heading.jsp">
	<jsp:param value="Divisions" name="title"/>
</jsp:include>	

</head>
<body>

	<jsp:include page="/includes/navbar.jsp">
		<jsp:param value="division" name="active"/>
	</jsp:include>
	
	<div class="container pt-4">
		<div class="mb-4 d-flex justify-content-between">
			<h3>Division Management</h3>
			
			<div>
				<a href="#" id="uploadBtn" class="btn btn-outline-primary">
					<i class="bi bi-upload"></i> Upload
				</a>
				
				<c:url value="/division-edit" var="addNew"></c:url>
				<a href="${ addNew }" class="btn btn-outline-danger"><i class="bi bi-plus-lg me-2"></i> Add New</a>	
			</div>
		</div>
		
		<c:choose>
			<c:when test="${ empty list }">
				<jsp:include page="/includes/no-data.jsp">
					<jsp:param value="Division" name="name"/>
				</jsp:include>
			</c:when>
			
			<c:otherwise>
				
				<table class="table table-striped">
					
					<tr>
						<th>Id</th>
						<th>Name</th>
						<th>Burmese</th>
						<th>Category</th>
						<th>Capital</th>
						<th></th>
					</tr>
					
					<c:forEach items="${ list }" var="item">
						<tr>
							<td>${ item.id() }</td>
							<td>${ item.name() }</td>
							<td>${ item.burmse() }</td>
							<td>${ item.categoryName() }</td>
							<td>${ item.capital() }</td>
							<td>
								<c:url var="editLink" value="/division-edit">
									<c:param name="id" value="${ item.id() }" />
								</c:url>
							
								<a href="${ editLink }" class="btn-link">
									<i class="bi bi-pencil"></i>
								</a>
							</td>
						</tr>
					</c:forEach>
					
				</table>
			
			</c:otherwise>
		
		</c:choose>
		
		<c:url value="/division-upload" var="uploadLink"></c:url>
		<form enctype="multipart/form-data" id="uploadForm" class="d-none" action="${ uploadLink }" method="post">
			<input name="file" id="uploadInput" type="file" />
		</form>
	
	</div>
	
	<c:url value="/resources/divisions.js" var="scriptSrc"></c:url>
	<script type="text/javascript" src="${ scriptSrc }"></script>

</body>
</html>