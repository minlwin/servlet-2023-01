<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>    
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
		
		<h3 class="mb-3">
			<i class="bi bi-gift"></i> ${dto.product.name}
 		</h3>
 		
 		<!-- Category -->
 		<div class="row row-cols-4 mb-4">
 			<c:forEach items="${dto.categories}" var="item">
 				<div class="col">
					<h5 class="card-title">
						<i class="bi bi-tag"></i> ${item.name}
					</h5>
 				</div>
 			</c:forEach>
 		</div>
 		
 		<div class="row">
			
			<div class="col">
				<!-- Images -->
				<c:choose>
					<c:when test="${not empty dto.photos}">
						
						<div class="row">
							<div class="col-auto">
								<div class="list d-flex flex-column">
									<c:forEach items="${dto.photos}" var="photo">
										<div class="image-control">
											<c:url value="/resources/photos/${photo.photo}" var="photoUrl"></c:url>
											<img class="img-thumbnail img-fluid" src="${photoUrl}" alt="Product Photo" />
										</div>
									</c:forEach>
								</div>
							</div>
							
							<div class="col">
								<div class="cover">
									<c:url value="/resources/photos/${cover}" var="coverUrl"></c:url>
									<img id="productCoverImage" class="img-thumbnail img-fluid cover-image" alt="Cover Image" src="${coverUrl}">
								</div>
							</div>
						</div>
					
						
					
					</c:when>
					
					<c:otherwise>
						<div class="img img-thumbnail">
							<c:url value="/resources/photos/product-image.png" var="defaultImage"></c:url>
							<img class="productImage" alt="Product Image" src="${defaultImage}">							
						</div>
					</c:otherwise>
				</c:choose>
			
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
		 			<button id="uploadBtn" class="btn btn-success me-1">
		 				<i class="bi bi-camera"></i> Upload Images
		 			</button>
		 			<!-- Edit -->
		 			<c:url value="/sale/product/edit" var="editLink">
		 				<c:param name="id" value="${dto.product.id}"></c:param>
		 			</c:url>
		 			<a href="${editLink}" class="btn btn-primary me-1">
		 				<i class="bi bi-pencil"></i> Edit
		 			</a>
		 			<!-- Set Sold Out -->
		 			<button class="btn btn-danger">
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
 		
 		<c:url value="/resources/product-details-admin.js" var="script"></c:url>
 		<script src="${script}"></script>

	</main>
	
</body>
</html>