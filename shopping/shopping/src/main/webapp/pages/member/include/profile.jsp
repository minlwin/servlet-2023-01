<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags" %>

<section>
	<app:card>
		<h4 class="cart-title">
			<i class="bi bi-person-circle"></i> Profile
		</h4>
		
		<div class="mb-3">
			<label class="form-label">Name</label>
			<span class="form-control">${member.name}</span>
		</div>

		<div class="mb-3">
			<label class="form-label">Role</label>
			<span class="form-control">${member.role}</span>
		</div>

		<div class="mb-3">
			<label class="form-label">Phone</label>
			<span class="form-control">${empty member.phone ? 'No Phone Number' : member.phone}</span>
		</div>

		<div class="mb-3">
			<label class="form-label">Email</label>
			<span class="form-control">${member.email}</span>
		</div>
		
		<button id="profileEditBtn" class="btn btn-outline-primary">
			<i class="bi bi-pencil"></i> Edit Profile
		</button>

	</app:card>
	
	<app:modal-dialog modelId="profileEditDialog">
	<c:url value="/members/profile" var="profileUpdateAction"></c:url>
	<form action="${profileUpdateAction}" method="post">
		
		<div class="modal-header">
			<h4>Edit Profile</h4>
		</div>
		
		<div class="modal-body">
			<input type="hidden" id="idInput" />		

			<div class="mb-3">
				<label class="form-label">Name</label>
				<input id="nameInput" name="name" value="${member.name}" class="form-control" placeholder="Enter Name" required="required" />
			</div>
	
			<div class="mb-3">
				<label class="form-label">Phone</label>
				<input id="phoneInput" name="phone" value="${member.phone}" class="form-control" placeholder="Enter Phone" required="required" />
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