<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>


<div class="modal fade" id="categoryEditDialog">
	<div class="modal-dialog">
		<c:url value="/sale/category" var="editFormAction"></c:url>
		<form action="${editFormAction}" method="post" class="modal-content">
			
			<input id="editCategoryId" type="hidden" name="id" />
			
			<div class="modal-header">
				<h4>Edit Category</h4>
			</div>
			
			<div class="modal-body">
			
				<div class="mb-3">
					<label class="form-label">Category Name</label>
					<input id="editCategoryName" type="text" name="name" class="form-control" placeholder="Enter Payment Name" required="required" />
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

<c:url value="/resources/category.js" var="categoryJS"></c:url>
<script src="${categoryJS}" ></script>
