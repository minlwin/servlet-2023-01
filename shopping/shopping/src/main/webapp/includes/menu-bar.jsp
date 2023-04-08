<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:url value="/products" var="searchProduct"></c:url>
<c:url value="/public" var="homeUrl"></c:url>
<c:url value="/cart/checkout" var="myCartUrl"></c:url>
<c:url value="/members/order" var="myOrderUrl"></c:url>

<div class="navbar navbar-expand fixed-top navbar-dark bg-primary">
	<form action="${searchProduct}" class="container">
		<a href="${homeUrl}" class="navbar-brand"><i class="bi bi-shop"></i> Easy Shopping</a>
	
		<ul class="navbar-nav">
			<li class="nav-item d-flex me-4">
				<div class="input-group">
					<input name="keyword" type="text" class="form-control" placeholder="Search Keyword" />
					<button type="submit" class="input-group-text">
						<i class="bi bi-search"></i>
					</button>
				</div>
			</li>
			<c:choose>
								
				<c:when test="${ not empty login }">
					<c:choose>
						<c:when test="${ login.role eq 'Owner' or login.role eq 'Sale' }">
							<!-- Onwer Menu -->
							<li class="nav-item">
								<a href="${myOrderUrl}" class="nav-link ${ main eq 'order' ? 'active' : '' }" >
									<i class="bi bi-cart3"></i> Orders 
								</a>
							</li>
							
							<c:if test="${ login.role eq 'Owner' }">
								<li class="nav-item">
									<c:url value="/owner/members" var="memberMenu"></c:url>
									<a href="${memberMenu}" class="nav-link ${ main eq 'members' ? 'active' : '' }">
										<i class="bi bi-people"></i> Members 
									</a>
								</li>
							</c:if>
							
							<li class="nav-item dropdown">
								<a class="nav-link dropdown-toggle ${ main eq 'master' ? 'active' : '' }" data-bs-toggle="dropdown">
									<i class="bi bi-database"></i> Master Data
								</a>
								
								<ul class="dropdown-menu">
									<li>
										<c:url value="/sale/category" var="categoryMasterMenu"></c:url>
										<a href="${categoryMasterMenu}" class="dropdown-item ${ sub eq 'category' ? 'active' : '' }">
											<i class="bi bi-tags"></i> Categories
										</a>
									</li>
									<li>
										<c:url value="/sale/product" var="productMasterMenu"></c:url>
										<a href="${productMasterMenu}" class="dropdown-item ${ sub eq 'product' ? 'active' : '' }">
											<i class="bi bi-gift"></i> Products
										</a>
									</li>
									
									<c:if test="${ login.role eq 'Owner' }">
										<li>
											<c:url value="/owner/paid-info" var="paidMasterMenu"></c:url>
											<a href="${paidMasterMenu}" class="dropdown-item ${ sub eq 'paid' ? 'active' : '' }">
												<i class="bi bi-credit-card"></i> Payment Methods
											</a>
										</li>
									</c:if>
								</ul>
							</li>
						
						</c:when>
					
						
						<c:when test="${ login.role eq 'Delivery' }">
							<!-- Delivery Menu -->
							<li class="nav-item">
								<a href="${myOrderUrl}" class="nav-link ${ main eq 'order' ? 'active' : '' }" >
									<i class="bi bi-cart3"></i> Orders 
								</a>
							</li>
						
						</c:when>
					
						<c:when test="${ login.role eq 'Customer' }">
							<!-- Customer Menu -->
							<li class="nav-item">
								<a href="${myOrderUrl}" class="nav-link ${ main eq 'order' ? 'active' : '' }" >
									<i class="bi bi-cart3"></i> Orders 
								</a>
							</li>
							
							<c:if test="${ not empty cart and cart.size gt 0 }">
								<li class="nav-item">
									<a href="${myCartUrl}" class="nav-link">
										<i class="bi bi-cart4"></i> My Cart ${cart.size}
									</a>
								</li>
							</c:if>
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
					<c:if test="${ not empty cart and cart.size gt 0 }">
						<li class="nav-item">
							<a href="${myCartUrl}" class="nav-link">
								<i class="bi bi-cart4"></i> My Cart ${cart.size}
							</a>
						</li>
					</c:if>
					
					<li class="nav-item">
						<a class="nav-link signUpMenu">
							<i class="bi bi-door-open-fill"></i> Sign Up 
						</a>
					</li>

					<li class="nav-item">
						<a class="nav-link signInMenu">
							<i class="bi bi-door-open"></i> Sign In 
						</a>
					</li>
				</c:otherwise>
			
			</c:choose>		
		</ul>
		
	</form>
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

<c:url value="/resources/js/menu.js" var="menuJs"></c:url>
<script type="text/javascript" src="${ menuJs }"></script>
