<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c" %>
    
<jsp:include page="/header.jsp"></jsp:include>

<section>  
<div class="container mx-auto">
	<div class="row text-center">
		<c:forEach var="item" items="${products}">
				<div class="card m-2 p-3 rounded" style=" width:20rem; height:35rem;">
					<img class="card-img-center mx-auto" src="${item.imgUrl}" style="height:300px; width:200px;">
					<div class="card-body">
						<h2 class="card-title">${item.name}</h2>
				 			<p class="lead">Rating: ${item.rating}</p>
				 			<h3> Rs.${item.price}</h3>
				 			<input class="btn btn-outline-warning" type="button" onClick="window.location='/online_shopping/viewcart?id=${item.id}'" value="Add to Cart">
						<input class="btn btn-outline-success" type="button" onClick="window.location='/online_shopping/checkout?orderId=${item.id}'" value="Buy Now" >
					</div>
				</div>
		</c:forEach>
	</div>
</div>
	
</section>

<jsp:include page="/footer.jsp"></jsp:include>