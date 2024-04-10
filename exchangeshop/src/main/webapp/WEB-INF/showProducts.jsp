<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Products Dashboard</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
</head>
<body
	style="background-image: url('https://images.pexels.com/photos/630754/autumn-orange-nature-red-630754.jpeg?auto=compress&amp;cs=tinysrgb&amp;w=1260&amp;h=750&amp;dpr=1'); background-size: cover; background-repeat: no-repeat;">
	<div class="container mt-5">
		<h1 style="text-align:center"> Xchange Shop </h1>
		<a href="/logout" style="float:right; font-size:large;">Logout</a>
		<h2 > Welcome, ${firstname}! </h2>
		<a href="/dashboard" style="float:right; font-size:large;"> Home </a>
		
	</div>
				<h1>Products in Category <c:out value="${category}"></c:out></h1>
		
		<table class="table" style="width: 100%; border: 2px solid black;">
			<thead style="text-align: center">
				<tr>
					<th>Product Name</th>
					<th>Year of Make</th>
					<th>Condition</th>
					<th>Price</th>	
					<th>Image</th>	
					<th>Actions</th>
				</tr>
			</thead>
			<tbody style="text-align: center">
				<c:forEach var="eachProduct" items="${products}">
		<c:if test="${!eachProduct.isSold}">
					<tr>
						<td><c:out value="${eachProduct.productname}"></c:out></td>
						<td><c:out value="${eachProduct.year}"></c:out></td>
						<td><c:out value="${eachProduct.amtused}"></c:out></td>
						<td><c:out value="${eachProduct.price}"></c:out></td>
						<td><img src="${eachProduct.productImage}" alt="Product Image" width="100" height="100"></td>
						<td><a href="/products/view/${eachProduct.id}">View details</a> </td>
					</tr>
					</c:if>
		<c:if test="${eachProduct.isSold}">
					<tr>
						<td><c:out value="${eachProduct.productname}"></c:out></td>
						<td><c:out value="${eachProduct.year}"></c:out></td>
						<td><c:out value="${eachProduct.amtused}"></c:out></td>
						<td><c:out value="${eachProduct.price}"></c:out></td>
						<td><img src="${eachProduct.productImage}" alt="Product Image" width="100" height="100"></td>
						<td style="color:red; font-size:large;">SOLD</td>
					</tr>
		</c:if>
				</c:forEach>
			</tbody>
		</table>	
</body>
</html>