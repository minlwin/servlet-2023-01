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
		<h3 class="mb-3"><i class="bi bi-cart-check"></i> My Cart</h3>
		
		<app:cart-contents items="${cart.items}"></app:cart-contents>
		
		<div class="mt-4">
			<c:choose>
				<c:when test="${not empty login}">
					<c:url value="/customer/cart/checkout" var="checkOutLink"></c:url>
					<a href="${checkOutLink}" class="btn btn-outline-primary">
						<i class="bi bi-cart-check"></i> Check Out
					</a>
				</c:when>
				
				<c:otherwise>
					<h4>Check Out Operation</h4>	
					<p>You need to sign in or sign up for check out operation.</p>			
					<div>
						<button class="btn btn-outline-primary me-2 signUpMenu">
							<i class="bi bi-door-open-fill"></i> Sign Up 
						</button>
						<button class="btn btn-outline-primary signInMenu">
							<i class="bi bi-door-open"></i> Sign In 
 						</button>
					</div>
				</c:otherwise>
			</c:choose>
		</div>	
	</main>
	
	
</body>
</html>