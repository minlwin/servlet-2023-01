<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>    
<%@ taglib prefix="app" tagdir="/WEB-INF/tags" %>

<%@ attribute name="items" required="true" type="java.util.List" %>
<%@ attribute name="totalQuantity" required="true" %>
<%@ attribute name="totalAmount" required="true" %>

<app:card>
	<h4 class="card-title">
		<i class="bi bi-list"></i> Purchase Items
	</h4>
	
	<table class="table table-striped">
		<thead>
			<tr>
				<th>No</th>
				<th>Product</th>
				<th>Brand</th>
				<th class="text-end">Unit Price</th>
				<th class="text-end">Quantity</th>
				<th class="text-end">Total</th>
			</tr>
		</thead>
		
		<tbody>
			<c:forEach items="${items}" var="item" varStatus="sts">
				<tr>
					<td>${sts.index + 1}</td>
					<td>${item.productName}</td>
					<td>${item.brand}</td>
					<td class="text-end">${item.unitPrice}</td>
					<td class="text-end">${item.quantity}</td>
					<td class="text-end">${item.total}</td>
				</tr>
			</c:forEach>
		</tbody>
		
		<tfoot>
			<tr>
				<td colspan="4" class="text-end">All Total</td>
				<td class="text-end">${totalQuantity}</td>
				<td class="text-end">${totalAmount}</td>
			</tr>
		</tfoot>
	</table>	
</app:card>