<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@attribute name="errorMessage" required="true"%>
<fmt:setLocale value="${lang}" scope="session"/>
<fmt:setBundle basename="resources" />

<c:if test="${not empty errorMessage}">
<p id="error" style="color: red;"> <fmt:message key="${errorMessage}"/></p></c:if>
