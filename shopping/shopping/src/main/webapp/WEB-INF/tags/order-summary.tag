<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>    
<%@ taglib prefix="app" tagdir="/WEB-INF/tags" %>

<%@ attribute name="vo" required="true" type="com.jdc.shop.model.dto.vo.OrderListVo" %>

<app:card>
	<div class="row">
		<div class="col">
			<label class="form-label"><i class="bi bi-person-circle"></i> Customer</label>
			<span class="form-control">${vo.customerName}</span>
		</div>

		<div class="col">
			<label class="form-label"><i class="bi bi-flag"></i> Status</label>
			<span class="form-control">${vo.status}</span>
		</div>

		<div class="col">
			<label class="form-label"><i class="bi bi-calendar-check"></i> Ordered Date</label>
			<span class="form-control">${vo.dateTime}</span>
		</div>
	</div>
	
</app:card>