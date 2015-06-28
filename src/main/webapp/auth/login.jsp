<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:master title="Login page">
	<jsp:body>
	<form action="${ctx}/login/openid" method="post">
		<input id="openid_identifier" name="openid_identifier" type="hidden"
				value="https://www.appdirect.com/openid/id" />
			
			<img alt="" src="${ctx}/resources/img/appdirect.jpg">
			<input type="submit" class="btn btn-default" value="Login" />
	</form>
	</jsp:body>
</t:master>