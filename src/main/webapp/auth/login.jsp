<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@
taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Open ID Login</title>
</head>
<body>
	<p>Please use the form below to log into your account with OpenID.
	</p>
	<form action="/Challenge/login/openid" method="post">
		<input id="openid_identifier" name="openid_identifier" size="20"
			maxlength="100" type="hidden"
			value="https://www.appdirect.com/openid/id" /><input type="submit"
			value="Login" />
	</form>

</body>
</html>