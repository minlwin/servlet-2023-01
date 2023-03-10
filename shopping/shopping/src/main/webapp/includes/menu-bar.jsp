<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<div class="navbar navbar-expand navbar-dark bg-primary">
	<div class="container">
		<a class="navbar-brand"><i class="bi bi-shop"></i> Easy Shopping</a>
	
		<ul class="navbar-nav">
			<c:choose>
								
				<c:when test="${ not empty login }">
					<c:choose>
						<c:when test="${ login.role eq 'Owner' }">
							<!-- Onwer Menu -->
							<li class="nav-item">
								<a  class="nav-link">
									<i class="bi bi-cart3"></i> Orders 
								</a>
							</li>
							
							<li class="nav-item">
								<a  class="nav-link">
									<i class="bi bi-people"></i> Members 
								</a>
							</li>
							
							<li class="nav-item dropdown">
								<a class="nav-link dropdown-toggle" data-bs-toggle="dropdown">
									<i class="bi bi-database"></i> Master Data
								</a>
								
								<ul class="dropdown-menu">
									<li>
										<a class="dropdown-item">
											<i class="bi bi-tags"></i> Categories
										</a>
									</li>
									<li>
										<a class="dropdown-item">
											<i class="bi bi-gift"></i> Products
										</a>
									</li>
									<li>
										<a class="dropdown-item">
											<i class="bi bi-credit-card"></i> Payment Methods
										</a>
									</li>
								</ul>
							</li>
						
						</c:when>
					
						<c:when test="${ login.role eq 'Sales' }">
							<!-- Sales Menu -->
						
						</c:when>
						
						<c:when test="${ login.role eq 'Delivery' }">
							<!-- Delivery Menu -->
						
						</c:when>
					
						<c:when test="${ login.role eq 'Customer' }">
							<!-- Customer Menu -->
						
							<li class="nav-item">
								<a  class="nav-link">
									<i class="bi bi-cart4"></i> My Cart 0
								</a>
							</li>
						</c:when>
					
					</c:choose>

					<li class="nav-item">
						<a id="signOutMenu" class="nav-link">
							<i class="bi bi-door-closed"></i> Sign Out
						</a>
					</li>
				</c:when>
				
				<c:otherwise>
					<!-- Anonymous Menu -->
					<li class="nav-item">
						<a class="nav-link">
							<i class="bi bi-cart4"></i> My Cart 0
						</a>
					</li>
					
					<li class="nav-item">
						<a id="signUpMenu" class="nav-link">
							<i class="bi bi-door-open-fill"></i> Sign Up 
						</a>
					</li>

					<li class="nav-item">
						<a id="signInMenu" class="nav-link">
							<i class="bi bi-door-open"></i> Sign In 
						</a>
					</li>
				</c:otherwise>
			
			</c:choose>		
		</ul>
		
	</div>
</div>

<c:choose>
	<c:when test="${ empty login }">
		<jsp:include page="/dialogs/sign-in.jsp"></jsp:include>
		<jsp:include page="/dialogs/sign-up.jsp"></jsp:include>
	</c:when>
	
	<c:otherwise>
		<c:url value="/sign-out" var="signOutAction"></c:url>
		<form id="signOutForm" method="post" action="${ signOutAction }" class="d-none"></form>
	</c:otherwise>
</c:choose>

<c:url value="/resources/menu.js" var="menuJs"></c:url>
<script type="text/javascript" src="${ menuJs }"></script>
