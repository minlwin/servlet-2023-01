<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags" %>

<section class="row mb-4">

	<!-- Ordered -->
	<div class="col">
	<app:sale-summary item="${summary.ordered}" icon="bi-cart" />
	</div>
	
	<!-- Canceled -->
	<div class="col">
	<app:sale-summary item="${summary.cancel}" icon="bi-cart-x"></app:sale-summary>
	</div>
	
	<!-- Delivered -->
	<div class="col">
	<app:sale-summary item="${summary.delivered}" icon="bi-truck"></app:sale-summary>
	</div>
	
	<!-- Finished -->
	<div class="col">
	<app:sale-summary item="${summary.finished}" icon="bi-cart-check"></app:sale-summary>
	</div>

</section>