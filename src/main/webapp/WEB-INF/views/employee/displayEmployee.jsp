<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employee Details</title>
<style type="text/css">
	
	.center-align{
	padding:5px;
	align = center;
	}
	 </style>
</head>
<body> 
<table border="1" align="center" >
	
		<tr>
			<th class = "center-align">Employee Id</th>
			<td class = "center-align">${employee.employeeId}</td>
		</tr>
	
		<tr>
			<th class = "center-align">First Name</th>
			<td class = "center-align">${employee.firstName}</td>
		</tr>
		<tr>
			<th class = "center-align">Last Name</th>
			<td class = "center-align">${employee.lastName}</td>
		</tr>
	
		<tr>
			<th class = "center-align">Nick Name</th>
			<td class = "center-align">${employee.nickName}</td>
		</tr>	
		<tr>
			<th class = "center-align">Email</th>
			<td class = "center-align">${employee.emailId}</td>
		</tr>
		<tr>
			<th class = "center-align">Department</th>
			<td class = "center-align">${employee.department}</td>
		</tr>

</table> 
</body>
</html>