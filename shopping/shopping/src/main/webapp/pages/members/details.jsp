<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Shop | Member Details</title>

<jsp:include page="/includes/bootstrap.jsp"></jsp:include>
</head>
<body>

	<nav>
		<!-- Menu Bar -->
		<jsp:include page="/includes/menu-bar.jsp"></jsp:include>
	
	</nav>
	
	
	<!-- Product List -->
	<main class="container pt-3">
		<div class="row">
			<div class="col-6">
				<h3><i class="bi bi-person"></i> Member Details</h3>
				
				<div class="my-3">
					<label class="form-label">Member ID</label>
					<span class="form-control">${dto.id}</span>
				</div>
				<div class="mb-3">
					<label class="form-label">Member Name</label>
					<span class="form-control">${dto.name}</span>
				</div>
				<div class="mb-3">
					<label class="form-label">Member Role</label>
					<span class="form-control">${dto.role}</span>
				</div>
				<div class="mb-3">
					<label class="form-label">Email Address</label>
					<span class="form-control">${dto.email}</span>
				</div>
				<div class="mb-3">
					<label class="form-label">Phone Number</label>
					<span class="form-control">${empty dto.phone ? 'No Phone Number' : dto.phone}</span>
				</div>
				
				<c:if test="${dto.role eq 'Sale' or dto.role eq 'Delivery'}">
					<c:url value="/owner/members/edit" var="edit">
						<c:param name="id" value="${dto.id}"></c:param>
					</c:url>
					<a href="${edit}" class="btn btn-outline-danger">
						<i class="bi bi-pencil"></i> Edit Member
					</a>
				</c:if>
			</div>
		</div>		
	</main>
	
	
</body>
</html>