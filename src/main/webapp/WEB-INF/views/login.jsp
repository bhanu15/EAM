<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
     <style>
	.login-page {
	  width: 400px;
	  padding: 8% 0 0;
	  margin: auto;
	}
	.form {
	  position: relative;
	  z-index: 1;
	  background: #bf0000;
	  max-width: 400px;
	  margin: 0 auto 100px;
	  padding: 45px;
	  text-align: center;
	  box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.2), 0 5px 5px 0 rgba(0, 0, 0, 0.24);
	}
	.form input {
	  font-family: "Roboto", sans-serif;
	  outline: 0;
	  background: #f2f2f2;
	  width: 100%;
	  border: 0;
	  margin: 0 0 15px;
	  padding: 15px;
	  box-sizing: border-box;
	  font-size: 14px;
	}
	.form button {
	  font-family: "Roboto", sans-serif;
	  text-transform: uppercase;
	  outline: 0;
	  background: lightgrey;
	  width: 100%;
	  border: 0;
	  padding: 15px;
	  color: #f2f2f2;
	  font-size: 14px;
	  -webkit-transition: all 0.3 ease;
	  transition: all 0.3 ease;
	  cursor: pointer;
	}
	.form button:hover,.form button:active,.form button:focus {
	  background: grey;
	}
	.form .message {
	  margin: 15px 15px 15px;
	  color: white;
	  font-size: 14px;
	}
	.form .message a {
	  color: #4CAF50;
	  text-decoration: none;
	}


	</style>
        <title>Login</title>
    </head>
    <body>
    	<div class="login-page">
    	<div class="form">
        <form:form id="loginForm" method="post" action="authenticate" modelAttribute="user">
        <div class="message"> ${message}</div>
            <form:input id="userName" name="userName" path="userName" placeholder="username" required="true"/><br>
            <form:password id="password" name="password" path="password" placeholder="password" required="true"/><br>
            <button id="submit" onclick="Submit()">login</button>
        </form:form>
        </div>
        </div>
    </body>
</html>