<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>    
<%@ taglib prefix="app" tagdir="/WEB-INF/tags" %>

<%@ attribute name="items" required="true" type="java.util.List" %>
<%@ attribute name="total" required="true" %>
<%@ attribute name="withControls" required="false" %>

<app:card>
	
	<h4><i class="bi bi-list-check"></i> Paid History</h4>
	
	<table class="table table-strpied">
		<thead>
			<tr>
				<th>Payment</th>
				<th>Account Number</th>
				<th>Account Name</th>
				<th></th>
				<th class="text-end">Total</th>
			</tr>
		</thead>
		
		<tbody>
			<c:forEach items="${items}" var="item" varStatus="status">
				<tr>
					<td>${item.payment}</td>
					<td>${item.accountNumber}</td>
					<td>${item.accountName}</td>
					<td>
						<c:url value="/resources/photos/${item.screenShoot}" var="imageLink"></c:url>
						<a data-image-link="${imageLink}" class="btn-link me-2 paidImageLink">
							<i class="bi bi-image-fill"></i>
						</a>
						
						<c:if test="${not empty withControls}">
						<c:url value="/customer/cart/payment/delete" var="deleteLink" >
							<c:param name="index" value="${status.index}"></c:param>
						</c:url>
						<a href="${deleteLink}" class="btn-link">
							<i class="bi bi-trash"></i>
						</a>
						</c:if>
						
					</td>
					<td class="text-end">${item.amount}</td>

				</tr>
			</c:forEach>
		</tbody>
		
		<tfoot>
			<tr>
				<td colspan="4" class="text-end">All Total</td>
				<td class="text-end">${total}</td>
			</tr>
		</tfoot>
	</table>

</app:card>

<app:modal-dialog modelId="paymentScreenShootDialog">
	<div class="modal-header">
		<h4>Payment Screen Shoot</h4>
	</div>
	
	<div class="modal-body">
		<c:url value="/resources/photos/${cart.paidInformations[0].screenShoot}" var="screenShootUrl"></c:url>
		<img id="targetScreenShoot" src="${screenShootUrl}" class="image-responsive w-100" />
	</div>
</app:modal-dialog>


<c:url value="/resources/js/cart-paid-history.js" var="scriptUrl"></c:url>
<script src="${scriptUrl}"></script>
