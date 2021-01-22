<%@ include file="/WEB-INF/includes/directive.jspf" %>

<!DOCTYPE html>
<fmt:message key="title.login" var="title" />
<html>
<head>
<%@ include file="/WEB-INF/includes/head.jspf" %>
<link rel="stylesheet" type="text/css" media="screen" href="style/st4.css" />
<style type="text/css">
main {
	overflow: hidden;
}

.content {
	font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
	border-collapse: collapse;
	width: 100%;
}

form {
	border: 3px solid #f1f1f1;
}

.container {
	overflow: hidden;
	padding-left: 200px;
	padding-right: 200px;
}

#error{
color: red;
}
</style>
</head>

<body>
	<t:header context="login" title="title.login"/>
	<main>
		<div class="innertube">
			<div class="content">
				<form id="login_form" action="controller?command=login" method="post">
					<div class="imgcontainer">
						<img src="images/graduarse.png" alt="Avatar" class="avatar">
					</div>
					<div class="container">
						<t:error errorMessage="${sessionScope.errorMessage}"/>
						<c:remove var="errorMessage"/>
						<t:success successMessage="${sessionScope.successMessage}"/>
						<c:remove var="successMessage"/>
						<label ><b><fmt:message key="label.login"/></b></label>
						<input type="text" name="login">
						<label ><b><fmt:message key="label.password"/></b></label>
						<input type="password" name="password" >
						<button type="submit"><fmt:message key="button.sign.in"/></button>
					</div>
				</form>
			</div>
		</div>
	</main>
<%@ include file="/WEB-INF/includes/footer.jspf" %>
</body>
</html>
