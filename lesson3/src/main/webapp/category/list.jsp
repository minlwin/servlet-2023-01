<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="/includes/heading.jsp">
	<jsp:param value="Categories" name="title"/>
</jsp:include>	
</head>
<body>

	<jsp:include page="/includes/navbar.jsp">
		<jsp:param value="category" name="active"/>
	</jsp:include>
	
	<div class="container pt-4">
		
		<div class="mb-4 d-flex justify-content-between">
			<h3>Category Management</h3>
			<c:url value="/category-edit" var="addNew"></c:url>
			<a href="${ addNew }" class="btn btn-outline-danger"><i class="bi bi-plus-lg me-2"></i> Add New</a>	
		</div>
		
		<div class="row row-cols-3 g-3">
			
			<c:forEach items="${ list }" var="item">
				<div class="col">
					<div class="card">
						<div class="card-body">
							<h5 class="card-title">${ item.name() }</h5>
							<span>${ item.burmese() }</span>
							
							<div class="row my-4">
								
								<div class="col d-flex flex-column">
									<h3>${ item.divisionCount() }</h3>
									<span>Divisions</span>
								</div>
								
								<div class="col d-flex flex-column">
									<h3>${ item.townshipCount() }</h3>
									<span>Townships</span>
								</div>

							</div>
							
							<c:url value="/category-edit" var="edit">
								<c:param name="id" value="${ item.id() }"></c:param>
							</c:url>
							<a href="${ edit }" class="btn btn-outline-danger">
								<i class="bi bi-pencil me-2"></i> Edit
							</a>
							
						</div>
					</div>
				</div>
			</c:forEach>

			
		</div>
	
	</div>

</body>
</html>