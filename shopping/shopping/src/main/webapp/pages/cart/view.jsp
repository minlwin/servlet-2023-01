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
		
		<div class="row mt-4">

			<div class="col-8">
				<!-- Cart Contents -->
				<div class="mb-4">
					<app:cart-contents items="${cart.items}" totalAmount="${cart.total}" totalQuantity="${cart.size}"></app:cart-contents>
				</div>
				
				<!-- Paid History -->
				<c:if test="${cart.totalPaid gt 0}">
					<div class="mb-4">
						<app:cart-paid-history items="${cart.paidInformations}" total="${cart.totalPaid}"></app:cart-paid-history>
					</div>				
				</c:if>
				
				<!-- Shipping Information -->
				<c:if test="${not empty cart.address}">
					<div class="mb-4">
						<app:cart-shipping address="${cart.address}"></app:cart-shipping>
					</div>
				</c:if>
			</div>
			
			<div class="col">
			
				<c:choose>
					<c:when test="${empty login}">
						<jsp:include page="side-bar/anonymous.jsp"></jsp:include>
					</c:when>
					
					<c:otherwise>
					
						<c:choose>
							<c:when test="${cart.remain gt 0}">
								<jsp:include page="side-bar/paid-form.jsp"></jsp:include>
							</c:when>
							
							<c:otherwise>
							
								<c:choose>
									<c:when test="${empty cart.address}">
										<jsp:include page="side-bar/shipping-form.jsp"></jsp:include>
									</c:when>
									
									<c:otherwise>
										<jsp:include page="side-bar/confirm.jsp"></jsp:include>
									</c:otherwise>
								</c:choose>
							
							</c:otherwise>
						</c:choose>
					
					</c:otherwise>				
				</c:choose>
				
			</div>
		</div>		
	</main>	
	
</body>
</html>