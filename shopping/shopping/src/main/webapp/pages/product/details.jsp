<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>  

<%@ taglib prefix="custom" tagdir="/WEB-INF/tags" %>
  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Shop | Product</title>

<jsp:include page="/includes/bootstrap.jsp"></jsp:include>
</head>
<body>

	<nav>
		<!-- Menu Bar -->
		<jsp:include page="/includes/menu-bar.jsp"></jsp:include>
	
	</nav>
	
	
	<!-- Content -->
	<main class="container pt-3">
		
		<h3>
			<i class="bi bi-gift"></i> ${dto.product.name}
 		</h3>
 		
 		<!-- Category -->
 		<div class="d-flex mb-3">
 			<c:forEach items="${dto.categories}" var="item">
				<h5 class="me-4">
					<i class="bi bi-tag"></i> ${item.name}
				</h5>
 			</c:forEach>
 		</div>
 		
 		<div class="row">
			
			<div class="col">
				<custom:product-image photos="${dto.photos}" cover="${dto.coverImage}"></custom:product-image>
			</div>
			
			<div class="col features">
			
				<h5>Product Details</h5>
			
				<!-- Brand -->
				<div class="row">
					<div class="col-4">
						<label>Brand</label>
					</div>
					
					<div class="col">
						<span>${dto.product.brand}</span>
					</div>
				</div>
				
				<!-- Name -->
				<div class="row">
					<div class="col-4">
						<label>Product Name</label>
					</div>
					
					<div class="col">
						<span>${dto.product.name}</span>
					</div>
				
				</div>				
				
				<!-- Price -->
				<div class="row">
					<div class="col-4">
						<label>Price</label>
					</div>
					
					<div class="col-4">
						<span>${dto.product.price}</span>
					</div>
				
				</div>
				
		 		<!-- Features -->
		 		<c:forEach var="item" items="${dto.features}">
					<div class="row">
						<div class="col-4">
							<label>${item.name}</label>
						</div>
						
						<div class="col">
							<span>${item.value}</span>
						</div>
					
					</div>
		 		</c:forEach>
		 		
		 		<!-- Description -->
		 		<c:if test="${not empty dto.product.description}">
		 			<h6>Description</h6>
		 			<p>${dto.product.description}</p>
		 		</c:if>
		 		
		 		<div class="mt-4">
		 			<!-- Upload Photo -->
		 			<button id="uploadBtn" class="btn btn-outline-success me-1">
		 				<i class="bi bi-camera"></i> Upload Images
		 			</button>
		 			
		 			<c:if test="${ not empty dto.photos }">
			 			<!-- Delete Current Image -->
			 			<button id="deleteImageBtn" class="btn btn-outline-success me-1">
			 				<i class="bi bi-trash2"></i> Delete Photo
			 			</button>
			 			
			 			<!-- Set Cover Photo -->
			 			<button id="setCoverBtn" class="btn btn-outline-success me-1">
			 				<i class="bi bi-image"></i> Set Cover
			 			</button>
		 			
		 			</c:if>
		 		
		 		</div>
		 		
			
		 		<div class="mt-3">
		 			<!-- Edit -->
		 			<c:url value="/sale/product/edit" var="editLink">
		 				<c:param name="id" value="${dto.product.id}"></c:param>
		 			</c:url>
		 			<a href="${editLink}" class="btn btn-outline-primary me-1">
		 				<i class="bi bi-pencil"></i> Edit
		 			</a>
		 			<!-- Set Sold Out -->
		 			<button id="soldOutBtn" class="btn btn-outline-danger">
		 				<i class="bi ${dto.product.soldOut ? 'bi-check' : 'bi-x-lg'}"></i> Set ${dto.product.soldOut ? 'In House' : 'Sold Out'}
		 			</button>
		 		</div>
			</div> 		
 		
 		</div>
 		
 		<c:url value="/sale/product/photo" var="photoUploadAction"></c:url>
 		<form id="uploadForm" enctype="multipart/form-data" action="${photoUploadAction}" class="d-none" method="post">
 			<input type="hidden" name="id" value="${dto.product.id}" />
 			<input type="file" multiple="multiple" name="image" id="uploadInput" />
 		</form>
 		
 		<c:url value="/sale/product/soldout" var="soldOutAction"></c:url>
 		<form action="${soldOutAction}" class="d-none" method="post" id="soldOutForm">
 			<input type="hidden" name="id" value="${dto.product.id}" />
			<input type="hidden" name="soldOut" value="${!dto.product.soldOut}"> 		
 		</form>
 		
 		<c:url value="/sale/product/cover" var="setCoverUrl"></c:url>
 		<form id="setCoverForm" action="${setCoverUrl}" method="post" class="d-none">
 			<input type="hidden" name="id" value="${dto.product.id}" />
			<input id="setCoverInput" type="hidden" name="cover" value="${cover}">		
 			<input id="deleteImageInput" type="hidden" name="delete" value="false" />
 		</form>
 		
 		<c:url value="/resources/product-details-admin.js" var="script"></c:url>
 		<script src="${script}"></script>

	</main>
	
</body>
</html>