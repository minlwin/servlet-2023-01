<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>    
<%@ taglib prefix="app" tagdir="/WEB-INF/tags" %>

<c:url value="/cart/checkout" var="checkoutAction"></c:url>
<form action="${checkoutAction}" method="post">
	<app:card>
		<h4 class="card-title">
			<i class="bi bi-cart-check"></i> Confirm
		</h4>
		
		<p>
			Please check your orders and you shipping address.
		</p>
		
		<div class="mt-2">
			<button type="submit" class="btn btn-outline-primary">
				<i class="bi bi-check"></i> Purchase
			</button>
		</div>
	</app:card>
</form>