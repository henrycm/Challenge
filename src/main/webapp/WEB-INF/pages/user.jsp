<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
			<c:forEach var="u" items="${page.getContent()}">
				<tr>
					<td>${u.id}</td>
					<td>${u.firstName}${u.lastName}</td>
					<td>${u.email}</td>
					<td>${u.openId}</td>
					<td>
					<c:if test="${not empty u.account.id}">
						<a class="btn btn-sm btn-default" data-toggle="popover"
									title="Details" data-trigger="focus" role="button" tabindex="0"
									data-content="Status:${u.account.status} Edition:${u.account.editionCode} Duration: ${u.account.pricingDuration}">${u.account.editionCode}</a>
					</c:if>
						</td>
					<td><fmt:formatDate pattern="yyyy-MM-dd hh:mm"
								value="${u.lastUpdate}" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<t:pagination url="${ctx}/list" page="${page}"></t:pagination>

	</jsp:body>
</t:master>