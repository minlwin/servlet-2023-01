<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Shop | Member Edit</title>

<jsp:include page="/includes/bootstrap.jsp"></jsp:include>
</head>
<body>

	<nav>
		<!-- Menu Bar -->
		<jsp:include page="/includes/menu-bar.jsp"></jsp:include>
	
	</nav>
	
	
	<!-- Content -->
	<main class="container pt-3">
		
		<form method="post">
			
			<h3 class="mb-3"><i class="bi bi-gift"></i> ${empty param.id ? 'Add New' : 'Edit'} Product</h3>
			
			
		</form>
	</main>
	
	
</body>
</html>