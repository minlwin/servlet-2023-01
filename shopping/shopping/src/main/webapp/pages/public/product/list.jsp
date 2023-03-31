<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>    
<%@ taglib prefix="app" tagdir="/WEB-INF/tags" %>

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
			<div class="row row-cols-4 g-2 mt-2">
				<c:forEach items="${list}" var="item">
					<div class="col">
						<app:public-product dto="${item}"></app:public-product>
					</div>
				</c:forEach>
			</div>
		</c:if>

		<c:if test="${empty list}">
			<jsp:include page="/includes/no-data.jsp">
				<jsp:param name="data" value="category"/>
			</jsp:include>
		</c:if>
		
	</main>
	
	
</body>
</html>