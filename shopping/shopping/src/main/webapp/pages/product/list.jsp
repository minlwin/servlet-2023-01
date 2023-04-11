<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>   
<%@ taglib prefix="app" tagdir="/WEB-INF/tags" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Shop | Products</title>

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
		<h3 class="mb-3"><i class="bi bi-gift"></i> Products Management</h3>
		
		<!-- Search Box -->
		<form id="adminProductSearch" class="row mb-3">
		
			<input type="hidden" id="adminProductSearchPageInput" name="page" value="${model.currentPage}" />
			<input type="hidden" id="adminProductSearchSizeInput" name="size" value="${model.pageSize}" />
			
			<div class="col-3">
				<label class="form-label">Category</label>
				<input type="text" name="category" class="form-control" placeholder="Search Category" />
			</div>
			
			<div class="col-3">
				<label class="form-label">Keyword</label>
				<input type="text" name="keyword" value="${param.keyword}" placeholder="Search Keyword" class="form-control" />
			</div>
			
			<div class="col btn-wrapper">
				<button class="btn btn-outline-primary me-2" type="submit">
					<i class="bi bi-search"></i> Search
				</button>
				
				<c:url value="/sale/product/edit" var="addNew"></c:url>
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
						<th>Category</th>
						<th>Brand</th>
						<th>Price</th>
						<th>Sold Out</th>
						<th></th>
					</tr>
				</thead>
				
				<tbody>
				
					<c:forEach var="item" items="${model.list}">
					
					<tr>
						<td>${item.product.id}</td>
						<td>${item.product.name}</td>
						<td class="d-flex flex-column">
							<c:forEach var="cat" items="${item.categories}">
								<span>${cat.name}</span>
							</c:forEach>
						</td>
						<td>${item.product.brand}</td>
						<td>${item.product.price}</td>
						<td>${item.product.soldOut ? 'Out' : 'In Hand'}</td>
						<td>
							<c:url value="/sale/product/details" var="detailsLink">
								<c:param name="id" value="${item.product.id}"></c:param>
							</c:url>
							<a href="${detailsLink}" class="btn-link"><i class="bi bi-send"></i></a>
						</td>
					</tr>
						
					</c:forEach>
					
				</tbody>
			</table>
			
			<app:pagination formId="adminProductSearch" pageSizeList="${pageSizes}" dataModel="${model}"></app:pagination>
			
		</c:if>
		
		<c:if test="${empty model.list}">
			<jsp:include page="/includes/no-data.jsp">
				<jsp:param name="data" value="product"/>
			</jsp:include>
		</c:if>
		
	</main>
	
	
</body>
</html>