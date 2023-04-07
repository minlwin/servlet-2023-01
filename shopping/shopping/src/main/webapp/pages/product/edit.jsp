<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Shop | Product Edit</title>

<jsp:include page="/includes/bootstrap.jsp"></jsp:include>
</head>
<body>

	<nav>
		<!-- Menu Bar -->
		<jsp:include page="/includes/menu-bar.jsp"></jsp:include>
	
	</nav>
	
	
	<!-- Content -->
	<main class="container pt-3">
		
		<form method="post">
			
			<input type="hidden" name="id" value="${empty dto ? 0 : dto.product.id}" />
			
			<h3 class="mb-3"><i class="bi bi-gift"></i> ${empty param.id ? 'Add New' : 'Edit'} Product</h3>
			
			<div class="row row-cols-lg-3 row-cols-md-1">
				<!-- Name -->
				<div class="col mb-md-2">
					<label class="form-label">Product Name</label>
					<input name="productName" value="${empty dto ? '' : dto.product.name}" type="text" placeholder="Enter Product Name" required="required" class="form-control" />
				</div>
				
				<!-- Brand -->
				<div class="col mb-md-2">
					<label class="form-label">Brand Name</label>
					<input name="brandName" value="${empty dto ? '' : dto.product.brand}" type="text" placeholder="Enter Brand Name" required="required" class="form-control" />
				</div>
				
				<!-- Price -->
				<div class="col mb-md-2">
					<label class="form-label">Price</label>
					<input name="price" type="number" value="${empty dto ? '' : dto.product.price}" placeholder="Enter Price" required="required" class="form-control" />
				</div>
			
			</div>
			
			<!-- Multi Select Check Box Category -->
			<h4 class="mt-4">Select Category</h4>
			<div class="row row-cols-lg-3 row-cols-md-1">
				<c:forEach items="${categories}" var="item">
					<div class="col">
						<div class="form-check">
							<input name="category" class="form-check-input" type="checkbox" ${item.check ? 'checked="checked"' : ''} value="${item.id}" id="${item.name}" />
							<label for="${item.name}" class="form-chcek-label">${item.name}</label>
						</div>
					</div>
				</c:forEach>
			</div>
			
			
			<!-- Dynamic Inputs For Features -->
			<h4 class="mt-4">Add Features</h4>
			<fieldset id="featureInputs">
				
				<div class="row">
					<div class="col-lg-4 col-md-2">
						<label class="form-label">Name</label>
					</div>
					
					<div class="col">
						<label class="form-label">Value</label>
					</div>
				</div>
				<c:choose>
					<c:when test="${empty dto}">
						<div class="row">
							<div class="col-lg-4">
								<input name="featureName" required="required" type="text" placeholder="Enter Feature Name" class="form-control" />
							</div>
							
							<div class="col">
								<input name="featureValue" required="required" type="text" placeholder="Enter Feature Value" class="form-control" />
							</div>

							<div class="col-auto">
								<button type="button" class="btn btn-outline-danger delete-feature">
									<i class="bi bi-trash"></i>
								</button>
							</div>
						</div>
					</c:when>
					
					<c:otherwise>
					
						<c:forEach items="${dto.features}" var="item" varStatus="status">
							<div class="row ${status.index gt 0 ? 'mt-2' : ''}">
								<div class="col-lg-4">
									<input name="featureName" value="${item.name}" required="required" type="text" placeholder="Enter Feature Name" class="form-control" />
								</div>
								
								<div class="col">
									<input name="featureValue" value="${item.value}" required="required" type="text" placeholder="Enter Feature Value" class="form-control" />
								</div>
								
								<div class="col-auto">
									<button type="button" class="btn btn-outline-danger delete-feature">
										<i class="bi bi-trash"></i>
									</button>
								</div>
							</div>
						</c:forEach>
					
					</c:otherwise>
				</c:choose>
			
			</fieldset>
			
			<!-- Description -->
			<div class="mt-4">
				<label class="form-label">Description</label>
				<textarea name="description" rows="3" cols="20" class="form-control"></textarea>
			</div>
			
			<!-- Buttons -->	
			<div class="mt-4">
				<button id="addFeatrueBtn" type="button" class="btn btn-primary me-2">
					<i class="bi bi-plus-lg"></i> Add Feature
				</button>
				
				<button type="submit" class="btn btn-danger">
					<i class="bi bi-save"></i> Save Product
				</button>
			</div>		
			
		</form>
	</main>
	
	<c:url value="/resources/js/product-edit.js" var="productEditJs"></c:url>
	<script src="${productEditJs}"></script>
	
</body>
</html>