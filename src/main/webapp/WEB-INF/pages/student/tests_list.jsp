<%@ include file="/WEB-INF/includes/directive.jspf" %>
<%@ taglib uri="ua.nure.biloborodov.newTags" prefix="nt"%>

<!DOCTYPE html>
<fmt:message key="page.title.tests.list" var="title" />
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
				<div id="ordering">
					<fmt:message key="label.order.by" />:<br/>
					<fmt:message key="table.test.name"/> <a href="controller?command=getTests&subjectId=${sessionScope.currentSubject}&order=name"><fmt:message key="label.order.by.name" /></a>
					 <a href="controller?command=getTests&subjectId=${sessionScope.currentSubject}&order=nameDesc"><fmt:message key="label.order.by.name.desc" /></a><br/>
					<fmt:message key="table.test.difficulty"/>
					<a href="controller?command=getTests&subjectId=${sessionScope.currentSubject}&order=difficulty"><fmt:message key="label.order.by.diff" /></a>
					<a href="controller?command=getTests&subjectId=${sessionScope.currentSubject}&order=difficultyDesc"><fmt:message key="label.order.by.diff.desc" /></a><br/>
					<fmt:message key="table.test.questions.count"/>
					<a href="controller?command=getTests&subjectId=${sessionScope.currentSubject}&order=questions">1...9</a>
					<a href="controller?command=getTests&subjectId=${sessionScope.currentSubject}&order=questionsDesc">9...1</a>
				</div>
				<div class="tests_table">
					<table>
						<tr>
							<th><fmt:message key="table.test.name"/></th>
							<th><fmt:message key="table.test.difficulty"/></th>
							<th><fmt:message key="table.test.time"/></th>
							<th><fmt:message key="table.test.questions.count"/></th>
							<th></th>
						</tr>
					<c:forEach var="elem" items="${currentTests}">
						<tr>
							<td>${elem.name}</td>
							<td>${elem.difficulty.name}</td>
							<td><nt:timeMin time="${elem.time}"/></td>
							<td>${elem.questionsCount}</td>
							<td>
								<form action="controller">
									<input type="hidden" name="command" value="startTest">
									<input type="hidden" name="test_id" value="${elem.id}">
									<button id="edit" type="submit"><fmt:message key="button.test.start"/></button>
								</form>
							</td>
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
	