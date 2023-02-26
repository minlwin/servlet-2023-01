<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="/includes/heading.jsp">
	<jsp:param value="Divisions" name="title"/>
</jsp:include>	
</head>
<body>

	<jsp:include page="/includes/navbar.jsp">
		<jsp:param value="division" name="active"/>
	</jsp:include>

</body>
</html>