<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags" %>


<app:modal-dialog modelId="errorDialog">

	<div class="modal-header">
		<h4>Message</h4>
	</div>
	
	<div class="modal-body">
		<p>${param.error}</p>
	</div>
	
	<div class="modal-footer">
		<button class="btn btn-outline-primary" data-bs-dismiss="modal" data-bs-target="errorDialog">
			<i class="bi bi-x-lg"></i> Close
		</button>
	</div>

</app:modal-dialog>