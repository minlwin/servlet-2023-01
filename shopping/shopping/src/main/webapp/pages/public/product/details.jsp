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
		
		<h3 class="mb-3">
			<i class="bi bi-gift"></i> ${dto.product.name}
 		</h3>
 		
 		<!-- Category -->
 		<div class="row row-cols-4 mb-4">
 			<c:forEach items="${dto.categories}" var="item">
 				<div class="col">
					<h5>
						<i class="bi bi-tag"></i> ${item.name}
					</h5>
 				</div>
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
		 			<!-- Add To Cart -->
		 			<c:url value="/cart/add" var="addToCartLink">
		 				<c:param name="id" value="${dto.product.id}"></c:param>
		 			</c:url>
		 			<a href="${addToCartLink}" class="btn btn-outline-primary me-1">
		 				<i class="bi bi-cart-plus"></i> Add To Cart
		 			</a>
		 		</div>
			
			</div> 		
 		
 		</div>
 		
 		<c:url value="/resources/product-details-public.js" var="script"></c:url>
 		<script src="${script}"></script>
	</main>
	
</body>
</html>