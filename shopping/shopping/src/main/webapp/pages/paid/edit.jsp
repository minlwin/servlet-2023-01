<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>


<div class="modal fade" id="paidInfoEditDialog">
	<div class="modal-dialog">
		<c:url value="/owner/paid-info" var="paidInfoForm"></c:url>
		<form action="${ paidInfoForm }" method="post" class="modal-content">
			
			<div class="modal-header">
				<h4>Edit Paid Info</h4>
			</div>
			
			<div class="modal-body">
			
				<div class="mb-3">
					<label for="login" class="form-label">Payment Type</label>
					<select name="paymentType" class="form-select">
						<option value="">All Payments</option>
						<option value="Banking" ${param.role eq 'Banking' ? 'selected' : ''}>Banking</option>
						<option value="Mobile" ${param.role eq 'Mobile' ? 'selected' : ''}>Mobile</option>
					</select>
				</div>
				
				<div class="mb-3">
					<label class="form-label">Payment Name</label>
					<input type="text" name="paymentName" class="form-control" placeholder="Enter Payment Name" required="required" />
				</div>
			
				<div class="mb-3">
					<label class="form-label">Account Number</label>
					<input type="text" name="accountNumber" class="form-control" placeholder="Enter Account Number" required="required" />
				</div>

				<div class="mb-3">
					<label class="form-label">Account Name</label>
					<input type="text" name="accountName" class="form-control" placeholder="Enter Account Name" required="required" />
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

