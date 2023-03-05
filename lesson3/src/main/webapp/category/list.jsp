<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="/includes/heading.jsp">
	<jsp:param value="Categories" name="title"/>
</jsp:include>	
</head>
<body>

	<jsp:include page="/includes/navbar.jsp">
		<jsp:param value="category" name="active"/>
	</jsp:include>
	
	<div class="container pt-4">
		
		<div class="mb-4 d-flex justify-content-between">
			<h3>Category Management</h3>
			<c:url value="/category-edit" var="addNew"></c:url>
			<a href="${ addNew }" class="btn btn-outline-danger"><i class="bi bi-plus-lg me-2"></i> Add New</a>	
		</div>
		
		<c:choose>
			<c:when test="${ empty list }">
				<jsp:include page="/includes/no-data.jsp">
					<jsp:param value="Category" name="name"/>
				</jsp:include>
			</c:when>
			
			<c:otherwise>
				<jsp:include page="/includes/categories.jsp"></jsp:include>
			</c:otherwise>
		
		</c:choose>
	
	</div>

</body>
</html>