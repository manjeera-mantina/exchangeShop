<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Showing User</title>
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
				<h3 > ${firstname}'s Account Information </h3>
		
		<c:choose>
		<c:when test="${userId.equals(purchasorinfo.id)}">
			<a href="/users/edit/${purchasorinfo.id}" style="float:left; font-size:large;"><input type="button" value="Edit Account Info"></a>	
		</c:when>
		<c:otherwise>
		<a></a>
		</c:otherwise>
		</c:choose>
		</br>
			<div style="display:flex; justify-content: center">
	<div >
<p style="font-size:large;"> Full Name:  <c:out value="${purchasorinfo.userName}"/> </p>
<p style="font-size:large;"> Email:  <c:out value="${purchasorinfo.email}"/> </p>
<p style="font-size:large;"> Address:  <c:out value="${purchasorinfo.address.housenumber}"/>, <c:out value="${purchasorinfo.address.streetname}"/></p>
<span style="font-size:large;">&emsp;&emsp;&emsp;&emsp; <c:out value="${purchasorinfo.address.city}"/>, <c:out value="${purchasorinfo.address.state}"/>, <c:out value="${purchasorinfo.address.zipcode}"/></span>
<p style="font-size:large;"> Will Provide Feedback:  <c:out value="${purchasorinfo.willProvideFeedback}"/> </p>
<p style="font-size:large;"> Seller Rating : <c:out value="${purchasorinfo.sellerRating}"/>  </p></br></br>


<h4> List of Purchases </h4>
<table class="table" style="width: 100%; border: 2px solid black;">
			<thead style="text-align: center">
				<tr>
					<th>Product Name</th>
					<th>Category Name</th>
					<th>Price</th>	
					<th>Image</th>	
					<th>Seller</th>
					<th>Date of Purchase</th>
					<th>More..<th>
					
				</tr>
			</thead>
			<tbody style="text-align: center">
				<c:forEach var="eachPurchase" items="${purchasorinfo.userPurchases}">
					<tr>
						<td><c:out value="${eachPurchase.solditem.productname}"></c:out></a></td>
						<td><c:out value="${eachPurchase.solditem.productcategory}"></c:out></td>
						<td><c:out value="${eachPurchase.solditem.price}"></c:out></td>
						<td><img src="${eachPurchase.solditem.productImage}" alt="Product image" width="100" height="100"/></td>
						<td><c:out value="${eachPurchase.solditem.postor.userName}"></c:out></td>
						<td><fmt:formatDate pattern = "yyyy-MM-dd" value = "${eachPurchase.dateOfPurchase}" /></td>
						<td>Seller received ${purchasorinfo.sellerRating} STAR Seller rating and Purchasor won ${eachPurchase.discountPercent}% discount ! </td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
<h4> List of Postings </h4>
<table class="table" style="width: 100%; border: 2px solid black;">
			<thead style="text-align: center">
				<tr>
					<th>Product Name</th>
					<th>Category Name</th>
					<th>Price</th>	
					<th>Image</th>	
					<th>Date of Posting</th>
				</tr>
			</thead>
			<tbody style="text-align: center">
				<c:forEach var="eachPosting" items="${purchasorinfo.userPosts}">
					<tr>
						<td><c:out value="${eachPosting.productname}"></c:out></a></td>
						<td><c:out value="${eachPosting.productcategory}"></c:out></td>
						<td><c:out value="${eachPosting.price}"></c:out></td>
						<td><img src="${eachPosting.productImage}" alt="Product image" width="100" height="100"/> </td>
						<td><fmt:formatDate pattern = "yyyy-MM-dd" value = "${eachPosting.dateOfPosting}" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>	
<h4> Current viewing Product </h4>
<table class="table" style="width: 100%; border: 2px solid black;">
			<thead style="text-align: center">
				<tr>
					<th>Product Name</th>
					<th>Category Name</th>
					<th>Price</th>	
					<th>Image</th>
					<th>Seller Rating</th>	
					<th>Date of Posting</th>
				</tr>
			</thead>
			<tbody style="text-align: center">
					<tr>
						<td><c:out value="${purchasingprodinfo.productname}"></c:out></a></td>
						<td><c:out value="${purchasingprodinfo.productcategory}"></c:out></td>
						<td><c:out value="${purchasingprodinfo.price}"></c:out></td>
						<td><img src="${purchasingprodinfo.productImage}" alt="Product image" width="100" height="100"/></td>
						<td><c:out value="${purchasingprodinfo.postor.sellerRating}"></c:out></td>	
						<td><fmt:formatDate pattern = "yyyy-MM-dd" value = "${purchasingprodinfo.dateOfPosting}" /></td>
					</tr>
			</tbody>
		</table>	
		
		</br> </br>
		<a href="/products/${purchasingprodinfo.productcategory}/purchase">	<input type="button" value="Go Back" class="btn btn-primary"/></a> &emsp;&emsp;&emsp;&emsp;
		<a href="/purchases/${purchasingprodinfo.id}"><input type="button" value="Purchase Product" class="btn btn-primary"/></a>
			 	
</div>
</div>
	</div>
</body>
</html>
