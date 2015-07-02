<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:master title="Event logs">
	<jsp:attribute name="footer">
	<script>
		$(document).ready(function() {
			$("a").bind("click", function() {
				var id = $(this).attr("data-id");
				$.get(ctx + "/logs/" + id, function(data) {
					$("#modal-data").html(data);
					$("#modal").modal();
				});
			});
		});
	</script>
	</jsp:attribute>
	<jsp:body>
      <h3>Event logs</h3>
      <table class="table table-bordered">
         <thead>
            <tr class="info">
               <th>Date</th>
               <th>Url</th>
               <th></th>
            </tr>
         </thead>
         <tbody>
            <c:forEach var="u" items="${page.getContent()}">
               <tr>
                  <td><fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss" value="${u.date}" /></td>
                  <td>${u.url}</td>
                  <td><a id="cmdDetails" data-id="${u.id}"
							class="btn btn-sm btn-default" role="button" title="Details">Details</a></td>
               </tr>
            </c:forEach>
         </tbody>
      </table>
      <t:pagination url="${ctx}/logs/list" page="${page}"></t:pagination>
      
          <div class="modal fade" id="modal">
            <div class="modal-dialog">
               <div class="modal-content">
                  <div class="modal-header">
                     <button type="button" class="close"
							data-dismiss="modal" aria-label="Close">
                     <span aria-hidden="true">&times;</span>
                     </button>
                     <h4 class="modal-title">Details</h4>
                  </div>
                  <div class="modal-body">
                  <textarea id="modal-data" rows="30" cols="80"></textarea>
                  </div>
               </div>
            </div>
         </div>
   </jsp:body>
</t:master>

