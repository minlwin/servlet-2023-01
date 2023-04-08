<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>    
<%@ taglib prefix="app" tagdir="/WEB-INF/tags" %>

<%@ attribute name="items" required="true" type="java.util.List" %>
<%@ attribute name="totalQuantity" required="true" %>
<%@ attribute name="totalAmount" required="true" %>
<%@ attribute name="canEdit" required="false" %>

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
					<td class="text-end">
						
						<c:choose>
							<c:when test="${not empty canEdit and canEdit}">
								<c:url value="/cart/remove" var="removeOne">
									<c:param name="id" value="${item.productId}"></c:param>
								</c:url>
								
								<a href="${removeOne}" class="btn-link me-3">
									<i class="bi bi-cart-dash"></i>
								</a>
								<span class="me-3">${item.quantity}</span>
		
								<c:url value="/cart/add" var="addOne">
									<c:param name="cart" value="1"></c:param>
									<c:param name="id" value="${item.productId}"></c:param>
								</c:url>
								<a href="${addOne}" class="btn-link">
									<i class="bi bi-cart-plus"></i>
								</a>
							</c:when>
							
							<c:otherwise>
								<span>${item.quantity}</span>
							</c:otherwise>
						</c:choose>
					</td>
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