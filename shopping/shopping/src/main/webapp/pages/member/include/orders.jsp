<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags" %>

<section>
	<app:card>
		<h4 class="card-title mb-4"><i class="bi bi-cart"></i> Resent Orders</h4>
		
		<c:choose>
			<c:when test="${not empty orders}">
			<app:orders-table list="${orders}"></app:orders-table>
			</c:when>
			
			<c:otherwise>
				<jsp:include page="/includes/no-data.jsp">
					<jsp:param value="Resent Orders" name="data"/>
				</jsp:include>
			</c:otherwise>
		</c:choose>
		
	</app:card>
</section>