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
		<h2 > Welcome, ${firstname}! </h2>
		<a href="/dashboard" style="float:right; font-size:large;"> Home </a>
		</br>
		<c:choose>
		<c:when test="${userId.equals(product.postor.id)}">
			<a href="/products/edit/${product.id}" style="float:left; font-size:large;"> Edit Posting</a>		
		</c:when>
		<c:otherwise>
		<a></a>
		</c:otherwise>
		</c:choose>
		</br>
			<div style="display:flex; justify-content: center">
	<div >
<h1>Posting of <c:out value="${product.productname}"/> into "${product.productcategory}"</h1>
<p style="font-size:large;"> Year of Make:  <c:out value="${product.year}"/> </p>
<p style="font-size:large;"> Condition:  <c:out value="${product.amtused}"/> </p>
<p style="font-size:large;"> Price($):  <c:out value="${product.price}"/> + $5 (for Standard Shipping)</p>
<span>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp; Product must arrive in a week from Date of Purchase </span>
<p> Image:  <img src="${product.productImage}" alt="Product image" width="100" height="100"/> </p>
<p style="font-size:large;"> Seller : <c:out value="${product.postor.userName}"/>  </p>
<p style="font-size:large;"> Seller Rating : <c:out value="${product.postor.sellerRating}"/>  </p>
<p style="font-size:large;"> Seller Contact : <c:out value="${product.postor.email}"/>  </p>
<p style="font-size:large;">  

"Seller Rating" assigned based on purchasor feedback regarding:
<p style="font-size:large;"> 
************************ </br>
1. Shipment received on time (within a week of Date of purchase)</br> 
2. Prompt response to queries from purchasor (within 24 hrs)</br>
3. Product meeting expecatation as per advertisement !</br></br>
 ************************ </p>
</p> 
		<a href="/products/${product.productcategory}/purchase">	<input type="button" value="Confirm & Go Back" class="btn btn-primary"/></a> &emsp;&emsp;&emsp;&emsp;
		<c:choose>
		<c:when test="${!userId.equals(product.postor.id)}">
				<a href="/purchases/account/${product.id}">	<input type="button" value="Confirm Account Details for Purchase" class="btn btn-primary"/></a> &emsp;&emsp;&emsp;&emsp;
				
		</c:when>
		<c:otherwise>
		<a></a>
		</c:otherwise>
		</c:choose>
</div>
</div>
	</div>
</body>
</html>
