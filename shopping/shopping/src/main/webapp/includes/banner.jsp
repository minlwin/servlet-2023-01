<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<div id="topBanner" class="carousel slide" data-bs-ride="carousel">
	<div class="carousel-indicators">
		<button type="button" data-bs-target="#topBanner" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
		<button type="button" data-bs-target="#topBanner" data-bs-slide-to="1" aria-label="Slide 2"></button>
		<button type="button" data-bs-target="#topBanner" data-bs-slide-to="2" aria-label="Slide 3"></button>
		<button type="button" data-bs-target="#topBanner" data-bs-slide-to="3" aria-label="Slide 4"></button>
	</div>
	
	<div class="carousel-inner">
		<div class="carousel-item active">
			<c:url value="/resources/photos/banner-1.png" var="image1"></c:url>
			<img class="d-block w-100" src="${image1}" alt="Cover Image" />
		</div>
		<div class="carousel-item">
			<c:url value="/resources/photos/banner-2.png" var="image2"></c:url>
			<img class="d-block w-100" src="${image2}" alt="Cover Image" />
		</div>
		<div class="carousel-item">
			<c:url value="/resources/photos/banner-3.png" var="image3"></c:url>
			<img class="d-block w-100" src="${image3}" alt="Cover Image" />
		</div>
		<div class="carousel-item">
			<c:url value="/resources/photos/banner-4.png" var="image4"></c:url>
			<img class="d-block w-100" src="${image4}" alt="Cover Image" />
		</div>
	</div>
	
	<button class="carousel-control-prev" type="button" data-bs-target="#topBanner" data-bs-slide="prev">
	    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
	    <span class="visually-hidden">Previous</span>
  	</button>
  	<button class="carousel-control-next" type="button" data-bs-target="#topBanner" data-bs-slide="next">
	    <span class="carousel-control-next-icon" aria-hidden="true"></span>
	    <span class="visually-hidden">Next</span>
  	</button>
	
</div>