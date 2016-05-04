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
		    	   var str = $("#updateEmployeeForm").serialize();
		    	    $.ajax({
		    	    	type: 'PUT',
		    	    	data: str,
		    	    	url:  "/eam/employee/update/?"+str, 
		    	    	success: function(result){
		    	    		if (result){
		    	    			$("#status").text("Successfully updated");
		    	    		}
		    	    		else{
		    	    			$("#status").text("Failed to update");
		    	    		}
		    	    }});
		    	});
	       });

	       </script>
    
        <form:form id="updateEmployeeForm">
        	<div id="status"></div>
        	<label>Employee Id</label>
            <input id="employeeId" name="employeeId" value="${employee.employeeId}" readonly="readonly"/><br>
            <label>First Name</label>
            <input id="firstName" name="firstName"  value="${employee.firstName}" /><br>
            <label>Last Name</label>
            <input id="lastName" name="lastName" value="${employee.lastName}" /><br>
            <label>Nick Name</label>
            <input id="nickName" name="nickName" value="${employee.nickName}"/><br>
            <label>Email Id</label>  
            <input id="emailId" name="emailId" value="${employee.emailId}"/><br>
            <label>Department</label>        
            <input id="department" name="department" value="${employee.department}"/><br>
            <label>Phone Number</label>
            <input id="phoneNumber" name="phoneNumber" value="${employee.phoneNumber}"/><br>
            
        </form:form>
        <button id="save">save</button>
    </body>
</html>