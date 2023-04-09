<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Shop | Members</title>

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
		<h3 class="mb-3"><i class="bi bi-person-circle"></i> Member Home</h3>
		
		<div class="row">
		
			<div class="col-9">
				<!-- Sales Summary -->
				<c:if test="${login.role eq 'Sale' or login.role eq 'Owner'}">
					<jsp:include page="include/sales-summary.jsp"></jsp:include>
				</c:if>
			
				<!-- Resent Orders -->
				<div class="mb-3">
					<jsp:include page="include/orders.jsp"></jsp:include>
				</div>
				
				<!-- Addresses for customer -->
				<c:if test="${login.role eq 'Customer'}">
					<jsp:include page="include/addresses.jsp"></jsp:include>
				</c:if>
			</div>
		
		
			<div class="col">
				<!-- Profile -->
				<jsp:include page="include/profile.jsp"></jsp:include>
			</div>
		</div>
		
	</main>
	
	<c:url value="/resources/js/member-home.js" var="memberHomeJs"></c:url>
	<script type="text/javascript" src="${memberHomeJs}"></script>
</body>
</html>