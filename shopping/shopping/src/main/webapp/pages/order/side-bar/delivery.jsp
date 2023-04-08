<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>    
<%@ taglib prefix="app" tagdir="/WEB-INF/tags" %>

<c:url value="/members/order/delivery" var="formAction"></c:url>
<form action="${formAction}" method="post">
<app:card>
	<h4 class="card-title">
		<i class="bi bi-truck"></i> Delivery
	</h4>
	
	<input type="hidden" name="id" value="${dto.order.id}" />
	
	<div class="mb-3">
		<label class="form-label">Delivery Provider</label>
		<select id="deliveryProviderSelect" name="delivery" class="form-select">
			<c:forEach items="${deliveryProviders}" var="item">
				<option data-phone="${item.phone}" data-email="${item.email}" value="${item.id}">${item.name}</option>
			</c:forEach>
		</select>
	</div>
	
	<div class="mb-3">
		<label class="form-label">Phone Number</label>
		<span id="deliveryPhone" class="form-control">${deliveryProviders[0].phone}</span>
	</div>

	<div class="mb-3">
		<label class="form-label">Email</label>
		<span id="deliveryEmail" class="form-control">${deliveryProviders[0].email}</span>
	</div>
	
	<div class="mb-3 row">
		<div class="col">
			<label class="form-label">Delivery Date</label>
			<input name="dateFrom" type="date" class="form-control" required="required" />
		</div>
		
		<div class="col btn-wrapper">
			<input name="dateTo" type="date" class="form-control" required="required" />
		</div>
	</div>
	
	<button class="btn btn-outline-primary">
		<i class="bi bi-send"></i> Request Delivery
	</button>
</app:card>
</form>

<c:url value="/resources/js/order-delivery.js" var="deliveryJs"></c:url>
<script src="${deliveryJs}"></script>