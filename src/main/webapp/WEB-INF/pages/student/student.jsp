<%@ include file="/WEB-INF/includes/directive.jspf" %>

<!DOCTYPE html>
<fmt:message key="title.student" var="title" />
<html>
<head>
<%@ include file="/WEB-INF/includes/headPro.jspf" %>
<link rel="stylesheet" type="text/css" media="screen" href="style/st4pro.css" />
</head>

<body>
	<t:header context="user" title="page.title.student" user="${sessionScope.currentUser.login}"/>
	<main>
		<div class="innertube">
			<div class="content">
				<%@ include file="/WEB-INF/includes/personal_info.jspf" %>
				<div class="statictic_table">
					<table>
						<tr>
							<th><fmt:message key="table.subject.name"/></th>
							<th><fmt:message key="table.test.name"/></th>
							<th><fmt:message key="table.test.date"/></th>
							<th><fmt:message key="table.test.result"/>, %</th>
						</tr>
		
						<c:forEach var="elem" items="${sessionScope.currentUsersTests}">
						<tr>
							<td>${elem.subjectName}</td>
							<td>${elem.testName}</td>
							<td>${elem.testTime}</td>
							<td>${elem.testResult}</td>
						</tr>
					</c:forEach>
					</table>
				</div>
			</div>
		</div>
	</main>
	<t:sidemenu role="student"/>
	<%@ include file="/WEB-INF/includes/footer.jspf" %>
</body>
</html>
	