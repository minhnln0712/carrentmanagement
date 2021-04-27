<%-- 
    Document   : login
    Created on : Mar 4, 2021, 8:42:39 PM
    Author     : Welcome
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style><%@include file="/WEB-INF/css/login.css"%></style>
<script src="https://www.google.com/recaptcha/api.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous"/>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js" integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <div class="wrapper fadeInDown">
            <div id="formContent">
                <div class="fadeIn first"><h1>Login</h1></div>
                <form action="Login" class=".form-horizontal">
                    <c:if test="${requestScope.ERROR!= null}"><font color="red">${requestScope.ERROR}</font></c:if><br/>
                    Email:<input type="text" name="txtEmail" class="fadeIn second" placeholder="Email" value="${param.txtEmail}" required/><br/>
                    Password:<input type="password" name="txtPassword" class="fadeIn third"  placeholder="Password" required/><br/>
                    <div style="display: flex;justify-content: center;" class="fadeIn fourth">
                        <div class="g-recaptcha" data-sitekey="6LeMRGwaAAAAAHkrAWzJ3OFvuaHB7pK3ZoWJ5S2A"></div>
                    </div>
                    <input type="submit" class="btn btn-dark" value="Login" class="fadeIn fitht"/>
                </form>
                <div id="formFooter">
                    <form action="SignUpPage" class="fadeIn sixth">
                        <p style="text-align: center;">Don't have account?</p>
                        <input type="submit" class="btn btn-light" value="Click Here To Create a New Account"/><br/>
                        <a href="DisplayCar">Cancel!!</a>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
