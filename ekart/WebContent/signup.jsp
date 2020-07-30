<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="css/style.css">
<style>
body {font-family: Arial, Helvetica, sans-serif;}
form {border: 3px solid #f1f1f1;
width:50%;
top: 50%;
    left: 50%;
    margin-right: -50%;
    transform: translate(50%, 0%)}

input[type=text], input[type=password] {
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

img.avatar {
  width: 40%;
  border-radius: 50%;
}

.container {
  padding: 16px;
}

span.psw {
  float: right;
  padding-top: 16px;
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
}
</style>
</head>
<body>

<jsp:include page="/header.jsp"></jsp:include>
<c:if test="${loginMessage != null}"> <div class="alert alert-danger text-center" role="alert">${loginMessage}</div></c:if>
<h2 style="text-align:center">Sign Up Form</h2>

<form action="/online_shopping/signup" method="post">
  <div class="container">
   <label for="uname"><b>Email ID</b></label>
    <input type="text" placeholder="Enter Username" name="email_id" required>
  
    <label for="uname"><b>Name</b></label>
    <input type="text" placeholder="Enter Username" name="name" required>

    <label for="psw"><b>Password</b></label>
    <input type="password" placeholder="Enter Password" name="password" required>
        
    <button type="submit">Sign Up</button>
  </div>

  <div class="container" style="background-color:#f1f1f1; text-align: center">
    <label style="padding:20px">Already signed up?</label><button type="button" class="cancelbtn" onclick="window.location='/online_shopping/login'">Log in</button>
  </div>
</form>

</body>
</html>
