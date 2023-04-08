<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>    
<%@ taglib prefix="app" tagdir="/WEB-INF/tags" %>

<%@ attribute name="vo" required="true" type="com.jdc.shop.model.dto.vo.OrderDeliveryVo" %>

<app:card>
	<h4 class="card-title">
		<i class="bi bi-truck"></i> Delivery
	</h4>
	
	<p>
		<i class="bi bi-person-circle"></i> ${vo.delivery}<br/>
		<i class="bi bi-telephone"></i> ${vo.phone}<br />
		<i class="bi bi-calendar"></i> ${vo.dateFrom} - ${vo.dateTo} <br/>
		<br />
	</p>
</app:card>