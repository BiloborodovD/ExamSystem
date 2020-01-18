<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${lang}" scope="session"/>
<fmt:setBundle basename="resources" />
<%@attribute name="role" required="true"%>

<nav id="left">
	<div class="innertube">
	<h2><fmt:message key="menu.label"/></h2>
		<c:if test="${role eq 'student'}">
			<ul>
				<li><a href="${pageContext.request.contextPath}/controller?command=studentProfile"><fmt:message key="menu.profile"/></a></li>
				<li><a href="${pageContext.request.contextPath}/controller?command=getSubjects"><fmt:message key="menu.tests.choose"/></a></li>
				<li><a href="${pageContext.request.contextPath}/controller?command=getSettings"><fmt:message key="menu.settings"/></a></li>
			</ul>
		</c:if>

		<c:if test="${role eq 'admin'}">
			<ul>
				<li><a href="${pageContext.request.contextPath}/controller?command=adminProfile"><fmt:message key="menu.profile"/></a></li>
				<li><a href="${pageContext.request.contextPath}/controller?command=usersList"><fmt:message key="menu.users"/></a></li>
				<li><a href="${pageContext.request.contextPath}/controller?command=getSubjectsEditor"><fmt:message key="menu.tests.editor"/></a></li>
				<li><a href="${pageContext.request.contextPath}/controller?command=getSettings"><fmt:message key="menu.settings"/></a></li>
			</ul>
		</c:if>
	</div>
</nav>



	

	