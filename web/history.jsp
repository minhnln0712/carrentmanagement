<%-- 
    Document   : history
    Created on : Mar 16, 2021, 1:56:26 PM
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
        <title>History Page</title>
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
            <form action="SearchHistory">
                <c:if test="${sessionScope.ROLE == 'admin'}">
                    Name: <input type="text" name="txtNameSearch" value="${requestScope.NAMESEARCH}" /><br/>
                </c:if>
                From: <input type="date" name="txtRentDate" value="${requestScope.RENTDATE}" /> - 
                To: <input type="date" name="txtReturnDate" value="${requestScope.RETURNDATE}" />
                <input type="submit" value="Search" />
            </form>
        </div>
        <div class="container-fulid">
            <c:set var="RENTALLIST" value="${requestScope.RENTALLIST}"/>
            <c:if test="${RENTALLIST != NULL}">
                <table border="1" style="width: 100%">
                    <thead style="text-align: center">
                        <tr>
                            <th>No.</th>
                            <th>Rental ID</th>
                            <th>Email</th>
                            <th>Create Date</th>
                            <th>Discount ID</th>
                            <th>Total Price</th>
                                <c:if test="${sessionScope.ROLE == 'admin'}">
                                <th>Action</th>
                                </c:if>
                            <th>Detail</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="rental" items="${RENTALLIST}" varStatus="counter">
                            <tr>
                                <td>${counter.count}</td>
                                <td>
                                    ${rental.rentalID}
                                    <input type="hidden" name="txtRentalID" value="${rental.rentalID}" />
                                </td>
                                <td>${rental.email}</td>
                                <td>
                                    <input class="form-control" type="date" name="" style="width: 200px;" value="${rental.createDate}" readonly="">
                                </td>
                                <td>${rental.discountID}</td>
                                <td>${rental.totalPrice}</td>
                                <c:if test="${sessionScope.ROLE == 'admin'}">
                                    <td>
                                        <a href="RemoveHistory?txtRentalID=${rental.rentalID}" class="buttonlink">Remove</a>
                                    </td>
                                </c:if>
                                <td>
                                    <a href="ViewHistoryDetail?txtRentalID=${rental.rentalID}" class="buttonlink">View</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>
            <c:if test="${RENTALLIST == NULL}">
                <h1 style="color: red">HISTORY EMPTY!</h1>
            </c:if>
        </div>
    </body>
</html>
