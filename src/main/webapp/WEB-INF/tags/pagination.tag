<%@tag description="Pagination" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@attribute name="page" type="org.springframework.data.domain.Page"
	required="true"%>
<%@attribute name="label"%>
<%@attribute name="url" required="true"%>

<c:if test="${page.totalPages > 1}">
	<c:choose>
		<c:when test="${fn:indexOf(url, '?') gt 0 }">
			<c:set var="url_" value="${url}" />
		</c:when>
		<c:otherwise>
			<c:set var="url_" value="${url}?1" />
		</c:otherwise>
	</c:choose>
	<nav>
		<ul class="pagination no-margin pull-right">
			<c:if test="${not empty label}">
				<li><span>${label}</span></li>
			</c:if>
			<c:choose>
				<c:when test="${page.number == 0}">
					<li class="disabled"><span>&laquo;</span></li>
				</c:when>
				<c:otherwise>
					<li><a href="${url_}&pageNumber=0">&laquo;</a></li>
				</c:otherwise>
			</c:choose>
			<c:forEach var="i" begin="1" end="${page.totalPages}">
				<c:choose>
					<c:when test="${page.number == i -1}">
						<li class="active"><span><c:out value="${i}" /></span></li>
					</c:when>
					<c:otherwise>
						<li><a href="${url_}&pageNumber=${i-1}">${i}</a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<c:choose>
				<c:when test="${page.number == page.totalPages -1}">
					<li class="disabled"><span>&raquo;</span></li>
				</c:when>
				<c:otherwise>
					<li><a href="${url_}&pageNumber=${page.totalPages-1}">&raquo;</a></li>
				</c:otherwise>
			</c:choose>
		</ul>
		<nav>
</c:if>
