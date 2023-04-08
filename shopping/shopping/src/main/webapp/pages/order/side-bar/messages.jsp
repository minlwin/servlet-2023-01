<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>    
<%@ taglib prefix="app" tagdir="/WEB-INF/tags" %>

<c:url value="/members/order/message" var="formAction"></c:url>
<form action="${formAction}" method="post">
<app:card>
	<h4 class="card-title">
		<i class="bi bi-chat"></i> Messages
	</h4>
	
	<c:choose>
		<c:when test="${not empty dto.messages}">
		
		<ul class="list-group list-group-flush">
			<c:forEach items="${dto.messages}" var="item">
				<li class="list-group-item">
					<div class="my-1">${item.message}</div>
					<div class="d-flex justify-content-between text-secondary">
						<span><i class="bi bi-calendar-check"></i> ${item.sendDateTime}</span>
						<span><i class="bi ${item.role eq 'Customer' ? 'bi-person' : (item.role eq 'Delivery' ? 'bi-truck' : 'bi-shop')}"></i> ${item.sender}</span>
					</div>
				</li>
			</c:forEach>	
		</ul>
		
		</c:when>
		
		<c:otherwise>
			<p>
				There is no messages. You can ask question here!
			</p>
		</c:otherwise>
	</c:choose>	
	
	<div class="my-3">
		<textarea name="message" 
			cols="30" rows="3" class="form-control"
			placeholder="Enter Message" required="required"></textarea>
	</div>
	
	<div>
		<button class="btn btn-outline-primary me-2">
			<i class="bi bi-send"></i> Send
		</button>
		
		<c:if test="${(login.role eq 'Sale' or login.role eq 'Owner') and dto.order.status eq 'Ordered'}">
		<button id="cancelBtn" type="button" class="btn btn-outline-primary me-2">
			<i class="bi bi-x-lg"></i> Cancel
		</button>
		
		<c:url value="/members/order/delivery" var="deliveryUrl">
			<c:param name="id" value="${dto.order.id}"></c:param>
		</c:url>
		<a href="${deliveryUrl}" class="btn btn-outline-primary me-2">
			<i class="bi bi-truck"></i> Delivery
		</a>
		</c:if>
		
		<c:if test="${login.role eq 'Delivery' and dto.order.status ne 'Finished'}">
		<button id="finishBtn" type="button" class="btn btn-outline-primary me-2">
			<i class="bi bi-check"></i> Finish
		</button>
		</c:if>
	</div>
</app:card>
</form>

<c:url value="/members/order/status" var="statusChangeAction"></c:url>	

<c:if test="${(login.role eq 'Sale' or login.role eq 'Owner') and dto.order.status eq 'Ordered'}">

<app:modal-dialog modelId="cancelDialog">
	<form action="${statusChangeAction}" method="post">
		<input type="hidden" name="id" value="${dto.order.id}" />	
		<input type="hidden" name="status" value="Cancel" />
		
		<div class="modal-header">
			<h4>Cancel Order</h4>
		</div>
		
		<div class="modal-body">
			<label class="form-label">Reason</label>
			<textarea name="remark" class="form-control" rows="3" cols="30" placeholder="Enter reason for cancel"></textarea>
		</div>	
		
		<div class="modal-footer">
			<button class="btn btn-outline-primary">
				<i class="bi bi-send"></i> Change Status
			</button>		
		</div>
	
	</form>
</app:modal-dialog>		
</c:if>

<c:if test="${login.role eq 'Delivery' and dto.order.status ne 'Finished'}">
<app:modal-dialog modelId="finishDialog">
	<form action="${statusChangeAction}" method="post">
		<input type="hidden" name="id" value="${dto.order.id}" />	
		<input type="hidden" name="status" value="Finished" />	
		
		<div class="modal-header">
			<h4>Finish Order</h4>
		</div>
		
		<div class="modal-body">
			<p>Order has been delivered. Status will be change to Finished.</p>
		</div>	
		
		<div class="modal-footer">
			<button class="btn btn-outline-primary">
				<i class="bi bi-send"></i> Change Status
			</button>		
		</div>	
	</form>
</app:modal-dialog>		
</c:if>

<c:url value="/resources/js/order-message.js" var="messageJs"></c:url>

<script src="${messageJs}"></script>
