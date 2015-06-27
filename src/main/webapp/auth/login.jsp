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
<title>Insert title here</title>
</head>
<body>

	<h1>Or, Log Into Your Account with OpenID</h1>
	<p>Please use the form below to log into your account with OpenID.
	</p>
	<form action="/Auditing/j_spring_openid_security_check" method="post">
		<label for="openid_identifier">Login</label>: <input
			id="openid_identifier" name="openid_identifier" size="20"
			maxlength="100" type="text" /> <img src="images/openid.png"
			alt="OpenID" /> <br /> <input type="submit" value="Login" />
	</form>

</body>
</html>