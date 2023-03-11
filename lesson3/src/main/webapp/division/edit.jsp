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
			<h3>${ empty param.id ? "Add New" : "Edit" } Division</h3>
		</div>
		
		<form method="post" class="w-50">
			
			<!-- ID -->
			<c:if test="${ not empty param.id }">
				<input type="hidden" name="id" value="${ param.id }" />
				<div class="form-group mb-4">
					<label for="id" class="form-label">Category Id</label>
					<span class="form-control">${ param.id }</span>
				</div>
			</c:if>
			
			<!-- Category -->
			<div class="form-group mb-4">
				<label class="form-label">Select Category</label>
				<select name="category" class="form-select">
					<option value="">Select One</option>
					<c:forEach items="${ categories }" var="category">
						<option value="${ category.id() }" ${ category.id() eq form.categoryId() ? 'selected' : '' }>${ category.name() }</option>
					</c:forEach>
				</select>
			</div>
			
			<!-- Name -->
			<div class="form-group mb-4">
				<label class="form-label">Division Name</label>
				<input type="text" value="${ form.name() }" required="required"  name="name" class="form-control" placeholder="Enter Division Name" />
			</div>
					
			<!-- Burmese -->
			<div class="form-group mb-4">
				<label class="form-label">Burmese Name</label>
				<input type="text" value="${ form.burmese() }" required="required" name="burmese" class="form-control" placeholder="Enter Burmese Name" />
			</div>
			
			<!-- Capital -->
			<div class="form-group mb-4">
				<label class="form-label">Capital Name</label>
				<input type="text" value="${ form.capital() }" required="required" name="capital" class="form-control" placeholder="Enter Capital Name" />
			</div>

			<button class="btn btn-outline-danger">
				<i class="bi bi-save me-2"></i> Save
			</button>
			
		</form>
	
	</div>

</body>
</html>