<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Scope Demo</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>

</head>
<body>

	<div class="container pt-4">
		
		<h1>Scope Demo</h1>
		
		<div class="row mb-4">
			<div class="col">
				<div class="card">
					<div class="card-header">Request Scope</div>
					<div class="card-body">
						${ counter.value }
					</div>
				</div>
			</div>
			
			<div class="col">
				<div class="card">
					<div class="card-header">Session Scope</div>
					<div class="card-body">
						${ sessionScope.counter.value }
					</div>
				</div>
			</div>
			
			<div class="col">
				<div class="card">
					<div class="card-header">Application Scope</div>
					<div class="card-body">
						${ applicationScope.counter.value }
					</div>
				</div>
			</div>
		</div>
		
		<c:url value="/countUp" var="up" />
		<a href="${ up }" class="btn btn-outline-primary">
			Count Up
		</a>
	
	</div>

</body>
</html>