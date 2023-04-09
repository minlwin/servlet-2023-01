<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ attribute name="dto" required="true" type="com.jdc.shop.model.dto.vo.ProductPublicVo" %>

<div class="card">
	
	<c:url value="/resources/photos/${dto.coverImage}" var="coverUrl"></c:url>
	<img src="${coverUrl}" alt="Cover Image" class="card-image-top image-responsive public-product-image" />
	
	<div class="card-footer">
		<h5 class="card-title text-truncate">
			${dto.product.name}
		</h5>
		<div class="d-flex justify-content-between">
			<span>
				${dto.product.price} MMK
			</span>
			
			<c:url value="/products" var="detailsLink">
				<c:param name="product-id" value="${dto.product.id}"></c:param>
			</c:url>
			<a href="${detailsLink}" class="btn-link">
				<i class="bi bi-send"></i>
			</a>
		</div>
	</div>
</div>