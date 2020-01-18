<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${lang}" scope="session"/>
<fmt:setBundle basename="resources" />
<%@attribute name="context" required="true"%>
<%@attribute name="title" required="true"%>
<%@attribute name="user" required="false"%>

<header id="header">
	<div id="logo">
		<h1><fmt:message key="title.logo"/></h1>
	</div>

	<c:if test="${context eq 'login'}">
		<div id="options">
			<p><fmt:message key="${title}"/></p>
			<a href="${pageContext.request.contextPath}/controller?command=registerPage">
				<fmt:message key="title.registration"/></a>
			<a href="${pageContext.request.contextPath}/controller?command=language&lang=en">EN</a>
			<a href="${pageContext.request.contextPath}/controller?command=language&lang=ru">RU</a>
		</div>
	</c:if>

	<c:if test="${context eq 'registration'}">
		<div id="options">
			<p><fmt:message key="${title}"/></p>
			<a href="${pageContext.request.contextPath}/login.jsp">
			<fmt:message key="button.login"/></a>
		</div>
	</c:if>

	<c:if test="${context eq 'user'}">
		<div id="options">
			<p><fmt:message key="${title}"/> ${user} </p>
			<a href="${pageContext.request.contextPath}/controller?command=logout">
			<fmt:message key="button.logout"/></a>
		</div>
	</c:if>
</header>