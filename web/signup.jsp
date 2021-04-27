<%-- 
    Document   : signup
    Created on : Mar 9, 2021, 3:49:23 PM
    Author     : Welcome
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style><%@include file="/WEB-INF/css/login.css"%></style>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous"/>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js" integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign Up Page</title>
    </head>
    <body>
        <div class="wrapper fadeInDown">
            <div id="formContent">
                <form action="SignUp" class="form-horizontal">
                    <div class="fadeIn first"><h1>Registration</h1></div>
                    <c:if test="${requestScope.ERROR!= null}"><font color="red">${requestScope.ERROR}</font></c:if><br/>
                    Email:<input type="text" name="txtEmail" class="fadeIn second" placeholder="Email" value="${param.txtEmail}" required/><br/>
                    Phone:<input type="text" name="txtPhone" class="fadeIn third" placeholder="Phone" value="${param.txtPhone}" required/><br/>
                    Name:<input type="text" name="txtName" class="fadeIn fourth" placeholder="Name" value="${param.txtName}" required/><br/>
                    Address:<input type="text" name="txtAddress" class="fadeIn fitht" placeholder="Address" value="${param.txtAddress}" required/><br/>
                    Password:<input type="password" name="txtPassword" class="fadeIn sixth"  placeholder="Password" required/><br/>
                    Confirm:<input type="password" name="txtConfirmPassword" class="fadeIn seventh"  placeholder="Confirm Password" required/><br/>
                    <div id="formFooter" class="fadeIn eigth">
                        <input type="submit" class="btn btn-dark" value="Create"/><br/>
                        <a href="DisplayCar">Cancel!!</a>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
