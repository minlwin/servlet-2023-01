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
			<table class="table table-striped">
				<thead>
					<tr>
						<th>Date</th>
						<th>Status</th>
						<th>Customer</th>
						<th>Total Amount</th>
						<th>Remark</th>
						<th></th>
					</tr>
				</thead>
				
				<tbody>
					<c:forEach var="item" items="${list}">
					<tr>
						<td>${item.dateTime}</td>
						<td>${item.status}</td>
						<td>${item.customerName}</td>
						<td>${item.totalAmount}</td>
						<td>${item.remark}</td>
						<td>
							<c:url value="/members/order" var="showDetailsUrl">
								<c:param name="id" value="${item.id}"></c:param>
							</c:url>
							<a href="${showDetailsUrl}" class="btn-link">
								<i class="bi bi-search"></i>
							</a>
						</td>
					</tr>
					</c:forEach>	
				</tbody>
			</table>
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