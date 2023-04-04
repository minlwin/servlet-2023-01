<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>    
<%@ taglib prefix="app" tagdir="/WEB-INF/tags" %>

<%@ attribute name="address" required="true" type="com.jdc.shop.model.dto.form.PurchaseAddressForm" %>

<app:card>
	<h4 class="card-title">
		<i class="bi bi-pin-map-fill"></i> Shipping Address
	</h4>
	
	<p>
		To ${address.name}<br/>
		Phone : ${address.phone}<br />
		${address.building}<br/>
		${address.street}
	</p>
</app:card>

