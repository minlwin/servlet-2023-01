<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@ attribute name="address" required="true" type="com.jdc.shop.model.dto.form.PurchaseAddressForm" %>

<div class="card">
	<div class="card-body">
		<h4 class="card-titl">
			<i class="bi bi-pin-map-fill"></i> Shipping Address
		</h4>
		
		<p>
			To ${address.name}<br/>
			Phone : ${address.phone}<br />
			${address.building}<br/>
			${address.street}
		</p>
	</div>
</div>