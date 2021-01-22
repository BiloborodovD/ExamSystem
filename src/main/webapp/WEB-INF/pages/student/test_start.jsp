<%@ include file="/WEB-INF/includes/directive.jspf" %>
<%@ taglib uri="ua.nure.biloborodov.newTags" prefix="nt"%>

<!DOCTYPE html>
<fmt:message key="page.title.test.start" var="title" />
<html>
<head>
<%@ include file="/WEB-INF/includes/headPro.jspf" %>
<link rel="stylesheet" type="text/css" media="screen" href="style/st4pro.css" />
<style>
#edit {
	width: 200px;
}

</style>

</head>

<body>

	<t:header context="user" title="page.title.test.start"/>
	<main>
		<div class="innertube">
			<div class="content">
				
				<div class="test_info">
					<p><fmt:message key="table.test.name"/>:<c:out value="${sessionScope.currentTest.name}"/> </p>
					<p><fmt:message key="table.test.difficulty"/>: <c:out value="${sessionScope.currentTest.difficulty}"/></p>
					<p><fmt:message key="table.test.time"/>: <nt:timeMin time="${sessionScope.currentTest.time}"/></p>
				</div>
				<div>
					<form action="controller" method="post">
						<input type="hidden" name="command" value="runTest">
						<button id="edit" type="submit"><fmt:message key="button.test.run"/></button>
					</form>
				</div>
			</div>
		</div>
	</main>
	<t:sidemenu role="student"/>
	<%@ include file="/WEB-INF/includes/footer.jspf" %>
</body>
</html>
	