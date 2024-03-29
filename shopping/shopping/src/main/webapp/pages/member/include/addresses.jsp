<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags" %>

<section>
	<app:card>
		<h4 class="card-title mb-4"><i class="bi bi-pin-map"></i> Shipping Addresses</h4>
		
		<c:choose>
			<c:when test="${not empty addresses}">
				<table class="table table-striped">
					<thead>
						<tr>
							<th>Name</th>
							<th>Phone</th>
							<th>Address</th>
							<th></th>
						</tr>
					</thead>
					
					<tbody>
						<c:forEach items="${addresses}" var="item">
						<tr>
							<td>${item.name}</td>
							<td>${item.phone}</td>
							<td>${item.building}, ${item.street}</td>
							<td>
								<a data-id="${item.id}" data-name="${item.name}" data-phone="${item.phone}" 
									data-building="${item.building}" data-street="${item.street}" class="btn-link addressEditLink">
									<i class="bi bi-pencil"></i>
								</a>
							</td>
						</tr>
						</c:forEach>
					</tbody>					
				</table>
			</c:when>
			
			<c:otherwise>
				<jsp:include page="/includes/no-data.jsp">
					<jsp:param value="Shipping Address" name="data"/>
				</jsp:include>
			</c:otherwise>
		</c:choose>
	</app:card>
	
	<app:modal-dialog modelId="addressEditDialog">
	<c:url value="/members/address" var="addressUpdateAction"></c:url>
	<form action="${addressUpdateAction}" method="post">
		
		<div class="modal-header">
			<h4>Shipping Address</h4>
		</div>
		
		<div class="modal-body">
			<input type="hidden" name="id" id="idInput" />		

			<div class="mb-3">
				<label class="form-label">Name</label>
				<input id="nameInput" name="name" class="form-control" placeholder="Enter Name" required="required" />
			</div>
	
			<div class="mb-3">
				<label class="form-label">Phone</label>
				<input id="phoneInput" name="phone" class="form-control" placeholder="Enter Phone" required="required" />
			</div>
		
			<div class="mb-3">
				<label class="form-label">Building</label>
				<input id="buildingInput" name="building" class="form-control" placeholder="Enter Building" required="required" />
			</div>

			<div class="mb-3">
				<label class="form-label">Street &amp; Township</label>
				<input id="streetInput" name="street" class="form-control" placeholder="Enter Street & Township" required="required" />
			</div>
		</div>
		
		<div class="modal-footer">
			<button class="btn btn-primary">
				<i class="bi bi-save"></i> Save
			</button>
		</div>	
			
	</form>	
	</app:modal-dialog>

</section>