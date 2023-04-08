<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>    
<%@ taglib prefix="app" tagdir="/WEB-INF/tags" %>

<form action="#" method="post">
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
		<button type="button" class="btn btn-outline-primary me-2">
			<i class="bi bi-x-lg"></i> Cancel
		</button>
		
		<a class="btn btn-outline-primary me-2">
			<i class="bi bi-truck"></i> Delivery
		</a>
		</c:if>
		
		<c:if test="${login.role eq 'Delivery' and dto.order.status ne 'Finished'}">
		<a class="btn btn-outline-primary me-2">
			<i class="bi bi-truck-fill"></i> Finish
		</a>
		</c:if>
	</div>
</app:card>
</form>