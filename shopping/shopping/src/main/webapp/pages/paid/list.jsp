<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>    
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
		<h3 class="mb-3"><i class="bi bi-credit-card"></i> Payment Methods</h3>
		
		<div class="mb-3">
			<a href="#" class="btn btn-outline-danger">
				<i class="bi bi-plus-lg"></i> Add New
			</a>
		</div>

		<!-- Table -->
		<table class="table table-strpied">
			
		</table>
	</main>
	
	
</body>
</html>