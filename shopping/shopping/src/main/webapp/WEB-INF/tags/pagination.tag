<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>    

<%@ attribute name="dataModel" required="true" type="com.jdc.shop.model.dto.page.Pagination" %>
<%@ attribute name="pageSizeList" required="true" type="java.util.List" %>
<%@ attribute name="formId" required="true" %>

<section id="pagination" data-search-form="${formId}" class="row pt-2">


<div class="col-auto">
	<!-- Page Size Select -->
	<select id="pageSizeSelect" class="form-select">
		<c:forEach var="size" items="${pageSizeList}">
			<option value="${size}" ${size eq param.size ? "selected='selected'" : ""}>${size}</option>
		</c:forEach>
	</select>
</div>

<div class="col-auto">
	<ul class="pagination">
	<c:if test="${dataModel.needToShow}">	
		
		<c:if test="${dataModel.needToShowFirst}">
			<!-- First -->
			<li class="page-item">
				<a data-page-num="1" class="page-link">
					<i class="bi bi-skip-backward"></i>
				</a>
			</li>
		
		</c:if>
		
		<!-- Page Links -->
		<c:forEach items="${dataModel.pages}" var="page">
			<li class="page-item ${page eq dataModel.currentPage ? 'active' : ''}">
				<a data-page-num="${page}" class="page-link">${page}</a>
			</li>
		</c:forEach>
		
		<c:if test="${dataModel.needToShowLast}">
			<!-- Last -->
			<li class="page-item">
				<a data-page-num="${dataModel.lastPage}" class="page-link">
					<i class="bi bi-skip-forward"></i>
				</a>
			</li>
		</c:if>
		
	
	</c:if>	
	</ul>
</div>


<!-- Total Information -->
<div class="col-auto">
	<div class="form-control">
		Total ${dataModel.totalCount}
	</div>
</div>

<c:url value="/resources/js/pagination.js" var="pagerJS"></c:url>
<script type="text/javascript" src="${pagerJS}"></script>

</section>