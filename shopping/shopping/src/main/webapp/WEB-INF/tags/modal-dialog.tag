<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="modelId" type="java.lang.String" required="true" %>
<%@ tag body-content="scriptless" %>

<div class="modal fade" id="${modelId}">
	<div class="modal-dialog">
		<div class="modal-content">
			<jsp:doBody></jsp:doBody>
		</div>
	</div>
</div>
