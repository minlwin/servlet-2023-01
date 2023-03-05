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
			<h3>${ not empty param.id ? 'Edit' : 'Add New' } Category</h3>
		</div>
		
		<form method="post" class="w-50">
		
			<c:if test="${ not empty param.id }">
				<input type="hidden" name="id" value="${ param.id }" />
				<div class="form-group mb-4">
					<label for="id" class="form-label">Category Id</label>
					<span class="form-control">${ param.id }</span>
				</div>
			</c:if>
			
			<div class="form-group mb-4">
				<label class="form-label">Category Name</label>
				<input type="text" required="required" name="name" class="form-control" placeholder="Enter Category Name" />
			</div>
		
			<div class="form-group mb-4">
				<label class="form-label">Burmese Name</label>
				<input type="text" required="required" name="burmese" class="form-control" placeholder="Enter Burmese Name" />
			</div>
			
			<button class="btn btn-outline-danger">
				<i class="bi bi-save me-2"></i> Save
			</button>
		</form>
	</div>

</body>
</html>