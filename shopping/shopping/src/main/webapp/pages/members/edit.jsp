<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Shop | Member Edit</title>

<jsp:include page="/includes/bootstrap.jsp"></jsp:include>
</head>
<body>

	<nav>
		<!-- Menu Bar -->
		<jsp:include page="/includes/menu-bar.jsp"></jsp:include>
	
	</nav>
	
	
	<!-- Content -->
	<main class="container pt-3">
		
		<div class="row">
			<div class="col-6">
				<h3 class="mb-3"><i class="bi bi-pencil"></i> ${empty param.id ? 'Add New' : 'Edit'} Member</h3>
				<form method="post">
				
					<input type="hidden" name="id" value="${param.id}" />
					<!-- Role -->	
					<div class="mb-3">
						<label class="form-label">Member Role</label>
						<select required="required" name="role" class="form-select">
							<option value="">Select Role</option>
							<option value="Sale" ${dto.role eq 'Sale' ? 'selected' : ''}>Sale</option>
							<option value="Delivery" ${dto.role eq 'Delivery' ? 'selected' : ''}>Delivery</option>
						</select>
					</div>
					
					<!-- Name -->
					<div class="mb-3">
						<label class="form-label">Member Name</label>
						<input value="${dto.name}" required="required" type="text" name="name" placeholder="Enter Member Name" class="form-control" />
					</div>
					
					<!-- Email -->
					<div class="mb-3">
						<label class="form-label">Email Address</label>
						<input value="${dto.email}" required="required" type="email" name="email" placeholder="Enter Email Address" class="form-control" />
					</div>
					
					<!-- Phone -->
					<div class="mb-3">
						<label class="form-label">Phone Number</label>
						<input value="${dto.phone}" type="tel" name="phone" placeholder="Enter Phone Number" class="form-control" />
					</div>
					
					<button type="submit" class="btn btn-outline-danger">
						<i class="bi bi-save"></i> Save
					</button>
				
				</form>
			
			</div>
		</div>
	</main>
	
	
</body>
</html>