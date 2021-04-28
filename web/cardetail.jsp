<%-- 
    Document   : cardetail
    Created on : Mar 15, 2021, 9:35:51 PM
    Author     : Welcome
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style><%@include file="/WEB-INF/css/header.css"%></style>
<style><%@include file="/WEB-INF/css/btn.css"%></style>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js" integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <c:set var="LISTDETAIL" value="${requestScope.LISTDETAIL}"/>
        <div class="container-fulid">
            <div class="row">
                <div class="position-static">
                    <div class="topnav " id="myTopnav">
                        <a href="DisplayCar"  class="active">Home</a>
                        <c:if test="${ROLE == NULL}">
                            <div class="logintopnav">
                                <a href="LoginPage">Login</a>
                            </div>
                        </c:if>
                        <c:if test="${ROLE != NULL}">
                            <a href="ViewHistory">History</a>
                            <div class="logintopnav">
                                <h3>Hello,<font color="#66ff33">${NAME}</font></h3>
                                <a href="Logout" >Logout</a>
                                <a href="Cart">CART</a>
                            </div>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
        <div class="container-fulid">
            <table border="1" style="width: 100%">
                <thead style="text-align: center">
                    <tr>
                        <th>No.</th>
                        <th>Car Name</th>
                        <th>Image</th>
                        <th>Rent Date</th>
                        <th>Return Date</th>
                        <th>License ID</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="car" varStatus="counter" items="${LISTDETAIL}">
                    <form action="AddToCart" onsubmit="alert('Add to cart completed!!')">
                        <tr>
                        <input type="hidden" name="txtCarDetailID" value="${car.carDetailID}" />
                        <td>${counter.count}</td>
                        <td>
                            <input type="text" name="" value="${car.carName}" readonly="readonly" />
                        </td>
                        <td>
                            <div style="display: flex;justify-content: center; background-image: linear-gradient(${car.color}, #f2f2f2);">
                                <img src="${car.carImage}" alt="${car.carName}" width="40%" height="auto"><br/>
                            </div>
                        </td>
                        <td>
                            <input class="form-control" type="date" name="txtRentDate" style="width: 200px;" min="${requestScope.RENTDATE}" value="" required>
                        </td>
                        <td>
                            <input class="form-control" type="date" name="txtReturnDate" style="width: 200px;" min="${requestScope.RENTDATE}" value="" required>
                        </td>
                        <td>${car.licenseID}</td>
                        <td>
                            <input type="submit" value="Add to Cart" />
                        </td>
                        </tr>
                    </form>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
