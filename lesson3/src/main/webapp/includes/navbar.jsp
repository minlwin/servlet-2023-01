<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:url value="/home" var="home"></c:url>
<c:url value="/category" var="category"></c:url>
<c:url value="/division" var="division"></c:url>
<c:url value="/township" var="township"></c:url>

<nav class="navbar navbar-expand bg-danger navbar-dark">
	<div class="container">
		<span class="navbar-brand"><i class="bi bi-pin-angle"></i> Locations</span>
		
		<ul class="navbar-nav">
		
			<li class="nav-item">
				<a href="${ home }" class="nav-link ${ param.active eq 'home' ? 'active' : '' }">
					<i class="bi bi-house"></i> Home
				</a>
			</li>
			<li class="nav-item">
				<a href="${ category }" class="nav-link ${ param.active eq 'category' ? 'active' : '' }">
					<i class="bi bi-tags"></i> Categories
				</a>
			</li>
			<li class="nav-item">
				<a href="${ division }" class="nav-link ${ param.active eq 'division' ? 'active' : '' }">
					<i class="bi bi-pin-map"></i> Divisions
				</a>
			</li>
			<li class="nav-item">
				<a href="${ township }" class="nav-link ${ param.active eq 'township' ? 'active' : '' }">
					<i class="bi bi-mailbox"></i> Townships
				</a>
			</li>
		</ul>
	</div>
</nav>