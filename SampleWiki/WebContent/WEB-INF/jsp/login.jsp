<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<link rel="stylesheet" href="css/common.css"/>
<script src='js/ext/jquery-3.3.1.min.js'></script>
<script src='js/login.js'></script>
<script src='js/common.js'></script>
</head>
<body>

<div id = 'container'>
<%@include file="/WEB-INF/jsp/include/header.jsp" %>

	<div id="content">
		<% %>
			<div id='errMessage'>${errorMessage}</div>
			<form id='loginFormId' action='login' method='post'>
				
				<p>User Name: <input type='text' id='userNameId' name='username' value=''/></p>
				<p>Password: <input type='text' id='passwordId' name='password' value=''/></p>
				
				<input type='button' id='submitBtnId' value='Submit'/>
				<input type='button' id='cancelBtnId'  value='Cancel'/>
				
			</form>
	</div>
	<%@include file="/WEB-INF/jsp/include/footer.jsp" %>
	
	</div>
</body>
</html>