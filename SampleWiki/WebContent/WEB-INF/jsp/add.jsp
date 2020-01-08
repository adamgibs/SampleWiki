

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

<!-- check if cookie exist -->
<%	
    //get cookie  
     String cookieName = "userId";
	 String userId = "";
	 boolean cookieExists = false;
		  
		  //looking for cookie
		  Cookie[] cookies = request.getCookies(); 
		  if (cookies != null) { 
			  for(int i=0; i<cookies.length; i++) 
			  { Cookie cookie = cookies[i]; 
			       if(cookieName.equals(cookie.getName())) 
			         { userId = cookie.getValue();
			    	   cookieExists = true;}//end if (userId found)
			  }//end for 
		  }//end if 
%>


<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Add Posting</title>
		<link rel="stylesheet" href="css/common.css"/>
		<Script src='js/ext/jquery-3.3.1.min.js'></Script>
		<Script src='js/common.js'></Script>
		<Script src='js/add.js'></Script>
	</head>
	
	<body>	
		<div id = 'container'>
		
			<%
				if (cookieExists){
					UserManager svc = new UserManager();
					User loginUser = svc.getUser(userId);
					String userName = loginUser.getFirstName() + " " + loginUser.getLastName();		
			%>	
				<div id='header'>
				<h1>WorkShare Wiki</h1>
				</div>
				<div id='menu'>
				<span><%=userName%></span>
	            <a class = 'menu' id='logoutLnkId'>Logout</a>  
				</div>		
				
				<div id = 'content'>
				<h2>Add New Posting: </h2>
				<form id ='newPostingFormId' action='add' method='post'>
				
				<input type='hidden' id='postingId' name='Id' value='<%=UUID.randomUUID().toString()%>'/>
				<input type='hidden' id='DateTimeId' name='dateTime' value='<%=LocalDateTime.now()%>'/>
				<input type='hidden' id='UserId' name='user' value='<%=userId %>'/>
				
				    <b>Title:</b>
					<input type='text' id='titleId' name='title' value=''/><span class='error'>*</span>
					
					<p/>
					
				 	<b>Body:</b><br/>	
				 	<textarea id='body' cols="40" rows="10" name='body'></textarea>		 	
				 	<p/>
				 	
				 	Tags:
				 	<input type='text' id='tagsId' name='tags' value=''/><span class='error'>* Must be comma-separated</span><br/>
				 	
					<input type='button' id='saveBtnId' value='Save'/>
					<input type='button' id='cancelBtnId' value='Cancel'/>	
					<br/>
					<span class='error'>* required</span>	
				</form>			
				</div>
				
			<% }
				else		
			{%>	
				<div id='header'>
				<h1>WorkShare Wiki</h1>
				</div>
	            <div id='menu'>
	        	<a class = 'menu' id='loginLnkId' href=''>Login</a>						
				<a class = 'menu' id='RegisterLnkId' href=''>Register</a>					
				</div>
				
				<div id="content">
				<p>Log in first to add a new posting.</p>
				</div>
				
			<%} %>
			
			
			<%@include file="/WEB-INF/jsp/include/footer.jsp" %>
		
		</div>
	
	</body>
</html>