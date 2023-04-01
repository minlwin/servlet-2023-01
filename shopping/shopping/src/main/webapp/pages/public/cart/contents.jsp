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
		<h3 class="mb-3"><i class="bi bi-cart-check"></i> My Cart</h3>
		
		<table class="table table-striped">
			<thead>
				<tr>
					<th>No</th>
					<th>Product</th>
					<th>Brand</th>
					<th class="text-end">Unit Price</th>
					<th class="text-end">Quantity</th>
					<th class="text-end">Total</th>
				</tr>
			</thead>
			
			<tbody>
				<c:forEach items="${cart.items}" var="item" varStatus="sts">
					<tr>
						<td>${sts.index + 1}</td>
						<td>${item.productName}</td>
						<td>${item.brand}</td>
						<td class="text-end">${item.unitPrice}</td>
						<td class="text-end">${item.quantity}</td>
						<td class="text-end">${item.total}</td>
					</tr>
				</c:forEach>
			</tbody>
			
			<tfoot>
				<tr>
					<td colspan="4">All Total</td>
					<td class="text-end">${cart.size}</td>
					<td class="text-end">${cart.total}</td>
				</tr>
			</tfoot>
		</table>	
		
		<div class="mt-4">
			<a href="#" class="btn btn-outline-primary">
				<i class="bi bi-cart-check"></i> Check Out
			</a>
		</div>	
	</main>
	
	
</body>
</html>