<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>  
<%@ taglib prefix="app" tagdir="/WEB-INF/tags" %>
  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Shop | Paid Infos</title>

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
		<h3 class="mb-3"><i class="bi bi-cart-check"></i> Order Details</h3>
		
		<div class="row">
			<div class="col-8">
				<!-- Order Summary -->
				
				<!-- Order Items -->
				<div class="mb-3">
					<app:cart-contents items="${dto.items}" totalQuantity="${dto.quantity}" totalAmount="${dto.total}"></app:cart-contents>
				</div>
				
				<!-- Paid History -->
				<div class="mb-3">
					<app:cart-paid-history items="${dto.paids}" total="${dto.totalPaid}"></app:cart-paid-history>
				</div>
				
				<!-- Shipping Address -->
				<div class="mb-3">
					<app:cart-shipping address="${dto.address}"></app:cart-shipping>
				</div>
				
				<!-- Delivery -->
			</div>
			
			<div class="col-4">
				<!-- Messages -->
				
			</div>
		</div>		
	</main>
	
	
</body>
</html>