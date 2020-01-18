<%@ include file="/WEB-INF/includes/directive.jspf" %>
<%@ taglib uri="ua.nure.biloborodov.newTags" prefix="nt"%>

<!DOCTYPE html>
<fmt:message key="page.title.tests.list" var="title"/>
<html>
<head>
<%@ include file="/WEB-INF/includes/headPro.jspf" %>
<link rel="stylesheet" type="text/css" media="screen" href="style/st4pro.css" />
</head>

<body>

	<t:header context="user" title="page.title.tests.list"/>
	<main>
		<div class="innertube">
			<div class="content">
				<a href="${pageContext.request.contextPath}/controller?command=addTestInfo"><fmt:message key="link.new.test"/></a>
				<div class="tests_list">
					<table>
						<tr>
							<th><fmt:message key="table.test.name"/></th>
							<th><fmt:message key="table.test.difficulty"/></th>
							<th><fmt:message key="table.test.questions.count"/></th>
							<th><fmt:message key="table.test.time"/>, min</th>
							<th></th>
							<th></th>
						</tr>
					<c:forEach var="elem" items="${sessionScope.testsList}">
						<tr>
							<td>${elem.name}</td>
							<td>${elem.difficulty.name}</td>
							<td>${elem.questionsCount}</td>
							<td><nt:timeMin time="${elem.time}"/></td>
							<td>
								<form action="controller">
									<input type="hidden" name="test_id" value="${elem.id}"> 
									<input type="hidden" name="command" value="editTestInfo"> 
									<button id="edit" type="submit"><fmt:message key="button.edit"/></button>
								</form>
							</td>
							<td>
								<form method="post" action="controller?command=deleteTest">
									<input type="hidden" name="test_id" value="${elem.id}"> 
									<button id="delete" type="submit"><fmt:message key="button.delete"/></button>
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
	