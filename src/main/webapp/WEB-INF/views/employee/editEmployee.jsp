<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        
        <style>
	.form-page {
	  width: 400px;
	  padding: 8% 0 0;
	  margin: auto;
	}
	
	.form input {
	  font-family: "Roboto", sans-serif;
	  outline: 0;
	  background: #f2f2f2;
	  border: 0;
	  margin:2px;
	  padding: 5px;
	  box-sizing: border-box;
	  font-size: 14px;
	}
	.form button {
	  font-family: "Roboto", sans-serif;
	  text-transform: uppercase;
	  outline: 0;
	  background: #bf0000;
	  width: 20%;
	  border: 0;
	  padding: 4px;
	  color: #f2f2f2;
	  font-size: 14px;
	  -webkit-transition: all 0.3 ease;
	  transition: all 0.3 ease;
	  cursor: pointer;
	   margin:15px;
	}
	.form button:hover,.form button:active,.form button:focus {
	  background: grey;
	}
	.form label {
	  font-family: "Roboto", sans-serif;
	  margin: 15px 15px 15px;
	  font-size: 14px;
	}
	
	.message {
	  font-family: "Roboto", sans-serif;
	  color: #bf0000;
	  margin: 15px 15px 15px;
	  font-size: 14px;
	}


	</style>
        
        <title>Edit Employee</title>
    </head>
    <body>
           <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>

	       <script>
	       $(document).ready(function(){
	    	   
	    	   var employeeContext = "/EmployeeManager/";
	    	   
		       $("#save").click(function(){
		    	   var userEmail = $("#emailId").val();
                   var phoneNumber = $("#phoneNumber").val();
                   
                   var validate = true;
                   
                   var validationMessage="";
                   
                   if(!isEmail(userEmail)){
                          validate = false;                  
                          $("#emailId").css("background-color","#bf0000");
                          validationMessage = validationMessage+"Email should be in correct format. <br>";
                   }
                   else{
                	   validate = true;                    
                        $("#emailId").css("background-color","#f2f2f2");
                       
                   }
                   if(!validatePhone(phoneNumber)){
                          validate = false;
                          $("#phoneNumber").css("background-color","#bf0000");
                          validationMessage = validationMessage+"Phone number should contain only 10 digits. <br>";
                   }
                   else{
                	   $("#phoneNumber").css("background-color","#f2f2f2");
                      
                   }
                   if(validate == false){
                	   $("#status").html(validationMessage); 
                   }
                   else{
		
			    	   var str = $("#updateEmployeeForm").serialize();
			    	    $.ajax({
			    	    	type: 'PUT',
			    	    	data: str,
			    	    	url:  employeeContext+"employee/update/?"+str, 
			    	    	success: function(data){
			    	    		$("#status").html(data.message); 
			    	    }});
                   }
			    });
		       
	       });
	       
	       function isEmail(email) {
               var regex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
               return email==null || email=="" ||regex.test(email);
          }
          
          function validatePhone(phoneNumber) {
             var filter =  /^\d{10}$/; 
             if (phoneNumber==null || phoneNumber=="" || filter.test(phoneNumber)) {
             return true;
             }
             else {
             return false;
             }
          }

	       function submit(){
	           var doc = window.opener.document;
	           doc.getElementById("layout").style.display="none";
	           window.close();
	       }   

	      window.onbeforeunload = function(){
	           var doc = window.opener.document;
	           doc.getElementById("layout").style.display="none";
	      }
	      

	       </script>
    <div class="form-page">
    <div class="form">
    
        <form:form id="updateEmployeeForm">
        <div id="status" class="message"></div>
        <table width="400px">
        <tr  align="left">
        	<td>
        		<label>Employee Id</label>
        	</td>
        	<td>
            	<input id="employeeId" name="employeeId" value="${employee.employeeId}" readonly="readonly"/><br>
            </td>
        </tr >
        <tr  align="left">
        	<td>
            <label>First Name</label>
            </td>
        	<td>
            <input id="firstName" name="firstName"  value="${employee.firstName}" /><br>
       		</td>
        </tr>
         <tr  align="left">
        	<td>
            <label>Last Name</label>
            </td>
        	<td>
            <input id="lastName" name="lastName" value="${employee.lastName}" /><br>
            </td>
        </tr>
        <tr  align="left">
        	<td>
            	<label>Nick Name</label>
            </td>
        	<td>
            <input id="nickName" name="nickName" value="${employee.nickName}"/><br>
            </td>
        </tr>
             <tr  align="left">
        	<td>
            <label>Email Id</label>
            </td>
        	<td>  
            <input id="emailId" name="emailId" value="${employee.emailId}"/><br>
            </td>
        </tr>
             <tr  align="left">
        	<td>
            <label>Department</label>   
            </td>
        	<td>     
            <input id="department" name="department" value="${employee.department}"/><br>
            </td>
        </tr>
             <tr  align="left">
        	<td>
            <label>Phone Number</label>
            </td>
        	<td>
            <input id="phoneNumber" name="phoneNumber" value="${employee.phoneNumber}"/><br>
            </td>
        </tr>
            
         </table>
        </form:form>
        <button id="save">save</button>
       
    </div>
    </div>
    </body>
</html>