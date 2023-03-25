<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Shop | Category</title>

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
		<h3 class="mb-3"><i class="bi bi-tags"></i> Category Management</h3>
		
		<div class="mb-3">
			<button type="button" id="addNewCategoryBtn" class="btn btn-outline-danger">
				<i class="bi bi-plus-lg"></i> Add New
			</button>
		</div>

		<!-- Grid -->
		<c:if test="${not empty list}">
		<div class="row row-cols-lg-3 row-cols-md-2 row-cols-sm-1">
			
			<c:forEach var="item" items="${list}">
				<div class="col">
					<div class="card">
						<div class="card-body d-flex justify-content-between">
							<h4>
								<i class="bi bi-tag"></i> ${item.name}
							</h4>
							
							<a href="#" class="btn-link categoryEditBtn" data-id="${item.id}" data-name="${item.name}" >
								<i class="bi bi-pencil"></i>
							</a>
						</div>
					</div>
				</div>
			</c:forEach>
			
		</div>
		</c:if>

		<c:if test="${empty list}">
		<jsp:include page="/includes/no-data.jsp">
			<jsp:param name="data" value="category"/>
		</jsp:include>
		</c:if>
		
	</main>
	
	<!-- Edit Modal Dialog -->
	<jsp:include page="edit.jsp"></jsp:include>
	
</body>
</html>