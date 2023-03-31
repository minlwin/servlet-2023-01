<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Shop | Products</title>

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
		<h3 class="mb-3"><i class="bi bi-gift"></i> Search Products</h3>
		

		<!-- Grid -->
		<c:if test="${not empty list}">

			
		</c:if>

		<c:if test="${empty list}">
			<jsp:include page="/includes/no-data.jsp">
				<jsp:param name="data" value="category"/>
			</jsp:include>
		</c:if>
		
	</main>
	
	
</body>
</html>