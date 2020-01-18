<%@ include file="/WEB-INF/includes/directive.jspf" %>
<%@ page import="ua.nure.biloborodov.SummaryTask4.db.Role" %>
<!DOCTYPE html>
<fmt:message key="title.settings" var="title" />
<html>
<head>
<%@ include file="/WEB-INF/includes/headPro.jspf" %>
<link rel="stylesheet" type="text/css" media="screen" href="style/st4pro.css" />
<style>
#add {
	width: 200px;
}

</style>

</head>

<body>
	<t:header context="user" title="title.settings"/>
	<main>
		<div class="innertube">
			<div class="content">
				<%@ include file="/WEB-INF/includes/settings_form.jspf" %>
			</div>
		</div>
	</main>
	
	<c:choose>
		<c:when test="${sessionScope.currentUser.role eq Role.ADMIN}">
			<t:sidemenu role="admin"/>
		</c:when>
		<c:when test="${sessionScope.currentUser.role eq Role.STUDENT}">
			<t:sidemenu role="student"/>
		</c:when>
	</c:choose>
	
	<%@ include file="/WEB-INF/includes/footer.jspf" %>
</body>
</html>
	