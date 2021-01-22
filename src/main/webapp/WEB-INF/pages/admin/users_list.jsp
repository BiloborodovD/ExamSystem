<%@ include file="/WEB-INF/includes/directive.jspf" %>
<%@ page import="ua.nure.biloborodov.SummaryTask4.db.Role" %>

<!DOCTYPE html>
<fmt:message key="page.title.users.list" var="title"/>
<html>
<head>
<%@ include file="/WEB-INF/includes/headPro.jspf" %>
<link rel="stylesheet" type="text/css" media="screen" href="style/st4pro.css" />
<style>

button {
	font: 100% Trebuchet MS;
	color: white;
	padding: 14px 20px;
	margin: 4px 0;
	border: none;
	cursor: pointer;
	width: 100%;
}

#blocked {
	background-color: red;
}

#unblocked {
	background-color: green;
}
</style>


</head>

<body>

	<t:header context="user" title="page.title.users.list"/>
	<main>
		<div class="innertube">
			<div class="content">
				<div class="users_list">
					<table>
						<tr>
							<th><fmt:message key="label.login"/></th>
							<th><fmt:message key="label.first.name"/></th>
							<th><fmt:message key="label.last.name"/></th>
							<th><fmt:message key="label.email"/></th>
							<th><fmt:message key="label.status"/></th>
						</tr>
					<c:forEach var="elem" items="${requestScope.usersList}">
						<tr>
							<td>${elem.login}</td>
							<td>${elem.firstName}</td>
							<td>${elem.lastName}</td>
							<td>${elem.email}</td>
							<td>
								<form method="post" action="controller?command=blockUser">
									<input type="hidden" name="login" value="${elem.login}"> 
									<c:choose>
										<c:when test="${elem.role eq Role.ADMIN}">
											<p style="color: blue"><fmt:message key="role.admin"/></p>
										</c:when>
										<c:when test="${elem.role eq Role.STUDENT}">
											<button id="unblocked" type="submit"><fmt:message key="role.unblocked"/></button>
										</c:when>
										<c:when test="${elem.role eq Role.BLOCKED}">
											<button id="blocked" type="submit"><fmt:message key="role.blocked"/></button>
										</c:when>
									</c:choose>
								</form>
							</td>
						</tr>
					</c:forEach>
					</table>
				</div>
			</div>
		</div>
	</main>
	
	<t:sidemenu role="admin"/>
	<%@ include file="/WEB-INF/includes/footer.jspf" %>
</body>
</html>
	