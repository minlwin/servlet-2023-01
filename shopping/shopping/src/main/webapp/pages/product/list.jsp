<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>    
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
		<form class="row mb-3">
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
		<c:if test="${not empty list}">
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
				
					<c:forEach var="item" items="${list}">
					
					<tr>
						<td>${item.id}</td>
						<td>${item.name}</td>
						<td>${item.id}</td>
						<td>${item.brand}</td>
						<td>${item.price}</td>
						<td>${item.soldOut ? 'Yes' : ''}</td>
						<td>
							<c:url value="/sale/product/details" var="detailsLink">
								<c:param name="id" value="${item.id}"></c:param>
							</c:url>
							<a href="${detailsLink}" class="btn-link"><i class="bi bi-send"></i></a>
						</td>
					</tr>
						
					</c:forEach>
					
				</tbody>
			</table>
		</c:if>
		
		<c:if test="${empty list}">
			<jsp:include page="/includes/no-data.jsp">
				<jsp:param name="data" value="product"/>
			</jsp:include>
		</c:if>
		
	</main>
	
	
</body>
</html>