<%@ page import="java.util.*,  com.ekart.entities.*" language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<jsp:include page="/header.jsp"></jsp:include>
<div class="container m-2">
<div class="row">
	<div class="col">
		<div class="container">
		<table style="border: 10px; margin: 20px">
		  	<c:forEach var="item" items="${products}">
		  		<tr style="margin: 50px">
		  			<td><div>
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
	<table class="table">
            <thead class="thead-dark">
                <tr>
                    <th>#</th>
                    <th>Product Name</th>
                    <th>Product Price</th>
                </tr>
            </thead>
            <tbody>
                <%int i=1; %>
                	<c:forEach var="item" items="${products}">
                	<tr>
	                    <th scope="row"><%=i %></th>
	                    <td>${item.name}</td>
	                    <td>${item.price}</td>
	                    <%i++; %>
	                </tr>
                    </c:forEach>
                
                <tr>
                <td></td>
                <th>TOTAL</th>
                <th>${total}</th>
                </tr>
                
            </tbody>
        </table>
	
		<h2 style="text-align:centre">Total: ${total}</h2>
		<input class="btn btn-danger btn-p-5" type="button" onClick="window.location='/online_shopping/viewcart'" value="Back" value="Back">
		<input class="btn btn-success btn-p-5 float-right"type="button" onClick="confirmation()" value="CONFIRM">
  	
	</div>

</div>
</div>

<script>
function confirmation() {
  var txt;
  var confirmation = prompt("Please enter CONFIRM to confirm:", "CONFIRM");
  if (confirmation == "confirm" || confirmation == "CONFIRM") {
	  location.replace('/online_shopping/confirmation?confirm=yes');
  } else {
	  location.replace("/online_shopping/confirmation?confirm=no");
  }
}
</script>
</html>
