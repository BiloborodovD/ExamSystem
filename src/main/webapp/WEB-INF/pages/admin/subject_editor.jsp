<%@ include file="/WEB-INF/includes/directive.jspf" %>
<%@ page import="ua.nure.biloborodov.SummaryTask4.db.Language" %>

<!DOCTYPE html>
<fmt:message key="page.title.subject.editor" var="title"/>
<html>
<head>
<%@ include file="/WEB-INF/includes/headPro.jspf" %>
<link rel="stylesheet" type="text/css" media="screen" href="style/st4pro.css" />
<style type="text/css">
button {
	font: 100% Trebuchet MS;
	color: white;
	padding: 7px 10px;
	margin: 4px 0;
	border: none;
	cursor: pointer;
	width: 100%;
}

#delete {
	background-color: red;
}

#add {
	background-color: green;
}

#edit {
	background-color: blue;
}

</style>

</head>

<body>

	<t:header context="user" title="page.title.subject.editor"/>
	<main>
		<div class="innertube">
			<div class="content">
				<div class="subjects_list">
				
					<table>
						<tr>
							<th><fmt:message key="table.subject.name"/></th>
							<th><fmt:message key="table.subject.language"/></th>
							<th></th>
							<th></th>
						</tr>
						<c:forEach var="elem" items="${sessionScope.subjectsList}">
						<tr>
							<td><c:out value="${elem.name}"/></td>
							<td><c:out value="${elem.langId}"/></td>
							<td>
								<form action="controller">
									<input type="hidden" name="command" value="getAdminTests"> 
									<input type="hidden" name="subject_id" value="${elem.id}"> 
									<button id="edit" type="submit"><fmt:message key="button.edit"/></button>
								</form>
							</td>
							<td>
								<form method="POST" action="controller?command=deleteSubject">
									<input type="hidden" name="subject_id" value="${elem.id}"> 
									<button id="delete" type="submit"><fmt:message key="button.delete"/></button>
								</form>
							</td>
						</tr>
						</c:forEach>
					</table>
				</div>
				<div id="new">
					<form name="subject" method="post" action="controller?command=addNewSubject">
					<fieldset>
						<legend><fmt:message key="label.create.subject"/></legend>
						<br />
						<t:success successMessage="${sessionScope.successMessage}"/>
						<c:remove var="successMessage"/>
						<br />
						<fmt:message key="label.subject.name"/>: <input type="text" name="subject_name">
						<br />
						<fmt:message key="label.language"/>: 
						<select name="language">
							<option value="${Language.EN.name}">${Language.EN.name}</option>
							<option value="${Language.RU.name}">${Language.RU.name}</option>
						</select> 
						<br />
						<fmt:message key="button.submit" var="submSubj"/>
						<p><button id="submit" type="submit" >${submSubj}</button></p>
					</fieldset>
				</form>
				</div>
			</div>
		</div>
	</main>
		
	<t:sidemenu role="admin"/>
	<%@ include file="/WEB-INF/includes/footer.jspf"%>
</body>
</html>
	