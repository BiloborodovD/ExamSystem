<%@ include file="/WEB-INF/includes/directive.jspf" %>

<!DOCTYPE html>
<fmt:message key="page.title.test.run" var="title" />
<html>
<head>
<%@ include file="/WEB-INF/includes/headPro.jspf" %>
<link rel="stylesheet" type="text/css" media="screen" href="style/st4pro.css" />
<style>
#ques_cont {
	background-color: #fdfd96;
	padding: 15px;
	border: 1px solid grey;
}
</style>
</head>

<body>

	<t:header context="user" title="page.title.test.run"/>
	<main>
		<div style="margin-left: 100px; margin-right: 100px;" class="innertube">
			<div class="content">
				<form method="post" action="controller">
					<input type="hidden" name="command" value="finishTest">
					<c:set var="quesCount" value="0" />
					<c:forEach var="ques" items="${sessionScope.mapTest}">
						<fieldset style="margin-top: 10px; margin-bottom: 10px;">
   						<div class="question">
   							<label><fmt:message key="label.question"/> ${quesCount + 1}:</label><br/>
   							<p id="ques_cont"> ${ques.key.content}</p>
   							<ul >
   							<c:forEach var="answer" items="${ques.value}">
   								<li style="text-align: left;"><input type="checkbox" name="answer_correct" value="${quesCount}_${ques.value.indexOf(answer)}"> ${answer.content} </li>
   							</c:forEach>
  							 </ul>
  							 <c:set var="quesCount" value="${quesCount + 1}"/>
  						</div>
  						</fieldset>
					</c:forEach>
					<button type="submit"><fmt:message key="button.test.submit"/></button>
				</form>
			</div>
		</div>
	</main>
	
	<nav id="right">
			<div style="text-align: center;" class="innertube">
			<h2><fmt:message key="header.time"/></h2>
			<h1 id="demo" style="color: red"></h1>
<script>
	var countDownDate = new Date().getTime() + ${sessionScope.timeTestRemind};
	var x = setInterval(function() {
	var now = new Date().getTime();
	var distance = countDownDate - now;
	var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
  	var seconds = Math.floor((distance % (1000 * 60)) / 1000);
	document.getElementById("demo").innerHTML = minutes + "m " + seconds + "s ";
	if (distance < 0) {
    	clearInterval(x);
    	document.getElementById("demo").innerHTML = "EXPIRED";
    	window.location.href = "${pageContext.request.contextPath}/controller?command=finishTest";
  		}
	}, 1000);
</script>
			</div>
		</nav>
	<t:sidemenu role="student"/>
	<%@ include file="/WEB-INF/includes/footer.jspf" %>
</body>
</html>
	