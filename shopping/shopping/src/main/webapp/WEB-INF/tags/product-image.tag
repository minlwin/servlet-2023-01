<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<%@ attribute name="photos" required="true" %>
<%@ attribute name="cover" required="false" %>

<!-- Images -->
<c:choose>
	<c:when test="${not empty dto.photos}">
		
		<div class="row">
			<div class="col-auto">
				<div class="list d-flex flex-column">
					<c:forEach items="${dto.photos}" var="photo">
						<c:url value="/resources/photos/${photo.photo}" var="photoUrl"></c:url>
						<div data-product-photo="${photo.photo}" data-product-url="${photoUrl}" class="image-control">
							<c:if test="${photo.cover}">
								<div class="ribbon">
									<span>COVER</span>
								</div>
							</c:if>
							<img class="img-thumbnail img-fluid" src="${photoUrl}" alt="Product Photo" />
						</div>
					</c:forEach>
				</div>
			</div>
			
			<div class="col">
				<div class="cover">
					<c:url value="/resources/photos/${cover}" var="coverUrl"></c:url>
					<img id="productCoverImage" class="img-thumbnail img-fluid cover-image" alt="Cover Image" src="${coverUrl}">
				</div>
			</div>
		</div>
	
	</c:when>
	
	<c:otherwise>
		<div class="img img-thumbnail">
			<c:url value="/resources/photos/product-image.png" var="defaultImage"></c:url>
			<img class="productImage" alt="Product Image" src="${defaultImage}">							
		</div>
	</c:otherwise>
</c:choose>