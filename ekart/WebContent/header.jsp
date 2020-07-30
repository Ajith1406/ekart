<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset='utf-8'>
    <meta http-equiv='X-UA-Compatible' content='IE=edge'>
    <title>Page Title</title>
    <meta name='viewport' content='width=device-width, initial-scale=1'>
   	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
</head>
<body>
<h3 class="text-white d-block text-center bg-dark p-5 mb-0">E-Kart</h3>
<nav class="navbar navbar-expand-sm navbar-light bg-light mb-2">
        <div class="container p-0">
            <a class="navbar-brand" href="/online_shopping/index">Ekart Home</a>
            <button class="navbar-toggler" data-toggle ="collapse"
            data-target="#navbarnav"> <span class="navbar-toggler-icon"></span></button>
            <div class="collapse navbar-collapse" id="navbarnav">
				 <ul class="navbar-nav ml-auto">
				 	  <li class="btn" style="font-size:20px;"><a class="nav-link" href="/online_shopping/viewcart">my kart</a></li>
					  <c:if test="${userId==null}">
					  <li class="btn btn-outline-success" style="font-size:20px; " ><a class="nav-link" href="/online_shopping/login">sign in</a></li>
					  
					  </c:if>
					  <c:if test="${userId!=null}">
					  <li class="btn" style="font-size:20px;"><a class="nav-link" href="/online_shopping/myorders">My Orders</a></li>
					  <li class="btn btn-outline-danger" style="font-size:20px; "><a class="nav-link" href="/online_shopping/sign-out">sign out</a></li>
					 </c:if>
				     
				 	
				 </ul>
			</div>
		</div>
</nav>
 <c:if test="${param.message != null}"> <div class="alert alert-danger text-center" role="alert">${param.message}</div></c:if>

 