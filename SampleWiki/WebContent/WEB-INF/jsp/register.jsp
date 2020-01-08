<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ page import="javax.servlet.http.Cookie" %>
<%@ page import="edu.edgewood.model.*"    %>   
<%@ page import="edu.edgewood.dao.*"    %>  
<%@ page import="edu.edgewood.svc.*"    %>  
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.util.*" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<link rel="stylesheet" href="css/common.css"/>
<script src='js/ext/jquery-3.3.1.min.js'></script>
<script src='js/register.js'></script>
<script src='js/common.js'></script>
</head>
<body>

<div id = 'container'>
<%@include file="/WEB-INF/jsp/include/header.jsp" %>

	<div id="content">
		<% %>
			<div id='errMessage'>${errorMessage}</div>
			<form id='registerFormId' action='register' method='post'>
			<input type='hidden' id='DateTimeId' name='dateTime' value='<%=LocalDateTime.now()%>'/>
				<p>First Name: <input type='text' id='firstNameId' name='firstname' value=''/></p>
				<p>Last Name: <input type='text' id='lastNameId' name='lastname' value=''/></p>
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