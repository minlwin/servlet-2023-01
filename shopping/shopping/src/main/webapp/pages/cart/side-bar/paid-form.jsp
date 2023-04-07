<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>    
<%@ taglib prefix="app" tagdir="/WEB-INF/tags" %>

<app:card>

	<h4 class="card-title">
		<i class="bi bi-credit-card"></i> Payment History
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

<c:url value="/resources/js/cart-paid-form.js" var="scriptUrl"></c:url>
<script src="${scriptUrl}"></script>
