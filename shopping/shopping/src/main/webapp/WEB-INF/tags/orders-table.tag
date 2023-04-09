<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>    
<%@ taglib prefix="app" tagdir="/WEB-INF/tags" %>

<%@ attribute name="list" required="true" type="java.util.List" %>

<table class="table table-striped">
	<thead>
		<tr>
			<th>Date</th>
			<th>Status</th>
			<th>Customer</th>
			<th>Total Amount</th>
			<th>Remark</th>
			<th></th>
		</tr>
	</thead>
	
	<tbody>
		<c:forEach var="item" items="${list}">
		<tr>
			<td>${item.dateTime}</td>
			<td>${item.status}</td>
			<td>${item.customerName}</td>
			<td>${item.totalAmount}</td>
			<td>${item.remark}</td>
			<td>
				<c:url value="/members/order" var="showDetailsUrl">
					<c:param name="id" value="${item.id}"></c:param>
				</c:url>
				<a href="${showDetailsUrl}" class="btn-link">
					<i class="bi bi-search"></i>
				</a>
			</td>
		</tr>
		</c:forEach>	
	</tbody>
</table>