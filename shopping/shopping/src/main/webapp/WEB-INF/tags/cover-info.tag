<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ tag body-content="tagdependent" %>
<%@ attribute name="icon"  %>
<div>
	<i class="bi ${icon}" style="font-size: 8rem"></i>
	
	<jsp:doBody></jsp:doBody>
</div>				
