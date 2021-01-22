<%@ include file="/WEB-INF/includes/directive.jspf" %>

<!DOCTYPE html>
<fmt:message key="page.title.subject.list" var="title" />
<html>
<head>
<%@ include file="/WEB-INF/includes/headPro.jspf" %>
<link rel="stylesheet" type="text/css" media="screen" href="style/st4pro.css" />

<style>

#subj {
  background-color: #779ecb;
  color: white;
  padding: 14px 25px;
  margin: 10px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
}

#subj:hover {
  background-color: #4477b2;
}
</style>

</head>

<body>

	<t:header context="user" title="page.title.subject.list"/>
	<main>
		<div class="innertube">
			<div class="content">
				<div class="subjects_list">
					<c:choose>
						<c:when test="${not empty sessionScope.errorMessage}">
							<p id="error" style="color: red;"> <fmt:message key="${errorMessage}"/></p>
							<c:remove var="errorMessage"/>
						</c:when>
						<c:when test="${empty sessionScope.errorMessage}">
							<p> <fmt:message key="message.choose.subject"/></p>
						</c:when>	
					</c:choose>
					<ul>
					<c:forEach var="elem" items="${sessionScope.subjectsList}">
						<li>
							<a id="subj" href="controller?command=getTests&subjectId=${elem.id}&order=default">${elem.name}</a> 
						</li>
					</c:forEach>
					</ul>
				</div>
			</div>
		</div>
	</main>
	
	<t:sidemenu role="student"/>
	<%@ include file="/WEB-INF/includes/footer.jspf" %>
</body>
</html>
	