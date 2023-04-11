<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %> 
<%@ taglib prefix="app" tagdir="/WEB-INF/tags" %>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Shop | Members</title>

<jsp:include page="/includes/bootstrap.jsp"></jsp:include>
</head>
<body>

	<nav>
		<!-- Menu Bar -->
		<jsp:include page="/includes/menu-bar.jsp"></jsp:include>
	
	</nav>
	
	
	<!-- Content -->
	<main class="container pt-3">
		<!-- Title -->
		<h3 class="mb-3"><i class="bi bi-people"></i> Members Management</h3>
		
		<!-- Search Box -->
		<form id="memberSearch" class="row mb-3">

			<input type="hidden" id="memberSearchPageInput" name="page" value="${model.currentPage}" />
			<input type="hidden" id="memberSearchSizeInput" name="size" value="${model.pageSize}" />

			<div class="col-auto">
				<label class="form-label">Search Role</label>
				<select name="role" class="form-select">
					<option value="">All Role</option>
					<option value="Owner" ${param.role eq 'Owner' ? 'selected' : ''}>Owner</option>
					<option value="Sale" ${param.role eq 'Sale' ? 'selected' : ''}>Sale</option>
					<option value="Delivery" ${param.role eq 'Delivery' ? 'selected' : ''}>Delivery</option>
					<option value="Customer" ${param.role eq 'Customer' ? 'selected' : ''}>Customer</option>
				</select>
			</div>
			
			<div class="col-3">
				<label class="form-label">Keyword</label>
				<input type="text" name="keyword" value="${param.keyword}" placeholder="Search Keyword" class="form-control" />
			</div>
			
			<div class="col btn-wrapper">
				<button id="memberSearchBtn" class="btn btn-outline-primary me-2" type="button">
					<i class="bi bi-search"></i> Search
				</button>
				
				<c:url value="/owner/members/edit" var="addNew"></c:url>
				<a href="${addNew}" class="btn btn-outline-danger">
					<i class="bi bi-plus-lg"></i> Add New
				</a>
			</div>
		</form>
		
		<!-- Member Tables -->	
		<c:if test="${not empty model.list}">
		<table class="table table-striped">
			<thead>
				<tr>
					<th>Id</th>
					<th>Name</th>
					<th>Role</th>
					<th>Email</th>
					<th>Phone</th>
					<th></th>
				</tr>
			</thead>
			
			<tbody>
			
				<c:forEach var="item" items="${model.list}">
				
				<tr>
					<td>${item.id}</td>
					<td>${item.name}</td>
					<td>${item.role}</td>
					<td>${item.email}</td>
					<td>${item.phone}</td>
					<td>
						<c:url value="/owner/members/details" var="memberDetails">
							<c:param name="id" value="${item.id}"></c:param>
						</c:url>
						<a href="${memberDetails}" class="btn-link">
							<i class="bi bi-send"></i>
						</a>
					</td>
				</tr>
					
				</c:forEach>
				
			</tbody>
		</table>
		
		<app:pagination pageSizeList="${pageSizes}" dataModel="${model}" formId="memberSearch"></app:pagination>
		</c:if>
		
		<c:if test="${empty model.list}">
		<jsp:include page="/includes/no-data.jsp">
			<jsp:param name="data" value="member"/>
		</jsp:include>
		</c:if>
		
	</main>
	
	
</body>
</html>