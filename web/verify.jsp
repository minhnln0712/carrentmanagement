<%-- 
    Document   : verify
    Created on : Mar 10, 2021, 2:06:59 AM
    Author     : Welcome
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Verify Page</title>
    </head>
    <body>
        <h1>Your account have not been activated!!!</h1>
        <a href="SendEmail?txtEmail=${requestScope.EMAIL}">Click Here to Send Activation Code and Check your EMAIL!!!</a><br/>
        <a href="DisplayCar">Cancel!!!</a>
    </body>
</html>
