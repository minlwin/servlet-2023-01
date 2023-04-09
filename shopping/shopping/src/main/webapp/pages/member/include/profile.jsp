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
	
	</app:modal-dialog>
</section>