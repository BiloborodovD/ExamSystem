<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@attribute name="successMessage" required="true"%>
<fmt:setLocale value="${lang}" scope="session"/>
<fmt:setBundle basename="resources" />

<c:if test="${not empty successMessage}">
<p id="error" style="color: green;"> <fmt:message key="${successMessage}"/></p></c:if>