<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>    
<%@ taglib prefix="app" tagdir="/WEB-INF/tags" %>

<%@ attribute name="address" required="true" type="com.jdc.shop.model.dto.form.PurchaseAddressForm" %>

<app:card>
	<h4 class="card-title">
		<i class="bi bi-pin-map-fill"></i> Shipping Address
	</h4>
	
	<p>
		<i class="bi bi-person-circle"></i> ${address.name}<br/>
		<i class="bi bi-telephone"></i> ${address.phone}<br />
		<i class="bi bi-map"></i> ${address.building}<br/>
		${address.street}
	</p>
</app:card>

