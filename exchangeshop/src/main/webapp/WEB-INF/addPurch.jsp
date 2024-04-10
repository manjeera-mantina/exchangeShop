<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register a Purchase</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
</head>
<body
	style="background-image: url('https://images.pexels.com/photos/630754/autumn-orange-nature-red-630754.jpeg?auto=compress&amp;cs=tinysrgb&amp;w=1260&amp;h=750&amp;dpr=1'); background-size: cover; background-repeat: no-repeat;">
	<div class="container mt-5">
		<h1 style="text-align:center"> Xchange Shop </h1>
		<a href="/logout" style="float:right; font-size:large;">Logout</a>
		<h2 > Welcome, ${firstname}! </h2>
		<h1> New Purchase Review </h1>
		<a href="/dashboard" style="float:right; font-size:large;"> Home </a></br>
		<form:form action="/purchases/confirm" method="post" modelAttribute="newPurch">
		<form:hidden path="purchasor" value="${userId}"/>
		<form:hidden path="solditem" value="${prodId}"/>
				
			<p>
				<form:label path="prodresponsemet">Prompt query response rating(1, 2 or 3):</form:label>
				<form:errors path="prodresponsemet" style="color: red;" class="text-danger"/>
				<form:input path="prodresponsemet" type="number" class="form-control"/>
			</p>		
								
								   <c:set var = "intProdId" value = "${prodId}" />
							<form:input type="number" path="prodexpectationmet" value="${intProdId}" style="visibility: hidden"/>
							<form:input type="number" path="prodtimelinemet" value="${intProdId}" style="visibility: hidden"/>
			
			   <c:set var = "dateOfPurchase" value = "<%= new java.util.Date()%>" />
			   <p> Date of Purchase: <fmt:formatDate pattern = "yyyy-MM-dd" value = "${dateOfPurchase}" /></p>
			   <form:input type="hidden" path="dateOfPurchase" value="${dateOfPurchase}"/>
			   <c:choose>
			   <c:when test="${userInfo.willProvideFeedback}">
			   	<c:set var = "discountPercent" value = "${newPurch.discountPercent+5}" />
			   	</c:when>
			   	<c:otherwise>
			   	<c:set var = "discountPercent" value = "${newPurch.discountPercent}" />
			   	</c:otherwise>
			   	</c:choose>
			   	<p> Discount Percent: <c:out value="${discountPercent}"></c:out></p>
				<form:input type="hidden" path="discountPercent" value="${discountPercent}"/>
				
				<input type="submit" value="Purchase" class="btn btn-primary"/>
		</form:form>
	</div>
</body>
</html>