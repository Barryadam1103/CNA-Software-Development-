<%--
  Created by IntelliJ IDEA.
  User: barry
  Date: 2023-03-09
  Time: 7:10 p.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>AddAuthor</title>
</head>
<body>
<h1>Add An Author</h1>
<form action="library-data" method="POST">
  Author ID : <input type = "text" name = "id"> <br />
  First Name : <input type = "text" name = "firstname"> <br />
  Last Name: <input type = "text" name = "lastname" /> <br />
  <input type="hidden" id="view" name="view" value="add_author">
  <input type = "submit" value = "Submit" />


</form>
<a href="index.jsp">Home</a>
</body>
</html>
