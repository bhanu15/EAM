<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
       pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome</title>
</head>
<body>
       <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
       <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.14/jquery-ui.min.js"></script>
       <script>
       $(document).ready(function () {
    	   $("input#employeeId").autocomplete({
    	   source: function (request, response) {
    	   var term = request.term;
    	   var restUrl = "http://localhost:8080/eam/employee/search/"+term;
    	   $("#response").hide();

    	   $.getJSON(restUrl, function (employees) {
    	   var items = [];
    	   $.each(employees, function (i, result) {                          
    	     var item = {
    	       label: result.firstName+" "+result.lastName+" "+result.employeeId,
    	       value: result.employeeId
    	       };

    	       items.push(item);
    	       });

    	       response(items);
    	       });
    	     }
    	   });
    	   
    	   
    	   $("#view").click(function(){ 
    		   employeeId=$("input#employeeId").val();  
    		   $.ajax({ 
    		   type:"GET", 
    		   url:"/eam/employee/"+employeeId,  
    		   success:function(data){ 
    		   $("#response").html(data); 
    		   $("#response").show();
    		   } 
    		   }); 
    		   }); 
    	   
    	   $("#delete").click(function(){ 
    		   employeeId=$("input#employeeId").val();  
    		   $.ajax({ 
    		   type:"DELETE", 
    		   url:"/eam/employee/delete/"+employeeId,  
    		   success:function(data){ 
    		   $("#response").html(data); 
    		   } 
    		   }); 
    		   }); 
    	   $("#checkIn").click(function(){ 
    		   employeeId=$("input#employeeId").val();  
    		   $.ajax({ 
    		   type:"POST", 
    		   url:"/eam/login?employeeId="+employeeId,  
    		   success:function(data){ 
    		   $("#response").html(data); 
    		   } 
    		   }); 
    		   }); 
    	   $("#checkOut").click(function(){ 
    		   employeeId=$("input#employeeId").val();  
    		   $.ajax({ 
    		   type:"POST", 
    		   url:"/eam/logout?employeeId="+employeeId,  
    		   success:function(data){ 
    		   $("#response").html(data); 
    		   } 
    		   }); 
    		   }); 
    	 });
       
       function edit()
       {
         
       var employeeId = $("input#employeeId").val();  
       window.open("/eam/employee/edit/"+employeeId,null,
       "height=500,width=500,status=yes,toolbar=no,menubar=no,location=center");
       }
       
       function add()
       {  
       window.open("/eam/employee/create",null,
       "height=500,width=500,status=yes,toolbar=no,menubar=no,location=center");
       }

    	 </script>   	

    	 Search Employee

    	 <input type="text" id="employeeId" />
    	 
    	 <button id="view">Search</button>
    	 
    	 <button id="add" onclick="add()" >Add Employee</button>
	
		<button id="edit" onclick="edit()">Edit Employee</button>  
	
		<button id="delete">Delete Employee</button> 
		
		<button id="checkIn">Check In</button> 
		
		<button id="checkOut">Check Out</button>
		
		<div id="response"></div> 
    	 
    	  
    	 </body>
    	 </html>
       