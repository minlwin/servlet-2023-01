<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>    
<%@ taglib prefix="app" tagdir="/WEB-INF/tags" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Shop | My Cart</title>

<jsp:include page="/includes/bootstrap.jsp"></jsp:include>

</head>
<body>

	<nav>
		<!-- Menu Bar -->
		<jsp:include page="/includes/menu-bar.jsp"></jsp:include>
	
	</nav>
	
	
	<!-- Content -->
	<main class="container pt-3">
		<!-- Title -->
		<h3><i class="bi bi-cart-check"></i> My Cart</h3>
		<h5><i class="bi bi-pin-map-fill"></i> Shipping Address</h5>
		
		<div class="row mt-4">
			<div class="col-8">
				<!-- Cart Contents -->
				<div class="mb-4">
					<app:cart-contents items="${cart.items}"></app:cart-contents>
				</div>
			
			</div>
			
			<div class="col">
			
				<app:card>
					<h4 class="card-title">
						<i class="bi bi-pin-map"></i> Shipping Address
					</h4>
					<!-- Address Form -->
					<c:url value="/customer/cart/shipping" var="shippingFormAction"></c:url>
					<form action="${shippingFormAction}" method="post">
						<!-- Id -->
						<c:if test="${not empty addresses}">
							<div class="mb-3">
								<label class="form-label">Select shipping address</label>
								<select name="id" class="form-select">
									<option value="">Primary Address</option>
								</select>
							</div>
						</c:if>
						<div class="mb-3">
							<!-- Name -->
							<label class="form-label">Customer Name</label>
							<input name="name" type="text" value="${login.name}" class="form-control" placeholder="Enter Customer Name" required="required" />
						</div>
						
						<div class="mb-3">
							<!-- Phone -->
							<label class="form-label">Contact Phone Number</label>
							<input name="phone" type="tel" class="form-control" placeholder="Enter Phone Number" required="required" />
						</div>			
	
						<div class="mb-3">
							<!-- Building -->
							<label class="form-label">Building</label>
							<input name="building" type="text" class="form-control" placeholder="Enter Address" required="required" />
						</div>		
						
						<div class="mb-3">
							<!-- Street -->
							<label class="form-label">Street Address</label>
							<input name="street" type="text" class="form-control" placeholder="Enter Street and Township" required="required" />
						</div>	
						
						<!-- Button -->
						<div class="mt-3">
							<c:if test="${not empty addresses}">
								<button type="button" class="btn btn-outline-primary me-2">
									<i class="bi bi-plus-lg"></i> Add New Address
								</button>	
							</c:if>	
							<button type="submit" class="btn btn-outline-primary">
								<i class="bi bi-check-lg"></i> Save
							</button>
						</div>
					</form>					
				</app:card>			
			
			</div>
		</div>		
	</main>	
	
</body>
</html>