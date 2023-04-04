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
				
				<c:if test="${not empty cart.paidInformations}">
					
					<app:card>
						
						<h4><i class="bi bi-list-check"></i> Paid History</h4>
						
						<table class="table table-strpied">
							<thead>
								<tr>
									<th>Payment</th>
									<th>Account Number</th>
									<th>Account Name</th>
									<th>Amount</th>
									<th></th>
								</tr>
							</thead>
							
							<tbody>
								<c:forEach items="${cart.paidInformations}" var="item" varStatus="status">
									<tr>
										<td>${item.payment}</td>
										<td>${item.accountNumber}</td>
										<td>${item.accountName}</td>
										<td>${item.amount}</td>
										<td>
											<c:url value="/resources/photos/${item.screenShoot}" var="imageLink"></c:url>
											<a data-image-link="${imageLink}" class="btn-link me-2 paidImageLink">
												<i class="bi bi-image-fill"></i>
											</a>
											
											<c:url value="/customer/cart/payment/delete" var="deleteLink" >
												<c:param name="index" value="${status.index}"></c:param>
											</c:url>
											<a href="${deleteLink}" class="btn-link">
												<i class="bi bi-trash"></i>
											</a>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					
					</app:card>
					
				</c:if>
				
			</div>
			
			<div class="col">
			
				<app:card>
					<h4 class="card-title">
						<i class="bi bi-credit-card"></i> Paid History
					</h4>
					
					<c:url value="/customer/cart/payment" var="paidAction"></c:url>
					<form enctype="multipart/form-data" id="paidForm" action="${paidAction}" method="post">
						<div class="mb-3">
							<label class="form-label">Payment</label>
							<select name="paymentInfoId" id="paymentInfoSelect" class="form-select">
								<c:forEach items="${paidInfoList}" var="item">
									<option data-payment="${item.paymentName} - ${item.paymentType}" data-acc-num="${item.accountNumber}" data-acc-name="${item.accountName}" value="${item.id}">${item.paymentName} - ${item.paymentType}</option>
								</c:forEach>
							</select>
						</div>
						
						<input name="payment" id="paymentInput" type="hidden" value="${paidInfoList[0].paymentName} - ${paidInfoList[0].paymentType}" />
						
						<div class="mb-3">
							<label class="form-label">Account Number</label>
							<input readonly="readonly" id="accountNumberInput" name="accountNumber" class="form-control" value="${paidInfoList[0].accountNumber}" />
						</div>

						<div class="mb-3">
							<label class="form-label">Account Name</label>
							<input readonly="readonly" id="accountNameInput" name="accountName" class="form-control" value="${paidInfoList[0].accountName}"></input>
						</div>
						
						<div class="mb-3">
							<label class="form-label">Amount</label>
							<input name="amount" id="amountInput" type="number" required="required" placeholder="Enter Amount" class="form-control" />
						</div>
						
						<input id="screenShootInput" type="file" name="screenShoot" class="d-none" />
						
						<div>
							<button type="button" id="uploadBtn" disabled="disabled" class="btn btn-block btn-outline-primary">
								<i class="bi bi-camera"></i> Screen Shoot
							</button>
						</div>
					</form>
				
				</app:card>			
			
			</div>
		</div>		
	</main>	
	
	<c:if test="${not empty cart.paidInformations}">
		<app:modal-dialog modelId="paymentScreenShootDialog">
			<div class="modal-header">
				<h4>Payment Screen Shoot</h4>
			</div>
			
			<div class="modal-body">
				<c:url value="/resources/photos/${cart.paidInformations[0].screenShoot}" var="screenShootUrl"></c:url>
				<img id="targetScreenShoot" src="${screenShootUrl}" class="image-responsive w-100" />
			</div>
		</app:modal-dialog>
	</c:if>
	
	<c:url value="/resources/cart-payment.js" var="scriptUrl"></c:url>
	<script src="${scriptUrl}"></script>
</body>
</html>