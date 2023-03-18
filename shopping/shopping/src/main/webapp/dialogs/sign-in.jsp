<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>


<div class="modal fade" id="signInForm">
	<div class="modal-dialog">
		<c:url value="/sign-in" var="signInAction"></c:url>
		<form action="${ signInAction }" method="post" class="modal-content">
			
			<div class="modal-header">
				<h4>Sign In</h4>
			</div>
			
			<div class="modal-body">
			
				<div class="mb-3">
					<label for="login" class="form-label">Email</label>
					<input type="email" name="loginId" id="login" class="form-control" placeholder="Enter Login Id" required="required" />
				</div>
				
				<div class="mb-3">
					<label for="password" class="form-label">Password</label>
					<input type="password" name="password" id="password" class="form-control" placeholder="Enter Password" required="required" />
				</div>
			
			</div>
			
			<div class="modal-footer">
				<button class="btn btn-primary">
					<i class="bi bi-door-open"></i> Sign In
				</button>
			</div>
		</form>
	</div>
</div>

