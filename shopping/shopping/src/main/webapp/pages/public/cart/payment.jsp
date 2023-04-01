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
		<h3><i class="bi bi-cart-check"></i> My Cart</h3>
		<h5><i class="bi bi-credit-card"></i> Payment History</h5>
		
		<div class="row mt-4">
			<div class="col-8">
				<!-- Cart Contents -->
				<div class="mb-4">
					<app:cart-contents items="${cart.items}"></app:cart-contents>
				</div>
				
				<!-- Shipping address -->
				<div class="mb-4">
					<app:cart-shipping address="${cart.address}"></app:cart-shipping>
				</div>
			
			</div>
			
			<div class="col">
			
				<app:card>
					<h4 class="card-title">
						<i class="bi bi-credit-card"></i> Paid History
					</h4>
					
					<form action="#">
						<div class="mb-3">
							<label class="form-label">Payment</label>
							<select class="form-select">
								<c:forEach items="${paidInfoList}" var="item">
									<option value="${item.id}">${item.paymentName} - ${item.paymentType}</option>
								</c:forEach>
							</select>
						</div>
						
						<div class="mb-3">
							<label class="form-label">Account Number</label>
							<span class="form-control">${paidInfoList[0].accountNumber}</span>
						</div>

						<div class="mb-3">
							<label class="form-label">Account Name</label>
							<span class="form-control">${paidInfoList[0].accountName}</span>
						</div>
						
						<div class="mb-3">
							<label class="form-label">Amount</label>
							<input type="number" required="required" placeholder="Enter Amount" class="form-control" />
						</div>
						
						<div>
							<button class="btn btn-block btn-outline-primary">
								<i class="bi bi-camera"></i> Screen Shoot
							</button>
						</div>
					</form>
				
				</app:card>			
			
			</div>
		</div>		
	</main>	
	
</body>
</html>