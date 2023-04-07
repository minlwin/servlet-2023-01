<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>    
<%@ taglib prefix="app" tagdir="/WEB-INF/tags" %>

<app:card>

	<h4 class="card-title">
		<i class="bi bi-pin-map"></i> Shipping Address
	</h4>

	<!-- Address Form -->
	<c:url value="/customer/cart/shipping" var="shippingFormAction"></c:url>
	<form action="${shippingFormAction}" method="post">
		<!-- Id -->
		<c:if test="${not empty addresses}">
			<div class="mb-3">
				<label class="form-label">Select shipping address</label>
				<select name="id" id="addressSelect" class="form-select">
					<c:forEach items="${addresses}" var="item" varStatus="status">
					<option value="${item.id}" 
						data-address-name="${item.name}" 
						data-address-phone="${item.phone}" 
						data-address-building="${item.building}" 
						data-address-street="${item.street}" >Address ${status.index + 1}</option>
					</c:forEach>
					
					<option value="0">Use Other Address</option>
				</select>
			</div>
		</c:if>
		<div class="mb-3">
			<!-- Name -->
			<label class="form-label">Customer Name</label>
			<c:choose>
				<c:when test="${not empty addresses}">
					<input id="nameInput" readonly="readonly" name="name" type="text" value="${addresses[0].name}" class="form-control" placeholder="Enter Customer Name" required="required" />
				</c:when>
				
				<c:otherwise>
					<input id="nameInput" name="name" type="text" value="${login.name}" class="form-control" placeholder="Enter Customer Name" required="required" />
				</c:otherwise>
			</c:choose>
		</div>
		
		<div class="mb-3">
			<!-- Phone -->
			<label class="form-label">Contact Phone Number</label>
			
			<c:choose>
				<c:when test="${not empty addresses}">
					<input id="phoneInput" name="phone" value="${addresses[0].phone}" readonly="readonly" type="tel" class="form-control" placeholder="Enter Phone Number" required="required" />
				</c:when>
				<c:otherwise>
					<input id="phoneInput" name="phone" type="tel" class="form-control" placeholder="Enter Phone Number" required="required" />
				</c:otherwise>
			</c:choose>
		</div>			

		<div class="mb-3">
			<!-- Building -->
			<label class="form-label">Building</label>

			<c:choose>
				<c:when test="${not empty addresses}">
					<input id="buildingInput" name="building" value="${addresses[0].building}" readonly="readonly" type="text" class="form-control" placeholder="Enter Address" required="required" />
				</c:when>
				<c:otherwise>
					<input id="buildingInput" name="building" type="text" class="form-control" placeholder="Enter Address" required="required" />
				</c:otherwise>
			</c:choose>
		</div>		
		
		<div class="mb-3">
			<!-- Street -->
			<label class="form-label">Street Address</label>

			<c:choose>
				<c:when test="${not empty addresses}">
					<input id="streetInput" name="street" value="${addresses[0].street}" readonly="readonly" type="text" class="form-control" placeholder="Enter Street and Township" required="required" />
				</c:when>
				<c:otherwise>
					<input id="streetInput" name="street" type="text" class="form-control" placeholder="Enter Street and Township" required="required" />
				</c:otherwise>
			</c:choose>

		</div>	
		
		<!-- Button -->
		<div class="mt-3">
			<button id="saveAddressBtn" type="submit" class="btn btn-outline-primary">
				<i class="bi bi-check-lg"></i> Save
			</button>
		</div>
	</form>	
	
	<c:url value="/resources/js/cart-shipping.js" var="scriptUrl"></c:url>
	<script src="${scriptUrl}"></script>				
</app:card>	