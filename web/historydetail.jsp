<%-- 
    Document   : historydetail
    Created on : Mar 16, 2021, 1:56:37 PM
    Author     : Welcome
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<style><%@include file="/WEB-INF/css/header.css"%></style>
<style><%@include file="/WEB-INF/css/btn.css"%></style>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js" integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>History Detail Page</title>
    </head>
    <body>
        <div class="container-fulid">
            <div class="row">
                <div class="position-static">
                    <div class="topnav " id="myTopnav">
                        <a href="DisplayCar">Home</a>
                        <c:if test="${ROLE == NULL}">
                            <div class="logintopnav">
                                <a href="LoginPage">Login</a>
                            </div>
                        </c:if>
                        <c:if test="${ROLE != NULL}">
                            <a href="ViewHistory" class="active">History</a>
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
            <c:set var="DETAILRENTALLIST" value="${sessionScope.DETAILRENTALLIST}"/>
            <table border="1" style="width: 100%;text-align: center">
                <thead style="">
                    <tr>
                        <th>No.</th>
                        <th>Detail ID</th>
                        <th>Car Name</th>
                        <th>Car Image</th>
                        <th>Rent Date</th>
                        <th>Return Date</th>
                        <th>Licence ID</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="detail" items="${DETAILRENTALLIST}" varStatus="counter">
                        <tr>
                            <td>${counter.count}</td>
                            <td>${detail.rentalDetailID}</td>
                            <td>${detail.carName}</td>
                            <td>
                                    <div style="display: flex;justify-content: center; background-image: linear-gradient(${car.color}, #f2f2f2);">
                                        <img src="${detail.carImage}" alt="${detail.carName}" width="40%" height="auto"><br/>
                                    </div>
                                </td>
                            <td>
                                <input class="form-control" type="date" name="" style="width: 100%;" value="${detail.rentDate}" readonly="">
                            </td>
                            <td>
                                <input class="form-control" type="date" name="" style="width: 100%;" value="${detail.returnDate}" readonly="">
                            </td>
                            <td>${detail.licenceID}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
