<%-- 
    Document   : search
    Created on : Mar 4, 2021, 9:59:41 PM
    Author     : Welcome
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style><%@include file="/WEB-INF/css/paging.css"%></style>
<style><%@include file="/WEB-INF/css/header.css"%></style>
<style><%@include file="/WEB-INF/css/image.css"%></style>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js" integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Page</title>
    </head>
    <body>
        <c:set var="CAR" value="${requestScope.LISTCAR}"/>
        <c:set var="MAXPAGE" value="${requestScope.MAXPAGE}"/>
        <c:set var="ROLE" value="${sessionScope.ROLE}"/>
        <c:set var="NAME" value="${sessionScope.NAME}"/>
        <c:set var="LISTCATE" value="${requestScope.LISTCATE}"/>
        <c:set var="CATEID" value="${requestScope.CATEID}"/>
        <c:set var="KEYWORD" value="${requestScope.KEYWORD}"/>
        <c:set var="QUANTITY" value="${requestScope.QUANTITY}"/>
        <c:set var="DATENOW" value="${sessionScope.DATENOW}"/>
        <div class="container-fulid">
            <div class="row">
                <div class="position-static">
                    <div class="topnav " id="myTopnav">
                        <a href="DisplayCar" class="active">Home</a>
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
                                <a href="Cart" >CART</a>
                            </div>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
        <div class="container-fulid">
            <div class="row">
                <div class="col-sm-3">
                    <div class="position-fixed">
                        <div class="position-static" style="display: flex;justify-content: center;top: 0;">
                            <form action="SearchCar">
                                Name: <input type="text" name="txtSearch" value="${requestScope.KEYWORD}" /><br/>
                                Type:<select name="cbbCategory">
                                    <option value="%">ALL</option>
                                    <c:forEach var="cate" items="${LISTCATE}">
                                        <option value="${cate.categoryID}">${cate.categoryName}</option>
                                    </c:forEach>
                                </select><br/>
                                Quantity: <input type="number" name="txtQuantity" value="${requestScope.QUANTITY}" min="0"/><br/>
                                From: <input type="date" name="txtRentDate" value="${requestScope.RENTDATE}" min="${DATENOW}"/> - 
                                To: <input type="date" name="txtReturnDate" value="${requestScope.RETURNDATE}" min="${DATENOW}"/><br/>
                                <input type="submit" value="Search" class="btn btn-dark"/>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="col-sm-9">
                    <c:forEach var="car" items="${CAR}" varStatus="counter">
                        <form action="GetCarDetail">
                            <div class="col-sm-6" style="position: relative;float: left;font-family: 'Times New Roman', Times, serif; background-color: #f2f2f2;border-style: inset; height: 550px;width: 550px;">
                                <div class="container" style="display: flex;justify-content: center; background-image: linear-gradient(${car.color}, #f2f2f2);">
                                    <img src="${car.carImage}" alt="${car.carName}" width="80%" height="auto"><br/>
                                    <div class="middle">
                                        <c:if test="${ROLE != null}">
                                            <c:if test="${ROLE == 'admin'}">
                                                <c:if test="${car.quantity > 0 }">
                                                    <input type="button" value="Rent" class="btn btn-dark" onclick="return alert('Admin CANNOT use this function!!');"/>
                                                </c:if>
                                            </c:if>
                                            <c:if test="${ROLE == 'user'}">
                                                <c:if test="${car.quantity > 0 }">
                                                    <input type="submit" value="Rent" class="btn btn-dark" />
                                                </c:if>
                                            </c:if>
                                        </c:if>
                                        <c:if test="${ROLE == null}">
                                            <c:if test="${car.quantity > 0 }">
                                                <input type="button" value="Rent" class="btn btn-dark" onclick="return alert('Please Login to use this function!');"/>
                                            </c:if>
                                        </c:if>
                                        <c:if test="${car.quantity <= 0 }">
                                            <input type="button" value="Out of cart" class="btn btn-danger"/>
                                        </c:if>
                                    </div>
                                </div>
                                <div style="position: absolute; bottom: 5%; left: 3%; width: 100%">
                                    Name: ${car.carName}<br/>
                                    <input type="hidden" name="txtCarID" value="${car.carID}" />
                                    <c:if test="${car.color == 'White'}">
                                        Color: <font color="${car.color}" style="font-weight: 900;-webkit-text-stroke: 0.3px black;">${car.color}</font><br/>
                                    </c:if>
                                    <c:if test="${car.color != 'White' && car.color != 'Silver'}">
                                        Color: <font color="${car.color}" style="font-weight: 900;">${car.color}</font><br/>
                                    </c:if>
                                    <c:if test="${car.color == 'Silver'}">
                                        Color: <font color="${car.color}" style="font-weight: 900;-webkit-text-stroke: 0.3px black;">${car.color}</font><br/>
                                    </c:if>
                                    Type: ${car.categoryName}<br/>
                                    Year: ${car.year}<br/>
                                    Price: ${car.price}USD/1 Day<br/>
                                    Quantity Remain : ${car.quantity}/${car.maxquantity}<br/>
                                </div>
                            </div>
                        </form>
                    </c:forEach>
                </div>
            </div>
        </div>
        <div class="container-fulid">
            <div class="row">
                <div style="text-align: center;">
                    <c:forEach var="maxpage" begin="1" end="${MAXPAGE}">
                        <a href="SearchCar?pageNo=${maxpage}&txtSearch=${requestScope.KEYWORD}&cbbCategory=${requestScope.CATEID}&txtQuantity=${requestScope.QUANTITY}" class="myButton">${maxpage}</a>
                    </c:forEach>
                </div>
            </div>
        </div>
    </body>
</html>
