<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:master title="Login page">
	<jsp:body>
	<form action="${ctx}/login/openid" method="post">
		<input id="openid_identifier" name="openid_identifier" type="hidden"
				value="https://www.appdirect.com/openid/id" />
				<input name="realm" type="hidden"
				value="http://52.26.104.124:8080/Challenge/*" />
			
			<img alt="" src="${ctx}/resources/img/appdirect.jpg">
			<input type="submit" class="btn btn-default" value="Login" />
	</form>
	</jsp:body>
</t:master>