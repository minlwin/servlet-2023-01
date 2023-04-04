<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>    
<%@ taglib prefix="app" tagdir="/WEB-INF/tags" %>

<app:card>
	
	<h4 class="card-title">
		<i class="bi bi-check"></i> Check Out Operation
	</h4>	
	
	<p>You need to sign in or sign up for check out operation.</p>			
	
	<div>
		<button class="btn btn-outline-primary me-2 signUpMenu">
			<i class="bi bi-door-open-fill"></i> Sign Up 
		</button>

		<button class="btn btn-outline-primary signInMenu">
			<i class="bi bi-door-open"></i> Sign In 
		</button>
	</div>
</app:card>
    