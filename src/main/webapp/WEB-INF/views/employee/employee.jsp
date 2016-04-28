<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
    <title>Person Page</title>
    <style type="text/css">
        .tg  {border-collapse:collapse;border-spacing:0;border-color:#ccc;}
        .tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#fff;}
        .tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#f0f0f0;}
        .tg .tg-4eph{background-color:#f9f9f9}
    </style>
</head>
<body>
<h1>
    Add a Employee
</h1>
 
<c:url var="addAction" value="/employee/create" ></c:url>
 
<form:form action="${addAction}" commandName="employee">
<table>
    <c:if test="${!empty person.name}">
    <tr>
        <td>
            <form:label path="employee_id">
                <spring:message text="ID"/>
            </form:label>
        </td>
        <td>
            <form:input path="employee_id" />
        </td> 
    </tr>
    </c:if>
    <tr>
        <td>
            <form:label path="first_name">
                <spring:message text="First Name"/>
            </form:label>
        </td>
        <td>
            <form:input path="first_name" />
        </td> 
    </tr>
    
        <td colspan="2">
            
            <c:if test="${empty person.name}">
                <input type="submit"
                    value="<spring:message text="Add Person"/>" />
            </c:if>
        </td>
    </tr>
</table>  
</form:form>
<br>
    </table>

</body>
</html>