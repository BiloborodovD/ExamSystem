<%@ include file="/WEB-INF/includes/directive.jspf" %>

<!DOCTYPE html>
<c:set var="title" value="Admin" />
<html>
<head>
<%@ include file="/WEB-INF/includes/headPro.jspf" %>
<link rel="stylesheet" type="text/css" media="screen" href="style/st4pro.css" />
</head>

<body>

	<t:header context="user" title="page.title.admin" user="${sessionScope.currentUser.login}"/>
	<main>
		<div class="innertube">
			<div class="content">
				<%@ include file="/WEB-INF/includes/personal_info.jspf" %>	
			</div>
		</div>
	</main>
	
	<t:sidemenu role="admin"/>
	<%@ include file="/WEB-INF/includes/footer.jspf" %>
</body>
</html>
	