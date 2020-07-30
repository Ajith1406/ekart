<%@ page import="java.util.*,  com.ekart.entities.*" language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<jsp:include page="/header.jsp"></jsp:include>

<section>  
<div class="container ml-5">
	<div class="row rounded">
		<c:forEach var="item" items="${products}">
				<div class="card text-center m-2 p-3" style=" width:20rem; height:35rem;">
					<img class="card-img-top mx-auto" src="${item.imgUrl}" style="height:300px; width:200px;">
					<div class="card-body">
						<h2 class="card-title">${item.name}</h2>
				 			<h3> Rs.${item.price}</h3>
					</div>
				</div>
		</c:forEach>
	</div>  
</div>	
</section>

<jsp:include page="/footer.jsp"></jsp:include>
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
