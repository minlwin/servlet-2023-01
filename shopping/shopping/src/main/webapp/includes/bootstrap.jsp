<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>    

<c:url value="/resources/bootstrap/css/bootstrap.min.css" var="bootstrapCss"></c:url>
<link href="${bootstrapCss}" rel="stylesheet" >

<c:url value="/resources/bootstrap/js/bootstrap.bundle.min.js" var="bootstrapJS"></c:url>
<script src="${bootstrapJS}" ></script>

<c:url value="/resources/bootstrap-icons/bootstrap-icons.css" var="bootstrapIcons"></c:url>
<link rel="stylesheet" href="${bootstrapIcons}">

<c:url var="styles" value="/resources/style.css"></c:url>
<link rel="stylesheet" href="${styles}" />