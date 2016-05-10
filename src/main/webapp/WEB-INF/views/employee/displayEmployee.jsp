<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employee Details</title>
</head>
<body> 
<table border="1" align="center" >
	
		<tr>
			<th>Employee Id</th>
			<td>${employee.employeeId}</td>
		</tr>
	
		<tr>
			<th>First Name</th>
			<td>${employee.firstName}</td>
		</tr>
		<tr>
			<th>Last Name</th>
			<td>${employee.lastName}</td>
		</tr>
	
		<tr>
			<th>Nick Name</th>
			<td>${employee.nickName}</td>
		</tr>	
		<tr>
			<th>Email</th>
			<td>${employee.emailId}</td>
		</tr>
		<tr>
			<th>Department</th>
			<td>${employee.department}</td>
		</tr>

</table> 
</body>
</html>