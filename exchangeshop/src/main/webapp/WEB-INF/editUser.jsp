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
<title>Product Edit</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
</head>
<body
	style="background-image: url('https://images.pexels.com/photos/630754/autumn-orange-nature-red-630754.jpeg?auto=compress&amp;cs=tinysrgb&amp;w=1260&amp;h=750&amp;dpr=1'); background-size: cover; background-repeat: no-repeat;">
	<div class="container mt-5" >
		<h1 style="text-align:center"> Xchange Shop </h1>
		<a href="/logout" style="float:right; font-size:large;">Logout</a>
		<h2 > Welcome, ${firstname}! </h2>
		<a href="/dashboard" style="float:right; font-size:large;"> Home </a>
		</br>
				<h3 > Editing ${editAcct.userName}'s account ! </h3>
		
		<form:form action="/users/edit/${editAcct.id}" method="put" modelAttribute="editRegUser">
		
			<p>
				<form:label path="userName">Name:</form:label>
				<form:errors path="userName" style="color: red;" class="text-danger"/>
				<form:input path="userName" class="form-control" value="${editAcct.userName}"/>
			</p>
			
			<form:input type="hidden" path="email" value="${editAcct.email}" />
			<form:input type="hidden" path="password" value="${editAcct.password}"/>
			<form:input type="hidden" path="confirm" value="${editAcct.password}" />
			
			<p style="font-size: large;"> Address :
			<p>
				<form:label path="housenumber"> House Number :</form:label>
				<form:errors path="housenumber" style="color: red;" class="text-danger"/>
				<form:input path="housenumber" class="form-control" value="${editAcct.address.housenumber}"/>
			</p>
			<p>
				<form:label path="streetname"> Street Name :</form:label>
				<form:errors path="streetname" style="color: red;" class="text-danger"/>
				<form:input path="streetname" class="form-control" value="${editAcct.address.streetname}"/>
			</p>
			<p>
				<form:label path="city"> City :</form:label>
				<form:errors path="city" style="color: red;" class="text-danger"/>
				<form:input path="city" class="form-control" value="${editAcct.address.city}"/>
			</p>
			<p>
				<form:label path="state">State :</form:label>
				<form:errors path="state" style="color: red;" class="text-danger"/>
				<form:input path="state" class="form-control" value="${editAcct.address.state}"/>
			</p>
			<p>
				<form:label path="zipcode">Zipcode :</form:label>
				<form:errors path="zipcode" style="color: red;" class="text-danger"/>
				<form:input path="zipcode" class="form-control" value="${editAcct.address.zipcode}"/>
			</p>
			<p>
				<form:label path="willProvideFeedback">Will Provide Feedback (Yes or No) :</form:label>
				<form:errors path="willProvideFeedback" style="color: red;" class="text-danger"/>
				<form:input path="willProvideFeedback" class="form-control" value="${editAcct.willProvideFeedback}"/>
			</p>
				
			<input type="submit" value="Update User Information" class="btn btn-primary"/>
		</form:form>
		<br/> <br/>
		
		
	</div>
</body>
</html>