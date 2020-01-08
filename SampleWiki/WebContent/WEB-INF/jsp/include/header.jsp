

<%@ page import="javax.servlet.http.Cookie" %>
<%@ page import="edu.edgewood.model.*"    %>   
<%@ page import="edu.edgewood.dao.*"    %>  
<%@ page import="edu.edgewood.svc.*"    %>  
<%@ page import="java.util.*"    %> 

<%	
    //get cookie  
     String cookieName = "userId";
	 String userId = "";
	 boolean cookieExisit = false;
		  
		  //looking for cookie
		  Cookie[] cookies = request.getCookies(); 
		  if (cookies != null) { 
			  for(int i=0; i<cookies.length; i++) 
			  { Cookie cookie = cookies[i]; 
			       if(cookieName.equals(cookie.getName())) 
			         { userId = cookie.getValue();
			    	   cookieExisit = true;}//end if (userId found)
			  }//end for 
		  }//end if 
%>

	<div id='header'>
	<h1>WorkShare Wiki</h1>
	</div>	
	
	<div id='menu'>
	<%
		if (cookieExisit){
		UserManager svc = new UserManager();
		User loginUser = svc.getUser(userId);
		String userName = loginUser.getFirstName() + " " + loginUser.getLastName();
	%>	
	    <span><%=userName%></span>	
		<a class = 'menu' id='logoutLnkId' href=''>Logout</a>
           
       <% }else{%>	       
       	<a class = 'menu' id='loginLnkId' href=''>Login</a>				
		<a class = 'menu' id='RegisterLnkId' href=''>Register</a>
	<%} %>
			
	</div>
	