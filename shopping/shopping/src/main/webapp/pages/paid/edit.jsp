<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>


<div class="modal fade" id="paidInfoEditDialog">
	<div class="modal-dialog">
		<c:url value="/owner/paid-info" var="paidInfoForm"></c:url>
		<form action="${ paidInfoForm }" method="post" class="modal-content">
			<input id="idInput" type="hidden" name="id" />
			<div class="modal-header">
				<h4>Edit Paid Info</h4>
			</div>
			
			<div class="modal-body">
			
				<div class="mb-3">
					<label for="login" class="form-label">Payment Type</label>
					<select id="paymentTypeSelect" name="paymentType" required="required" class="form-select">
						<option value="">All Payments</option>
						<option value="Banking">Banking</option>
						<option value="Mobile">Mobile</option>
					</select>
				</div>
				
				<div class="mb-3">
					<label class="form-label">Payment Name</label>
					<input id="paymentNameInput" type="text" name="paymentName" class="form-control" placeholder="Enter Payment Name" required="required" />
				</div>
			
				<div class="mb-3">
					<label class="form-label">Account Number</label>
					<input id="accountNumberInput" type="text" name="accountNumber" class="form-control" placeholder="Enter Account Number" required="required" />
				</div>

				<div class="mb-3">
					<label class="form-label">Account Name</label>
					<input id="accountNameInput" type="text" name="accountName" class="form-control" placeholder="Enter Account Name" required="required" />
				</div>

			</div>
			
			<div class="modal-footer">
				<button class="btn btn-primary" type="submit">
					<i class="bi bi-save"></i> Save
				</button>
			</div>
		</form>
	</div>
</div>

<c:url value="/resources/js/paidInfo.js" var="paidJs"></c:url>
<script src="${paidJs}" ></script>

