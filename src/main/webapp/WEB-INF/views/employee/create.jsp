<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Create Employee</title>
    </head>
    <body>
           <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>

	       <script>
	       $(document).ready(function(){
		       $("#save").click(function(){
		    	   var str = $("#createEmployeeForm").serialize();
		    	  
		    	    $.ajax({type: "POST", 
		    	    	data: str,
		    	    	url: "create", 
		    	    	success: function(result){
		    	    		if (result){
		    	    			$("#status").text("Successfully inserted");
		    	    		}
		    	    		else{
		    	    			$("#status").text("Failed to insert");
		    	    		}
		    	    }});
		    	});
	       });

	       </script>
    
        <form:form id="createEmployeeForm">
        	<div id="status"></div>
        	<label>Employee Id</label>
            <input id="employeeId" name="employeeId"/><br>
            <label>First Name</label>
            <input id="firstName" name="firstName"  /><br>
            <label>Last Name</label>
            <input id="lastName" name="lastName"/><br>
            <label>Nick Name</label>
            <input id="nickName" name="nickName" /><br>
            <label>Email Id</label>  
            <input id="emailId" name="emailId" /><br>
            <label>Department</label>        
            <input id="department" name="department"/><br>
            <label>Phone Number</label>
            <input id="phoneNumber" name="phoneNumber"/><br>
            
        </form:form>
        <button id="save">save</button>
    </body>
</html>