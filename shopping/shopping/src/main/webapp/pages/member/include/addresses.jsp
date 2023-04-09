<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags" %>

<section>
	<app:card>
		<h4 class="card-title mb-4"><i class="bi bi-pin-map"></i> Shipping Addresses</h4>
		
		<c:choose>
			<c:when test="${not empty addresses}">
				<table class="table table-striped">
					<thead>
						<tr>
							<th>Name</th>
							<th>Phone</th>
							<th>Address</th>
							<th></th>
						</tr>
					</thead>
					
					<tbody>
						<c:forEach items="${addresses}" var="item">
						<tr>
							<td>${item.name}</td>
							<td>${item.phone}</td>
							<td>${item.building}, ${item.street}</td>
							<td>
								<a class="btn-link addressEditLink">
									<i class="bi bi-pencil"></i>
								</a>
							</td>
						</tr>
						</c:forEach>
					</tbody>					
				</table>
			</c:when>
			
			<c:otherwise>
				<jsp:include page="/includes/no-data.jsp">
					<jsp:param value="Shipping Address" name="data"/>
				</jsp:include>
			</c:otherwise>
		</c:choose>
	</app:card>
	
	<app:modal-dialog modelId="addressEditDialog">
		
	</app:modal-dialog>
</section>