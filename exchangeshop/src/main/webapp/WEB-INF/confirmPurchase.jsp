<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Showing Product</title>
</head>
<body
	style="background-image: url('https://images.pexels.com/photos/630754/autumn-orange-nature-red-630754.jpeg?auto=compress&amp;cs=tinysrgb&amp;w=1260&amp;h=750&amp;dpr=1'); background-size: cover; background-repeat: no-repeat;">
	<div class="container mt-5" >
		<h1 style="text-align:center"> Xchange Shop </h1>
		<a href="/logout" style="float:right; font-size:large;">Logout</a>
		<h2 > Thank you, ${firstname}, for your purchase! </h2>
		<a href="/dashboard" style="float:right; font-size:large;"> Home </a>
		</br>
		<div style="display:flex; justify-content: center">
	<div >
		<p style="font-size:x-large;"> Your Purchase ID is ${onePurch.id } for purchase of ${onePurch.solditem.productname} </br>
		from seller ${onePurch.solditem.postor.userName} with seller rating ${onePurch.purchasor.sellerRating}STAR!</p>
		<c:if test="${onePurch.purchasor.willProvideFeedback}">
		<p style="font-size:large;"> Please submit your accurate seller review in reply to the email from Xchange shop ! </p>
		</c:if>
		</div>
		</div>
</body>
</html>