<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registration and Login</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script>
$(document).onchange(function(){
  $("#processselect").change(function(){
	  $("#categoryselectlabel").show();
    $("#categoryselect").show();
  });
});
</script>
</head>
<body
	style="background-image: url('https://images.pexels.com/photos/630754/autumn-orange-nature-red-630754.jpeg?auto=compress&amp;cs=tinysrgb&amp;w=1260&amp;h=750&amp;dpr=1'); background-size: cover; background-repeat: no-repeat;">
	<div class="container mt-5">
		<h1 style="text-align:center"> Xchange Shop </h1>
		<a href="/logout" style="float:right; font-size:large;">Logout</a>
		<h2 > Welcome, ${firstname}! </h2>
	</div>
<form action="/process" class="form-group" method="post"> 

<h3 style="text-align:center"> Choose your Action </h3>

<select id="processselect" name="processchosen" class="form-select">
<option  value="purchase" style="font-weight:bold;">Purchase</option>
<option  value="post" style="font-weight:bold;">Post</option>
</select>

<h3 id="categoryselectlabel" style="text-align:center"> Choose the Category </h3>
<select id="categoryselect" name="categorychosen" class="form-select">
<option value="Electronics" style="font-weight:bold;">Electronics</option>
<option value="Houseware" style="font-weight:bold;">Houseware</option>
<option value="Daily needs" style="font-weight:bold;">Daily needs</option>
</select> </br>
<button type="submit" style="margin:0px auto" class="btn btn-primary"> Get Output </button>

</form>

</body>
</html>