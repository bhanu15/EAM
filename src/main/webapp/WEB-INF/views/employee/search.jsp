<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
       pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Welcome</title>
	<style type="text/css">
	
	.mybutton {
	  font-family: "Roboto", sans-serif;
	  text-transform: uppercase;
	  outline: 0;
	  background: #bf0000;
	  width: 300px;
	  border: 0;
	  padding:10px;
	  color: #f2f2f2;
	  font-size: 14px;
	  cursor: pointer;
	}
	
	.mybutton2 {
	  font-family: "Roboto", sans-serif;
	  outline: 0;
	  background: #bf0000;
	  width: auto;
	  border: 0;
	  color: #f2f2f2;
	  font-size: 14px;
	  cursor: pointer;
	  padding:10px;
	  display: inline;
	  white-space: nowrap;
	}
		
	.mybutton2:hover,.mybutton2:active,.mybutton2:focus {
	  background: grey;
	}	
	
	.mybutton:hover,.mybutton2:active,.mybutton2:focus {
	  background: grey;
	}
			
	.myinput {
	  font-family: "Roboto", sans-serif;
	  outline: 0;
	  background: #f2f2f2;
	  width: 400px;
	  border: 0;
	  margin: 0 0 15px;
	  padding: 10px;
	  box-sizing: border-box;
	  font-size: 14px;
	  }
	  .validationmessage {
	  font-family: "Roboto", sans-serif;
	  margin: 15px 15px 15px;
	  color:  #bf0000;
	  font-size: 14px;
	}           
	 </style>
</head>
<body>
       <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
       <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.14/jquery-ui.min.js"></script>   
       <script>
       $(document).ready(function () {
    	   var employeeContext = "/EmployeeManager/";
    	   $("input#employeeId").autocomplete({
    	   source: function (request, response) {
    	   var term = request.term;
    	   var restUrl = "http://localhost:8080/eam/employee/search/"+term;

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
    		   url:employeeContext+"employee/"+employeeId,  
    		   success:function(data){ 
    		   $("#response").html(data); 
    		   } 
    		   }); 
    		   }); 
    	   
    	   $("#delete").click(function(){ 
    		   employeeId=$("input#employeeId").val();  
    		   $.ajax({ 
    		   type:"DELETE", 
    		   url:employeeContext+"employee/delete/"+employeeId,  
    		   success:function(data){ 
    		   $("#status").html(data.message); 
    		   } 
    		   }); 
    		   }); 
    	   $("#checkIn").click(function(){ 
    		   employeeId=$("input#employeeId").val();  
    		   $.ajax({ 
    		   type:"POST", 
    		   url:employeeContext+"checkin?employeeId="+employeeId,  
    		   success:function(data){ 
    		   $("#status").html(data); 
    		   } 
    		   }); 
    		   }); 
    	   $("#checkOut").click(function(){ 
    		   employeeId=$("input#employeeId").val();  
    		   $.ajax({ 
    		   type:"POST", 
    		   url:employeeContext+"checkout?employeeId="+employeeId,  
    		   success:function(data){ 
    		   $("#status").html(data); 
    		   } 
    		   }); 
    		   }); 
    	   $("#logout").click(function(){   
    		   $.ajax({ 
    		   type:"POST", 
    		   url:employeeContext+"logout",  
    		   success:function(data){   			   
    			   window.location.href =employeeContext+"login";
    		   } 
    		   }); 
    		   }); 
    	 });
       
       function edit()
       {
         
       var employeeId = $("input#employeeId").val();  
       window.open(employeeContext+"employee/edit/"+employeeId,null,
       "height=500,width=500,status=yes,toolbar=no,menubar=no,location=center");
       }
       
       function add()
       {  
       window.open(employeeContext+"employee/create",null,
       "height=500,width=500,status=yes,toolbar=no,menubar=no,location=center");
       }

    	 </script>  
    	 <div style="width:100%">	
    	 	<div>
    	 		<div style="float: left" >
					<button id="add" onclick="add()" class="mybutton2" >Add Employee</button>
		
					<button id="edit" onclick="edit()" class="mybutton2">Edit Employee</button>  
		
					<button id="delete" class="mybutton2">Delete Employee</button> 
				</div>
				<div style="float: right">
					<button id="logout" class="mybutton2">Logout</button> 
    			</div>
  			</div>
  		</div>
  		<div style="width:100%;padding:50px">
  			<div>
  				<div id="status" class="validationmessage" style="text-align: center;"></div> 
    	 		<div  style="text-align: center;">
					<input type="text" id="employeeId" placeholder="Search Employee" required="true" class="myinput"/>
			 	 
    	 			<button id="view" class="mybutton2">Search</button>
    			</div>
    		</div>
    	</div>
    	<div style="width:100%">
     		<div id="response" class="validationmessage"></div>
     	</div>
     	<div style="width:100%;padding-top:200px">
			<div style="text-align: center;">
				<button id="checkIn" class="mybutton" >Check In</button> 
			
				<button id="checkOut" class="mybutton" >Check Out</button>
			</div>
		</div>
		
    </body>
</html>
       