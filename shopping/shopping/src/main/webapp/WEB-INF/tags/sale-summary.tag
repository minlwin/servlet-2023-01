<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>    
<%@ taglib prefix="app" tagdir="/WEB-INF/tags" %>

<%@ attribute name="item" required="true" type="com.jdc.shop.model.dto.vo.SaleSummaryItem" %>
<%@ attribute name="icon" required="true" type="java.lang.String" %>

<app:card>
	<div class="row">
		<div class="col-auto">
			<i class="bi ${icon}" style="font-size: 3rem;"></i>
		</div>
		<div class="col text-end">
			<div>${item.currentMonth}</div>
			<div>${item.total}</div>
			<div>${item.status}</div>
		</div>
	</div>
</app:card>