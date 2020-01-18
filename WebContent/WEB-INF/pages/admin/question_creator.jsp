<%@ include file="/WEB-INF/includes/directive.jspf" %>

<!DOCTYPE html>

<fmt:message key="page.title.questions.editor" var="title"/>
<html>
<head>
<%@ include file="/WEB-INF/includes/headPro.jspf" %>
<link rel="stylesheet" type="text/css" media="screen" href="style/st4pro.css" />
<style>

#answerd {
	font: 80% Trebuchet MS;
	background-color: red;
	width:100px;
	padding: 5px;
}

#answera {
	font: 80% Trebuchet MS;
	background-color: green;
	width:100px;
	padding: 5px;
}

</style>

</head>

<body>

	<t:header context="user" title="page.title.questions.editor"/>
	<main>
		<div class="innertube">
			<div class="content">
				<form method="post">
				
					<label><fmt:message key="label.question"/>:</label><br/>
					<textarea name="question_content" rows="3" cols="50">${sessionScope.currentQuestion.content}</textarea>
					<br/>
					<br/>
					<ul>
					<c:forEach var="elem" items="${sessionScope.currentAnswers}">
						<li>
						<input type="hidden" name="answer_id" value="${elem.id}"> 
						<input type="checkbox" name="correct"  ${elem.correct eq "true" ? 'checked' : ''} value="${currentAnswers.indexOf(elem)}">
						<textarea rows="5" cols="50" name="answer_content">${elem.content}</textarea>
						<button id="answerd" type="submit" formaction="controller?command=deleteAnswer&delete_answer=${currentAnswers.indexOf(elem)}">
							<fmt:message key="button.delete.answer"/>
						</button>
						</li>
					</c:forEach>
					<li><button id="answera" type="submit" formaction="controller?command=addAnswer"><fmt:message key="button.add.answer"/></button></li>
					</ul>
					
					<button id="add" type="submit" formaction="controller?command=submitQuestion"><fmt:message key="button.submit.question"/></button>
					<button id="delete" type="submit" formaction="controller?command=deleteQuestion"><fmt:message key="button.delete.question"/></button>
				</form>
			</div>
		</div>
	</main>
	
<t:sidemenu role="admin"/>
	
	<%@ include file="/WEB-INF/includes/editor_menu.jspf" %>	
	
	<%@ include file="/WEB-INF/includes/footer.jspf" %>
</body>
</html>
	