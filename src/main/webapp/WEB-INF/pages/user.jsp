<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:master title="User page">
	<jsp:body>
	<h3>User List</h3>
	<table class="table table-bordered">
		<thead>
			<tr class="info">
				<th>Id</th>
				<th>Name</th>
				<th>Email</th>
				<th>Open ID</th>
				<th>Account</th>
				<th>Last update</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="u" items="${users}">
				<tr>
					<td>${u.id}</td>
					<td>${u.firstName}${u.lastName}</td>
					<td>${u.email}</td>
					<td>${u.openId}</td>
					<td>${u.account.accountIdentifier}</td>
					<td>${u.lastUpdate}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	</jsp:body>
</t:master>