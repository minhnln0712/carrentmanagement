<%-- 
    Document   : cart
    Created on : Mar 14, 2021, 12:11:13 PM
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
        <title>View Cart Page</title>
    </head>
    <body>
        <c:set var="CART" value="${sessionScope.CART.cart.values()}"/>
        <c:set var="EMAIL" value="${sessionScope.EMAIL}"/>
        <c:set var="TOTAL" value="${sessionScope.TOTAL}"/>
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
                            <a href="ViewHistory">History</a>
                            <div class="logintopnav">
                                <h3>Hello,<font color="#66ff33">${NAME}</font></h3>
                                <a href="Logout" >Logout</a>
                                <a href="Cart" class="active">CART</a>
                            </div>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
        <div class="container-fulid"> 
            <c:if test="${CART == null}">
                <h1 style="color: red">CART EMPTY!GO GET SOME CAR!!</h1>
            </c:if>
            <c:if test="${CART != null}">
                <table border="1">
                    <thead style="text-align: center    "> 
                        <tr>
                            <th>No.</th>
                            <th>Name</th>
                            <th>Image</th>
                            <th>Rent Date</th>
                            <th>Return Date</th>
                            <th>License ID</th>
                            <th>Price</th>
                        </tr>
                    </thead>
                    <tbody>
                    <form action="UpdatePrice">
                        <c:forEach var="cart" items="${CART}" varStatus="counter">
                            <tr>
                                <td>${counter.count}</td>
                                <td>
                                    <input type="text" name="" value="${cart.carName}" readonly=""/>
                                    <input type="hidden" name="txtCarDetailID" value="${cart.carDetailID}" />
                                    <input type="hidden" name="txtCarID" value="${cart.carID}" />
                                </td>
                                <td>
                                    <div style="display: flex;justify-content: center; background-image: linear-gradient(${car.color}, #f2f2f2);">
                                        <img src="${cart.carImage}" alt="${cart.carName}" width="40%" height="auto"><br/>
                                    </div>
                                </td>                            
                                <td>
                                    <input class="form-control" type="date" name="txtRentDate" style="width: 200px;" value="${cart.rentDate}" min="${sessionScope.DATENOW}">
                                </td>
                                <td>
                                    <input class="form-control" type="date" name="txtReturnDate" style="width: 200px;" value="${cart.returnDate}" min="${sessionScope.DATENOW}">
                                </td>
                                <td>
                                    <input type="text" name="" value="${cart.licenseID}" readonly=""/>
                                </td>
                                <td>
                                    <input type="text" name="" value="${cart.totalPrice}" readonly=""/>
                                </td>
                                <td> 
                                    <a href="RemoveInCart?txtCarDetailID=${cart.carDetailID}" class="buttonlink">DELETE</a>
                                </td>
                            </tr>
                        </c:forEach>
                        <tr>
                            <td colspan="6"></td>
                            <td>
                                <select name="cbbDiscount">
                                    <c:forEach var="dis" items="${sessionScope.DISCOUNT}">
                                        <c:if test="${dis.discountPercent != requestScope.DISCOUNT}">
                                            <option value="${dis.discountPercent}">${dis.discountID}</option>
                                        </c:if>
                                        <c:if test="${dis.discountPercent == requestScope.DISCOUNT}">
                                            <option value="${dis.discountPercent}" selected="">${dis.discountID}</option>
                                        </c:if>
                                    </c:forEach>
                                </select>
                                <input type="submit" value="Update" />
                            </td>
                            <td>
                                <input type="text" name="" value="${TOTAL}" readonly="readonly" />
                            </td>
                        </tr>
                    </form>
                    <tr>
                        <td colspan="6"></td>
                    <form action="Rental" onsubmit="alert('Order Completed!!')">
                        <td>

                        </td>
                        <td>
                            <input type="submit" value="Order" />
                        </td>
                    </form>
                    </tr>
                    </tbody>
                </table>
            </c:if>
        </div>
    </body>
</html>
