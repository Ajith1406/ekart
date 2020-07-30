<%@ page import="java.util.*,  com.ekart.entities.*" language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<jsp:include page="/header.jsp"></jsp:include>

<div class="row">
	<div class="col">
		<div class="container mx-auto">
		<table style="border: 10px; margin: 20px">
		  	<c:forEach var="item" items="${products}">
		  		<tr style="margin: 50px">
		  			<td><div class="mx-auto">
		  				<img src="${item.imgUrl}" style="height: 300px; width:200px">
					  	</div>
					 </td>
		  			<td>
		  				<div style="padding: 40px">
				  			<p>${item.name}</p>
				  			<p>${item.rating}</p>
				  			<p>${item.price}</p>	
		  				</div>
		  			</td>
		  			<td>
		  			<input class="btn btn-danger" type="button" onClick="window.location='/online_shopping/viewcart/remove?removeItemId=${item.id}'" value="Remove Item" style="padding: 10px">
		  			</td>
		  		</tr>
		  	</c:forEach>
	  	</table>
		</div>
	</div>
	<div class="col align-self-center">
		<h2 style="text-align:centre">Total: ${total}</h2>
		<input class="btn btn-success btn-p-5" type="button" onClick="window.location='/online_shopping/index'" value="Continue Shopping">
		<input class="btn btn-info btn-p-5"type="button" onClick="window.location='/online_shopping/checkout'" value="Checkout">
  	
	</div>

</div>
