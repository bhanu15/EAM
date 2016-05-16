<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
       pageEncoding="ISO-8859-1"%>
<%@ page import="com.rakuten.eam.model.User" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
   <link href="//cdnjs.cloudflare.com/ajax/libs/jqueryui/1.11.2/jquery-ui.css" rel="stylesheet"/>
<link href="//cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.2/css/bootstrap.css" rel="stylesheet"/>
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
	.mybutton2:disabled{
		background: lightgrey;
	}
	
	.mybutton:hover,.mybutton2:active,.mybutton2:focus {
	  background: grey;
	}
	
	.mybutton:disabled{
		background: lightgrey;
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
	.form-control, .autocomplete{
		left: 180px;
	}
        
	.ui-widget{
	left: 180px !important;
/* 	top: 10px !important; */
 	} 
	
	
 	.ui-autocomplete{ 
       left:250px !important; 
/*        width:305px !important;  */
   } 
   
       .layer
  {
        position: fixed;
        opacity: 0.7;
        left: 0px;
        top: 0px;
        width: 100%;
        height: 100%;
        z-index: 999999;
        background-color: #BEBEBE;
        display: none;
        cursor: not-allowed;
  }
	 </style>
</head>
<body>
		
		<div class="layer" id="layout"></div>
       <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
       <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.14/jquery-ui.min.js"></script>   


		<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.js"></script>
		<script src="//cdnjs.cloudflare.com/ajax/libs/jqueryui/1.11.2/jquery-ui.js"></script>
		<script src="//cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.2/js/bootstrap.js"></script>
   
      <script type="text/javascript">
	      var employeeContext = "/EmployeeManager/";
		   var hostName = "http://localhost:8080"+employeeContext;
      </script>
      <script>
       $(document).ready(function () {
    	   
		$(".autocomplete").autocomplete({
			source:  function(request, response){
				  var term = request.term;
		     	   var restUrl = hostName+"employee/search/"+term;
		     	   var items = [];
		     	   
		     	  $.ajax({
		     	        type: 'GET',
		     	        url: restUrl,
		     	        dataType: 'json',
		     	        async: false ,
		     	        success: function(employees) { 
		     	        	 $.each(employees, function (i, result) {     
		     	        		var item = {
	 		  			     	    	label: result.firstName+" "+result.lastName+" "+result.employeeId,
	 		  			     	        value: result.employeeId
	 		  			     	       };
		  			 			items.push(item);
		  		     	      });
		     	        	return items;
		     	        },
		     	    });
				response( items);
			}
    	   });
    	   
		      
    	   $("#userSearch").click(function(){ 
    		   employeeId=$("input#employeeId").val();  
    		   $.ajax({ 
    		   type:"GET", 
    		   url:employeeContext+"employee/"+employeeId,  
    		   success:function(data){ 
   			   if(typeof data =='object')
   			   {
   				   $("#status").html(data.message);
     		       $("#status").show();
   			   }  		             		        		       
    		   else{
   		    		$("#response").html(data);
   		    		$("#response").show();	
   		    		$("#edit").removeAttr("disabled");  
   		    		$("#delete").removeAttr("disabled");  
   		    		$("#checkIn").removeAttr( "disabled");
   			        $("#checkOut").removeAttr( "disabled");
   			   		$("#report").removeAttr( "disabled");
    		   } }
    		   }); 
    		   }); 
    	   
    	   $("#delete").click(function(){ 
    		   employeeId=$("input#employeeId").val();  
    		   $.ajax({ 
    		   type:"DELETE", 
    		   url:employeeContext+"employee/delete/"+employeeId,  
    		   success:function(data){ 
    		   $("#status").html(data.message);
    		   $("#status").show();
    		   
    		   } 
    		   }); 
    		   }); 
    	   $("#checkIn").click(function(){ 
    		   employeeId=$("input#employeeId").val();  
    		   $.ajax({ 
    		   type:"POST", 
    		   url:employeeContext+"checkin?employeeId="+employeeId,  
    		   success:function(data){  
    		   	$("#status").html(data.message); 
    		   	$("#status").show();
    		   		
    		   } 
    		   }); 
    		   }); 
    	   $("#checkOut").click(function(){ 
    		   employeeId=$("input#employeeId").val();  
    		   $.ajax({ 
    		   type:"POST", 
    		   url:employeeContext+"checkout?employeeId="+employeeId,  
    		   success:function(data){ 
    		   	$("#status").html(data.message); 
    		   	$("#status").show();
    		   } 
    		   }); 
    		   }); 
    	   $("#logout").click(function(){   
    		   $.ajax({ 
    		   type:"POST", 
    		   url: employeeContext+"logout",  
    		   success:function(data){   			   
    			   window.location.href =employeeContext+"login";
    		   } 
    		   }); 
    		   }); 
    	   
    	   $("#employeeId").keydown( function(e) {
    		   $("#edit").prop( "disabled", true );
		       $("#delete").prop( "disabled", true );
		       $("#checkIn").prop( "disabled", true );
		       $("#checkOut").prop( "disabled", true );
		       $("#report").prop( "disabled", true );
    		   $("#status").hide();
    		   $("#response").hide();
    		  
    		    if(e.keyCode === 8 || e.keyCode === 46){
    		    	 $("#response").hide();
    		    	 
    		    }
    		    if(e.keyCode == 13){

    		    	employeeId=$("input#employeeId").val();  
    	    		$.ajax({ 
    	    			type:"GET", 
    	    		     url:employeeContext+"employee/"+employeeId,  
    	    		     success:function(data){ 
    	    			 	if(typeof data =='object')
    	    	   			   {
    	    	   				   $("#status").html(data.message);
    	    	     		       $("#status").show();
    	    	   			   }  		             		        		       
    	    	    		   else{
    	    	   		    		$("#response").html(data);
    	    	   		    		$("#response").show();	
    	    	   		    		$("#edit").removeAttr("disabled");  
    	    	   		    		$("#delete").removeAttr("disabled");  
    	    	   		    		$("#checkIn").removeAttr( "disabled");
    	    	   			       $("#checkOut").removeAttr( "disabled");
    	    	   			       $("#report").removeAttr( "disabled");
    	    	    		   }
    	    			 	
    	    		   	  } 
    	    		 });      		    	
    		    }
    		});
    	   
    	   $("#report").click(function(){
     		  
    		   var employeeId = $("input#employeeId").val();  

    		   var generateReportURL = employeeContext+"generateReport/"+employeeId;
    		   
    		   $.ajax({ 
    		   type:"GET", 
    		   dataType: 'json',
    		   url: generateReportURL,  
    		   success: function( employeeReport ){ 
    			   if(employeeReport.success == false){
    				   $("#status").html(employeeReport.message);
	     		       $("#status").show();
	     		       
    			   }else{
	    			   var content = "<table border='3' align='center' style='margin-top:50px;'>";
	    			   content += "<tr bgcolor='#bf0000'><td>" + "Employee Id </td><td> CheckIn Time  </td><td> CheckOut Time </td><td>Total Time Clocked </td></tr>";
	    			   $.each(employeeReport, function (i, result) {     
	 			 			
	 			 			content += "<tr><td style='padding:5px;'align='center'>" + result.employeeId + "</td><td style='padding:5px;'align='center'>"+result.checkInTime +"</td><td style='padding:5px;'align='center'> "+result.checkOutTime+"</td><td style='padding:5px;'align='center'> "+result.timeClocked+"</td></tr>";   
	    			   });
	    			  
	    			   content += "</table>";
					   var w = window.open();
					   $(w.document.body).html(content);
    			   }
					   
    		   }
    		   
    		   }); 
    		   
    	   });
    	
    	 });
       
       $(".layout").click(function(e) {
           new_window.focus();
       
   		});
       
       function edit()
       {
    	   var w=400;
    	   var h=500;
    	   var left = (screen.width/2)-(w/2);
    	   var top = (screen.height/2)-(h/2);
	       var employeeId = $("input#employeeId").val();  
	       var url = employeeContext+"employee/edit/"+employeeId;
	       $(".layer").show();
	       new_window = window.open(url,null, "height="+h+",width="+w+",left="+left+",top="+top+",status=yes,toolbar=no,menubar=no,location=center");
       }
       
       function add()
       { 
    	   var w=400;
    	   var h=500;
    	   var left = (screen.width/2)-(w/2);
    	   var top = (screen.height/2)-(h/2);
    	   var url = employeeContext+"employee/create";
    	   $(".layer").show();
    	   new_window = window.open(url,null, "height="+h+",width="+w+",left="+left+",top="+top+",status=yes,toolbar=no,menubar=no,location=center"); 
       }
       
       
       
       
</script>  
		
    	 <div  id="temp" style="width:100%;top:150px;padding-top:50px;">
    	 		<%
				if (session.getAttribute("user")!=null && "admin".equalsIgnoreCase(((User)session.getAttribute("user")).getUserName())){
				%>	
    	 		<div style="float: left;padding-left:50px;" >
    	 		
					<button id="add" onclick="add()" class="mybutton2" >Add Employee</button>
		
					<button id="edit" onclick="edit()" class="mybutton2" disabled="disabled">Edit Employee</button>  
		
					<button id="delete" class="mybutton2" disabled="disabled">Delete Employee</button> 
					<button id="report" class="mybutton2" disabled="disabled">Employee Attendance Report</button> 
				</div>
				<%}%>
				<div style="float: right;padding-right:50px;">
					<button id="logout" class="mybutton2">Logout</button> 
    			</div>
  		</div>
		
  		<div style="width:100%;padding:75px;top:150px;padding-right:250px;padding-left:250px;padding-bottom:20px;">
  				<div id="status" class="validationmessage" style="text-align: center;"></div> 
    	 		<div  style="text-align: center;">
					<input type="text" id="employeeId" class="form-control autocomplete" placeholder="Search Employee" required="true" class="myinput"/>
    			</div>
    	</div>
    	<div style="width:100%;padding-bottom:20px;">
     		<div id="response" class="validationmessage"></div>
     	</div>
    	<button id="userSearch" class="mybutton2" style="width:10%;padding:10px;margin-left:625px;top:50px;">Search</button>
     	<div id="userSearchSuggestions" style="text-align: center;"></div>
     	<div style="width:100%;padding-top:50px">
			<div style="text-align: center;">
				<button id="checkIn" class="mybutton" disabled="disabled">Check In</button> 
			
				<button id="checkOut" class="mybutton" disabled="disabled">Check Out</button>
			</div>
		</div>
		</div>
    </body>
</html>