<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<div class="navbar navbar-expand navbar-dark bg-primary">
	<div class="container">
		<span class="navbar-brand"><i class="bi bi-shop"></i> Easy Shopping</span>
	
		<ul class="navbar-nav">
			<c:choose>
				
				<c:when test="${ not empty login and login.role eq 'Owner' }">
				<!-- Onwer Menu -->
				
				</c:when>
			
				<c:when test="${ not empty login and login.role eq 'Sales' }">
				<!-- Sales Menu -->
				
				</c:when>
				
				<c:when test="${ not empty login and login.role eq 'Delivery' }">
				<!-- Delivery Menu -->
				
				</c:when>
			
				<c:when test="${ not empty login and login.role eq 'Customer' }">
				<!-- Customer Menu -->
				
				</c:when>
				
				<c:otherwise>
					<!-- Anonymous Menu -->
					<li class="nav-item">
						<a href="#" class="nav-link">
							My Cart <i class="bi bi-cart4"></i> 0
						</a>
					</li>
					
					<li class="nav-item">
						<a href="#" class="nav-link">
							Sign Up <i class="bi bi-door-open-fill"></i>
						</a>
					</li>

					<li class="nav-item">
						<a id="signInMenu" href="#" class="nav-link">
							Sign In <i class="bi bi-door-open"></i>
						</a>
					</li>
				</c:otherwise>
			
			</c:choose>		
		</ul>
		
	</div>
</div>


