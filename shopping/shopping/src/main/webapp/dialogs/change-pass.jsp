<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags" %>


<app:modal-dialog modelId="changePassDialog">
<c:url value="/members/password" var="changePassAction"></c:url>
<form action="${changePassAction}" method="post">
	
	<div class="modal-header">
		<h4>Change Password</h4>
	</div>
	
	<div class="modal-body">
	
		<div class="mb-3">
			<label class="form-label">Old Password</label>
			<input type="password" name="oldPass" class="form-control" placeholder="Enter Old Password" required="required" />
		</div>

		<div class="mb-3">
			<label class="form-label">New Password</label>
			<input type="password" name="newPass" class="form-control" placeholder="Enter New Password" required="required" />
		</div>
	
	</div>
	
	<div class="modal-footer">
		<button class="btn btn-primary">
			<i class="bi bi-lock"></i> Change Password
		</button>
	</div>	
		
</form>
</app:modal-dialog>

