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
				<app:cart-contents items="${cart.items}"></app:cart-contents>
			</div>
			
			<div class="col">
				<app:card>
					<h4><i class="bi bi-check"></i> Check Out Operation</h4>	
					<p>You need to sign in or sign up for check out operation.</p>			
					<div>
						<button class="btn btn-outline-primary me-2 signUpMenu">
							<i class="bi bi-door-open-fill"></i> Sign Up 
						</button>
						<button class="btn btn-outline-primary signInMenu">
							<i class="bi bi-door-open"></i> Sign In 
							</button>
					</div>
				</app:card>
			</div>
			
		</div>
	</main>
	
	
</body>
</html>