<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
  <title>Insert Product</title>
  <style type="text/css">
    form {
  /* Center the form on the page */
  margin: 0 auto;
  width: 400px;
  /* Form outline */
  padding: 1em;
  border: 1px solid #CCC;
  border-radius: 1em;
}

ul {
  list-style: none;
  padding: 0;
  margin: 0;
}

form li + li {
  margin-top: 1em;
}

label {
  /* Uniform size & alignment */
  display: inline-block;
  width: 30%px;
  text-align: left;
}

input, 
textarea {
  /* To make sure that all text fields have the same font settings
     By default, textareas have a monospace font */
  font: 1em sans-serif;

  /* Uniform text field size */
  width: 70%;
  height: 30px;
  box-sizing: border-box;

  /* Match form field borders */
  border: 1px solid #999;

}

input:focus, 
textarea:focus {
  /* Additional highlight for focused elements */
  border-color: #000;
}

textarea {
  /* Align multiline text fields with their labels */
  vertical-align: top;

  /* Provide space to type some text */
  height: 5em;
}

.button {
  /* Align buttons with the text fields */
  padding-left: 90px; /* same size as the label elements */
}

button {
  /* This extra margin represent roughly the same space as the space
     between the labels and their text fields */
  margin-left: .5em;
}

  </style>
</head>
<body>
<jsp:include page="/header.jsp"></jsp:include>

  <h2 style="text-align: center">Insert Product</h2>
<form action="/addProduct" method="post">
 <ul>
  <li>
    <label for="name">Product Name:</label>
    <input type="text" id="product_name" name="user_name">
  </li>

  <li>
    <label for="name">Product Id:</label>
    <input type="number" id="product_id" name="user_name">
  </li>

  <li>
    <label for="name">Image Url:</label>
    <input type="text" id="product_imgUrl" name="user_name">
  </li>

  <li>
    <label for="name">Product price:</label>
    <input type="number" id="product_price" name="user_name">
  </li>
  <li>
    <input type="button" name="addProduct" value="Add Item">
  </li>
 </ul>
</form>
</body>
</html>