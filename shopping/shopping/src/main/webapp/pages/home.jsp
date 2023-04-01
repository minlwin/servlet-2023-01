<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>  
<%@ taglib prefix="app" tagdir="/WEB-INF/tags" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Shop | Home</title>

<jsp:include page="/includes/bootstrap.jsp"></jsp:include>
</head>
<body>

	<nav>
		<!-- Menu Bar -->
		<jsp:include page="/includes/menu-bar.jsp"></jsp:include>
	</nav>
	
	
	<!-- Product List -->
	<main>
		<!-- Banner -->
		<jsp:include page="/includes/banner.jsp"></jsp:include>
		
		<div class="container">
		
			<section class="row mt-4 text-center">
				
				<div class="col">
					<app:cover-info icon="bi-search">
						<h2>Search you want!</h2>
						<p>You can search every thing you want!</p>
					</app:cover-info>
				</div>
				
				<div class="col">
					<app:cover-info icon="bi-cart-plus">
						<h2>Get it now!</h2>
						<p>If you found you want, you can add item to your cart.</p>
					</app:cover-info>
				</div>
	
				<div class="col">
					<app:cover-info icon="bi-emoji-smile">
						<h2>Be Happy!</h2>
					</app:cover-info>
				</div>
			</section>
		</div>
	
	</main>
	
	
</body>
</html>