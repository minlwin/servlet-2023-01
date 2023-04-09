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
		<h3 class="mb-3"><i class="bi bi-cart-check"></i> Orders</h3>
		
		<!-- Search Bar -->
		<form class="row mb-4">
			<div class="col-auto">
				<label class="form-label">Status</label>
				<select name="status" class="form-select">
					<option value="">All Status</option>
					<option value="Ordered" ${param.status eq 'Ordered' ? 'selected' : ''}>Ordered</option>
					<option value="Delivered" ${param.status eq 'Delivered' ? 'selected' : ''}>Delivered</option>
					<option value="Finished" ${param.status eq 'Finished' ? 'selected' : ''}>Finished</option>
					<option value="Cancel" ${param.status eq 'Cancel' ? 'selected' : ''}>Cancel</option>
				</select>
			</div>
			
			<div class="col-auto">
				<label class="form-label">Date From</label>
				<input type="date" name="from" value="${param.from}" class="form-control" />
			</div>
			
			<div class="col btn-wrapper">
				<button class="btn btn-outline-primary">
					<i class="bi bi-search"></i> Search
				</button>
			</div>
		</form>
		
		<!-- Result List -->
		<c:choose>
			<c:when test="${not empty list}">
			<app:orders-table list="${list}"></app:orders-table>
			</c:when>
			
			<c:otherwise>
				<jsp:include page="/includes/no-data.jsp">
					<jsp:param value="Order" name="data"/>
				</jsp:include>
			</c:otherwise>
		</c:choose>
		
	</main>
	
	
</body>
</html>