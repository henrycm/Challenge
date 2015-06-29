<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<sec:authorize access="isAuthenticated()">
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">Challenge integration</a>
			</div>

			<div class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li><a href="${ctx}/list">User List</a></li>
					<li><a href="${ctx}/logs">Event logs</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="#">User:<sec:authentication
								property="principal.username" /></a></li>
					<li role="separator" class="divider"></li>
					<li><a href="${ctx}/logout">LogOut</a></li>
				</ul>
			</div>
		</div>
	</nav>

</sec:authorize>