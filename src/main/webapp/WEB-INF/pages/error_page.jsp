<%@ page isErrorPage="true" %>
<%@ page import="java.io.PrintWriter" %>

<%@ include file="/WEB-INF/includes/directive.jspf" %>

<!DOCTYPE html>
<c:set var="title" value="title.error" scope="page" />
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
</style>

</head>
<body>
	<header id="header">
		
		<div id="logo">
			<h1><fmt:message key="title.logo"/></h1>
		</div>
		
		<div id="options">
			<p><fmt:message key="title.error"/></p>
		</div>
	
	</header>

	<main>
		<div class="innertube">
			<div class="content">
				<div class="container">
					<c:set var="code"
						value="${requestScope['javax.servlet.error.status_code']}" />
					<c:set var="message"
						value="${requestScope['javax.servlet.error.message']}" />
					<c:set var="exception"
						value="${requestScope['javax.servlet.error.exception']}" />
						
					<c:if test="${not empty code}">
						<h3>Error code: ${code}</h3>
					</c:if>
						<c:if test="${not empty message}">
						<h3>${message}</h3>
					</c:if>
						<c:if test="${not empty exception}">
						<%exception.printStackTrace(new PrintWriter(out));%>
					</c:if>

						<%-- if we get this page using forward --%>
					<c:if test="${not empty requestScope.errorMessage}">
						<h3><fmt:message key="${requestScope.errorMessage}"/></h3>
					</c:if>
   				</div>
			</div>
		</div>
	</main>
<%@ include file="/WEB-INF/includes/footer.jspf" %>
</body>
</html>
