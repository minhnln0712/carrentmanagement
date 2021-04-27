<%-- 
    Document   : activated
    Created on : Mar 10, 2021, 2:34:01 AM
    Author     : Welcome
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Activated Page</title>
    </head>
    <body>
        <h1>Your Account Have't Active Yet!!</h1>
        <c:if test="${requestScope.ERROR != null}">
            <h1 style="color: red">${requestScope.ERROR}</h1>
        </c:if>
        <form action="ActiveAccount">
            <input type="hidden" name="txtEmail" value="${requestScope.EMAIL}" />
            HELLO!! ${requestScope.EMAIL}<br/>
            PLEASE INPUT YOUR ACTIVATION CODE: <input type="text" name="txtActiveCode" value="" /><br/>
            <input type="submit" value="Submit" />
        </form>
    </body>
</html>
