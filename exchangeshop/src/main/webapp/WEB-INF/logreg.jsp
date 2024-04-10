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
</head>
<body
	style="background-image: url('https://images.pexels.com/photos/630754/autumn-orange-nature-red-630754.jpeg?auto=compress&amp;cs=tinysrgb&amp;w=1260&amp;h=750&amp;dpr=1'); background-size: cover; background-repeat: no-repeat;">
	<div class="container mt-5">
		<h1>
			Register &emsp; &emsp; &emsp; &emsp; &emsp; &emsp; <span>
				Xchange Shop </span>
		</h1>


		<form:form action="/register" method="post"
			modelAttribute="newRegister">
			<div style="display: flex; flex-wrap: wrap;">
				<div class="form-group" style="width: 45%;">
					<p>
						<form:label path="userName">Username (Full Name):</form:label>
						<form:input type="text" path="userName" class="form-control" />
						<form:errors path="userName" style="color: red;"
							class="text-danger" />
					</p>
					<p>
						<form:label path="email">Email (valid email for notification):</form:label>
						<form:input type="text" path="email" class="form-control" />
						<form:errors path="email" style="color: red;" class="text-danger" />

					</p>
					<p>
						<form:label path="password">Password (for login):</form:label>
						<form:input type="password" path="password" class="form-control" />
						<form:errors path="password" style="color: red;"
							class="text-danger" />

					</p>
					<p>
						<form:label path="confirm">Confirm password:</form:label>
						<form:input path="confirm" type="password" class="form-control" />
						<form:errors path="confirm" style="color: red;"
							class="text-danger" />
					</p>
					<div style="display:flex">
					<div>
					<form:label path="willProvideFeedback">Will provide feedback (Enter Yes or No)</form:label>
					<form:input type="text" path="willProvideFeedback" class="form-control" style="width: 40%;"/>
					<form:errors path="willProvideFeedback" style="color: red;" class="text-danger" />
					
					</div> 
					<div></br><input type="submit" class="btn btn-primary"
						value="Register" /> </div>
					</div>
				</div>
				&emsp;&emsp;&emsp;
				<div class="form-group" style="width: 50%;">


					<p>
						<form:label path="housenumber">House Number:</form:label>
						<form:input type="text" path="housenumber"
							class="form-control" />
						<form:errors path="housenumber" style="color: red;"
							class="text-danger" />
					</p>
					<p>
						<form:label path="streetname">Street Name:</form:label>
						<form:input type="text" path="streetname"
							class="form-control" />
						<form:errors path="streetname" style="color: red;"
							class="text-danger" />

					</p>
					<p>
						<form:label path="city">City:</form:label>
						<form:input type="text" path="city" class="form-control" />
						<form:errors path="city" style="color: red;"
							class="text-danger" />

					</p>
					<p>
						<form:label path="state">State:</form:label>
						<form:input path="state" type="text" class="form-control" />
						<form:errors path="state" style="color: red;"
							class="text-danger" />
					</p>
					<p>
						<form:label path="zipcode">Zipcode:</form:label>
						<form:input path="zipcode" type="number"
							class="form-control" />
						<form:errors path="zipcode" style="color: red;"
							class="text-danger" />

				</div>

			</div>
		</form:form>
		<h1>Login</h1>
		<form:form action="/login" method="POST" modelAttribute="newLogin">
			<p>
			<p>
				<form:label path="email">Email:</form:label>
				<form:input type="text" path="email" class="form-control" />
				<form:errors path="email" style="color: red;" class="text-danger" />

			</p>
			<p>
				<form:label path="password">Password:</form:label>
				<form:input type="password" path="password" class="form-control" />
				<form:errors path="password" style="color: red;" class="text-danger" />
			</p>
			<input type="submit" value="Login" class="btn btn-primary" />
		</form:form>
	</div>
</body>
</html>