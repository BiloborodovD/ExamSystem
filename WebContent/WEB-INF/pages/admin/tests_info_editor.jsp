<%@ include file="/WEB-INF/includes/directive.jspf" %>
<%@ taglib uri="ua.nure.biloborodov.newTags" prefix="nt"%>
<!DOCTYPE html>
<fmt:message key="page.title.test.info.editor" var="title"/>
<html>
<head>
<%@ include file="/WEB-INF/includes/headPro.jspf" %>
<link rel="stylesheet" type="text/css" media="screen" href="style/st4pro.css" />
</head>

<body>

	<t:header context="user" title="page.title.test.info.editor"/>
	<main>
		<div class="innertube">
			<div class="content">
				<form name="test" method="post" action="controller?command=submitTestInfo">
					<fieldset>
						<legend><fmt:message key="label.test.info"/></legend>
						<br />
						 <fmt:message key="table.test.name"/>: <input type="text" name="test_name" value="${sessionScope.currentTest.name}">
						<br />
						<br />
						<fmt:message key="table.test.difficulty"/>: 
						<select name="diff_level">
							<c:forEach var="pt" items="${sessionScope.difficulties}">
								<option ${pt eq sessionScope.currentTest.difficulty ? 'selected' : ''} value="${pt.name}"><fmt:message key="${pt.name}"/></option>
							</c:forEach>
						</select> 
						<br />
						<br />
						<fmt:message key="table.test.time"/>: <input type="text" name="test_time" value="<nt:timeMin time='${sessionScope.currentTest.time}'/>"><br />
						<br />
						<button type="submit"><fmt:message key="button.submit"/></button>
					</fieldset>
				</form>
			</div>
		</div>
	</main>
	
	<t:sidemenu role="admin"/>
	<c:if test="${not empty sessionScope.currentTest}">
		<%@ include file="/WEB-INF/includes/editor_menu.jspf" %>	
	</c:if>
	<%@ include file="/WEB-INF/includes/footer.jspf" %>
</body>
</html>
	