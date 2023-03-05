<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="/includes/heading.jsp">
	<jsp:param value="Divisions" name="title"/>
</jsp:include>	
</head>
<body>

	<jsp:include page="/includes/navbar.jsp">
		<jsp:param value="division" name="active"/>
	</jsp:include>
	
	<div class="container pt-4">
		<div class="mb-4 d-flex justify-content-between">
			<h3>Division Management</h3>
			<c:url value="/division-edit" var="addNew"></c:url>
			<a href="${ addNew }" class="btn btn-outline-danger"><i class="bi bi-plus-lg me-2"></i> Add New</a>	
		</div>
		
		<c:choose>
			<c:when test="${ empty list }">
				<jsp:include page="/includes/no-data.jsp">
					<jsp:param value="Division" name="name"/>
				</jsp:include>
			</c:when>
			
			<c:otherwise>
				
				<table class="table table-striped">
					
					<tr>
						<th>Id</th>
						<th>Name</th>
						<th>Burmese</th>
						<th>Category</th>
						<th>Capital</th>
						<th></th>
					</tr>
					
					<tr>
						<td>1</td>
						<td>Ayawadii</td>
						<td>Burmese</td>
						<td>State</td>
						<td>Pathein</td>
						<td>
							<a href="#" class="btn btn-link">
								<i class="bi bi-pencil"></i>
							</a>
						</td>
					</tr>
					
				</table>
			
			</c:otherwise>
		
		</c:choose>
	
	</div>

</body>
</html>