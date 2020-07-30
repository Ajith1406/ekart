<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
* {box-sizing: border-box}
body {font-family: "Lato", sans-serif;}

/* Style the tab */
.tab {
  float: left;
  border: 1px solid #ccc;
  background-color: #f1f1f1;
  width: 20%;
  height: 300px;
}

/* Style the buttons inside the tab */
.tab button {
  display: block;
  background-color: inherit;
  color: black;
  padding: 22px 16px;
  width: 100%;
  border: none;
  outline: none;
  text-align: left;
  cursor: pointer;
  transition: 0.3s;
  font-size: 17px;
}

/* Change background color of buttons on hover */
.tab button:hover {
  background-color: #ddd;
}

/* Create an active/current "tab button" class */
.tab button.active {
  background-color: #ccc;
}

/* Style the tab content */
.tabcontent {
  float: left;
  padding: 0px 12px;
  border: 1px solid #ccc;
  width: 80%;
  border-left: none;
  height: 100%;
  
}
.tabcontent h3{
text-align: center;
}

/* Form styles */
body {font-family: Arial, Helvetica, sans-serif;}
form {border: 3px solid #f1f1f1;
margin:20px;
width:50%;
top: 50%;
    left: 50%;
    margin-right: -50%;
    transform: translate(50%, 0%)}

input[type=text], input[type=number] {
  width: 100%;
  padding: 12px 20px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid #ccc;
  box-sizing: border-box;
}
select{
  width: 100%;
  padding: 12px 20px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid #ccc;
  box-sizing: border-box;
}

button {
  background-color: #4CAF50;
  color: white;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  cursor: pointer;
  width: 100%;
}

button:hover {
  opacity: 0.8;
}

.cancelbtn {
  width: auto;
  padding: 10px 18px;
  background-color: #f44336;
}

.imgcontainer {
  text-align: center;
  margin: 24px 0 12px 0;
}

.container {
  padding: 16px;
}

span.psw {
  float: right;
  padding-top: 16px;
}
#users {
  font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
  border-collapse: collapse;
  width: 50%;
}

#users td, #users th {
  border: 1px solid #ddd;
  padding: 8px;
}

#users tr:nth-child(even){background-color: #f2f2f2;}

#users tr:hover {background-color: #ddd;}

#users th {
  padding-top: 12px;
  padding-bottom: 12px;
  text-align: left;
  background-color: #4CAF50;
  color: white;
}

/* Change styles for span and cancel button on extra small screens */
@media screen and (max-width: 300px) {
  span.psw {
     display: block;
     float: none;
  }
  .cancelbtn {
     width: 100%;
  }
</style>
</head>
<body>
<jsp:include page="/header.jsp"></jsp:include>
<!-- <div style="text-align:center">
<h2>Admin</h2>
<p>You can add or delete Items</p>
</div> -->
<div class="tab">
  <button class="tablinks" onclick="openTab(event, 'Insert')" id="defaultOpen">Add Item</button>
 <!--  <button class="tablinks" onclick="openTab(event, 'Update')">Update Item</button>
  <button class="tablinks" onclick="openTab(event, 'Delete')">Delete Item</button>
  <button class="tablinks" onclick="openTab(event, 'Category')">Add Category</button> -->
  <button class="tablinks" onclick="openTab(event, 'MakeAdmin')">Make Admin</button>
</div>

<div id="Insert" class="tabcontent">
  <h3>Enter New Product</h3>
  <form action="/online_shopping/admin/addproduct" method="post">
  <div class="container">
  
  	<!-- <label for="category"><b>Select Category</b></label>
	<select name="category">
	<option value="">category-1</option>
	<option value="">category-3</option>
	<option value="">category-2</option>
	</select> -->    
	
    <label for="product_name"><b>Product Name</b></label>
    <input type="text" placeholder="Enter Product Name" name="product_name" required>

    <label for="imgurl"><b>Image URL</b></label>
    <input type="text" placeholder="Enter product Image URL" name="imgurl" required>
    
    <label for="product_price"><b>Product Price</b></label>
    <input type="number" placeholder="Enter Product Price" name="product_price" required>
    
    <label for="product_rating"><b>Product Rating</b></label>
    <input type="number" placeholder="Enter Product Rating" name="product_rating" required>
    
        
    <button type="submit" style="width:100%;text-align:center;">Add Item</button>
    <label>
    </label>
  </div>
  </form>
</div>

<div id="Update" class="tabcontent">
  <h3>View a products</h3>
</div>

<!-- <div id="Delete" class="tabcontent">
 <h3>Delete a product</h3>
</div> -->
<div id="MakeAdmin" class="tabcontent">
  <h3>Users</h3>
  <table id="users">
  <tr><th>Name</th><th>Mail Id</th><th>Access</th></tr>
  <c:forEach var="user" items="${users}">
  <c:if test="${!user.admin}">
  <tr>
  <td>${user.name}</td>
  <td>${user.emailId}</td>
  <td><a href="/online_shopping/admin/makeadmin?userid=${user.userId}">Make Admin</a></td>
  </tr>
  </c:if>
  </c:forEach>
  </table>
  
 <h3>Admins</h3>
  <table id = "users">
  <tr><th>Name</th><th>Mail Id</th></tr>
  <c:forEach var="user" items="${users}">
  <c:if test="${user.admin}">
  <tr>
  <td>${user.name}</td>
  <td>${user.emailId}</td>
  
  </tr>
  </c:if>
  </c:forEach>
  </table>
</div>

<!-- <div id="Category" class="tabcontent">
  <form action="#" method="post">
  <div class="container">
 	
    <label for="product_name"><b>Category Name</b></label>
    <input type="text" placeholder="Enter Product Name" name="category_name" required>
        
    <button type="submit" style="width:100%; text-align:center;">Add Item</button>
    <label>
    </label>
  </div>
</form>
</div> -->

<script>
function openTab(evt, tabClass) {
  var i, tabcontent, tablinks;
  tabcontent = document.getElementsByClassName("tabcontent");
  for (i = 0; i < tabcontent.length; i++) {
    tabcontent[i].style.display = "none";
  }
  tablinks = document.getElementsByClassName("tablinks");
  for (i = 0; i < tablinks.length; i++) {
    tablinks[i].className = tablinks[i].className.replace(" active", "");
  }
  document.getElementById(tabClass).style.display = "block";
  evt.currentTarget.className += " active";
}

// Get the element with id="defaultOpen" and click on it
document.getElementById("defaultOpen").click();
</script>
   
</body>
</html> 
