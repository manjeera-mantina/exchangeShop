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
<title>Post a Product</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
</head>
<body
	style="background-image: url('https://images.pexels.com/photos/630754/autumn-orange-nature-red-630754.jpeg?auto=compress&amp;cs=tinysrgb&amp;w=1260&amp;h=750&amp;dpr=1'); background-size: cover; background-repeat: no-repeat;">
	<div class="container mt-5">
		<h1 style="text-align:center"> Xchange Shop </h1>
		<a href="/logout" style="float:right; font-size:large;">Logout</a>
		<h2 > Welcome, ${firstname}! </h2>
		<h1>New Product Posting in ${newProd.productcategory} </h1>
		<a href="/dashboard" style="float:right; font-size:large;"> Home </a></br>
		<form:form action="/products/new" method="post" modelAttribute="newProd">
		<form:hidden path="postor" value="${userId}"/>
			<p>
				<form:label path="productname">Name:</form:label>
				<form:errors path="productname" style="color: red;" class="text-danger"/>
				<form:input path="productname" class="form-control"/>
			</p>
				<form:input type="hidden" path="productcategory" value="${newProd.productcategory}"/>
			
			<p>
				<form:label path="year">Year of Make:</form:label>
				<form:errors path="year" style="color: red;" class="text-danger"/>
				<form:input path="year" class="form-control"/>
			</p>
			<p>
				<form:label path="amtused">Condition(New,Good,Ok):</form:label>
				<form:errors path="amtused" style="color: red;" class="text-danger"/>
				<form:input path="amtused" class="form-control"/>
			</p>
			<p>
				<form:label path="productImage">Image (URL):</form:label>
				<form:errors path="productImage" style="color: red;" class="text-danger"/>
				<form:input path="productImage" class="form-control"/>
			</p>		
			<p>
				<form:label path="price">Price($):</form:label>
				<form:errors path="price" style="color: red;" class="text-danger"/>
				<form:input path="price" class="form-control"/>
			</p>
			
			   <c:set var = "dateOfPosting" value = "<%= new java.util.Date()%>" />
			   <p> Date of Posting: <fmt:formatDate pattern = "yyyy-MM-dd" value = "${dateOfPosting}" /></p>
			   <form:input type="hidden" path="dateOfPosting" value="${dateOfPosting}"/>
							   
				<form:input type="hidden" path="isSold" value="false"/>
				
				<input type="submit" value="Review Post" class="btn btn-primary"/>
		</form:form>
	</div>
</body>
</html>