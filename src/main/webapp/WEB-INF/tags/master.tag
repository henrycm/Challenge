
<%@tag description="Master" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@attribute name="header" fragment="true"%>
<%@attribute name="footer" fragment="true"%>
<%@attribute name="title" required="false"%>

<c:set var="ctx" value="${pageContext.request.contextPath}"
	scope="session" />

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link href="${ctx}/resources/bootstrap-3.2.0-dist/css/bootstrap.min.css"
	rel="stylesheet">
<link href="${ctx}/resources/css/style.css" rel="stylesheet">

<jsp:invoke fragment="header" />
<title>Integration Challenge</title>
</head>
<body>
	<!-- Header -->
	<jsp:include page="/WEB-INF/pages/menu.jsp" />


	<div class="container-fluid">
		<div class="row-fluid">
			<div id="main-frame" class="span10">
				<jsp:doBody />
				<div id="messages" class="alert alert-danger" style="display: none;">
					<button type="button" class="close" data-dismiss="alert">&times;</button>
					<div></div>
				</div>
			</div>
		</div>
	</div>

	<script src="${ctx}/resources/js/jquery-1.11.1.min.js"></script>
	<script src="${ctx}/resources/bootstrap-3.2.0-dist/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		var ctx = "${ctx}";
	</script>
	<jsp:invoke fragment="footer" />
</body>
</html>
