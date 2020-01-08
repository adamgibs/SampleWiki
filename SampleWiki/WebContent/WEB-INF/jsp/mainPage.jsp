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
<title>Main</title>
<link rel="stylesheet" href="css/common.css"/>
<script src='js/ext/jquery-3.3.1.min.js'></script>
<script src='js/common.js'></script>
</head>


<body>

	<div id='container'>
	
				<div id='header'>
				<h1>WorkShare Wiki</h1>
				</div>
	
			<%
				if (cookieExists){
					UserManager svc = new UserManager();
					User loginUser = svc.getUser(userId);
					String userName = loginUser.getFirstName() + " " + loginUser.getLastName();		
			%>	
				
					<div id='menu'>
					<span><%=userName%></span>
		            <a class = 'menu' id='logoutLnkId' href=''>Logout</a>  
					</div>		
				
				
			 <% }
				else		
			 {%>	
				
		            <div id='menu'>
		        	<a class = 'menu' id='loginLnkId' href=''>Login</a>						
					<a class = 'menu' id='registerLnkId' href=''>Register</a>					
					</div>
				
				
				<%} %>
				<form id='searchFormId' action='main' method='post'>
				
					<p>Search: <input type='text' id='searchId' name='search' value=''/></p>
		
					<input type='submit' id='submitBtnId' value='Submit'/>
					<input type='button' id='showAllBtnId' value='Show All'/>
					
				
				</form>
				
				
	
				<div id="content">
		
						<%
						    List<Posting> postings = (List)request.getAttribute("postings");
							if(postings == null || postings.isEmpty()){
						%>
						<% if (cookieExists){ %>
						<a class='newButton' href='add'>Add New Post</a>
						<p></p>
						<%} %>
								
								Nothing Found 
							
						<%	
							}else{
						%>
						
						<% if (cookieExists){ %>
						<a class='newButton' href='add'>Add New Post</a>
						<p></p>
						<%} %>
								
							
								
						<%
								for(Posting posting : postings){
						%>
						<div id="postings">
						<table>
							<tr>
								<td><b><%=posting.getCreatedDate()%></b>   </td>									
							</tr>
							
							<tr>
								<td id="postingNameId">
								<a href='details?id=<%=posting.getId()%>'><%=posting.getTitle() %></a>
								</td>
							</tr>
							
							<% if (cookieExists){ %>
							<tr>
								<td>
								<a class = 'Button' href='update?id=<%=posting.getId()%>'>Edit</a>
								
	    		 			    <a class='Button' href='delete?id=<%=posting.getId()%>'>Delete</a>
	    		 			    
	    		 			    
								</td>
							</tr>
							<%} %>											
														
							</table>
							</div>
							
							<p></p>
						<%			
							}
						%>						
						
						<%
						}
						%>
				</div>
				
				<%@include file="/WEB-INF/jsp/include/footer.jsp" %>
			</div>
</body>
</html>