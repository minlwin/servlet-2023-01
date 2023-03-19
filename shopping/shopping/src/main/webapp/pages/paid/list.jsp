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
			<button id="paidInfoAddNewBtn" class="btn btn-outline-danger">
				<i class="bi bi-plus-lg"></i> Add New
			</button>
		</div>

		<!-- Table -->
		<c:if test="${not empty list}">
		<table class="table table-strpied">
			<thead>
				<tr>
					<th>Id</th>
					<th>Payment Type</th>
					<th>Payment Name</th>
					<th>Account Number</th>
					<th>Account Name</th>
					<th></th>
				</tr>
			</thead>
			
			<tbody>
				<c:forEach items="${list}" var="item">
					<tr>
						<td>${item.id}</td>
						<td>${item.paymentType}</td>
						<td>${item.paymentName}</td>
						<td>${item.accountNumber}</td>
						<td>${item.accountName}</td>
						<td>
							<c:url value="/owner/padi-info/details" var="fetchLink">
								<c:param name="id" value="${item.id}"></c:param>
							</c:url>
							<a data-link="${fetchLink}" class="btn-link paidEditLink">
								<i class="bi bi-pencil"></i>
							</a>
						</td>
					</tr>
				
				</c:forEach>
			</tbody>
			
		</table>
		</c:if>

		<c:if test="${empty list}">
			<jsp:include page="/includes/no-data.jsp">
				<jsp:param name="data" value="payment method"/>
			</jsp:include>
		</c:if>
		
		<jsp:include page="edit.jsp"></jsp:include>
	</main>
	
	
</body>
</html>