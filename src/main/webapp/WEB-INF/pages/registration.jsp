<%@ include file="/WEB-INF/includes/directive.jspf" %>

<!DOCTYPE html>
<fmt:message key="title.registration" var="title" />
<html>
<head>
<%@ include file="/WEB-INF/includes/head.jspf" %>
<link rel="stylesheet" type="text/css" media="screen" href="style/st4.css" />
<style type="text/css">

		form {border: 3px solid #f1f1f1;
			padding-left: 50px;
			padding-right: 50px;
		}

		form p {
			margin: 0;
		}
		form h3 {
			margin: 0;
		}

		input[type=text], input[type=password] {
		  width: 100%;
		  padding: 6px 10px;
		  margin: 0;
		  display: inline-block;
		  border: 1px solid #ccc;
		  box-sizing: border-box;
		}

		button {
		  background-color: #4CAF50;
		  color: white;
		  padding: 14px 20px;
		  margin: 8px 0;
		  border: none;
		  cursor: pointer;
		  width: 100%;
		}

		button:hover {
		  opacity: 0.8;
		}
		</style>
</head>

<body>

	<t:header context="registration" title="title.registration"/>
	
	<main>
		<div class="innertube">
			<div class="content">
				<form id="registration_form" method="post" action="controller?command=register">
 					 <div class="container">
    					<h3><fmt:message key="header.register"/></h3>
    					<hr>
					    <t:success successMessage="${sessionScope.successMessage}"/>
						<c:remove var="errorMessage"/>
					    
					    <label><b><fmt:message key="label.login"/></b></label>
					    <input type="text" name="login" >
    
						<label><b><fmt:message key="label.first.name"/></b></label>
					    <input type="text" name="first_name" >
					
						<label><b><fmt:message key="label.last.name"/></b></label>
						<input type="text" name="last_name" >
						
						<label><b><fmt:message key="label.email"/></b></label>
					    <input type="password" name="email" >
					
					    <label><b><fmt:message key="label.password"/></b></label>
					    <input type="password" name="password" >
					
					    <label ><b><fmt:message key="label.password.repeat"/></b></label>
					    <input type="password" name="passwordConfirm" >
					    <hr>
					    
					    <t:error errorMessage="${sessionScope.errorMessage}"/>
						<c:remove var="errorMessage"/>
    					
    					<button type="submit" class="registerbtn"><fmt:message key="button.register"/></button>
  					</div>
				</form>
			</div>
		</div>
	</main>
<%@ include file="/WEB-INF/includes/footer.jspf" %>
</body>
</html>
	