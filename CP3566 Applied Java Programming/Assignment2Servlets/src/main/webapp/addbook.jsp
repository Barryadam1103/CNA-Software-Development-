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
    <title>Add Book</title>
</head>
<body>
<h1>Add A Book</h1>

<form action="library-data" method= "POST">
    ISBN: <input type = "text" name = "isbn"> <br />
    Title: <input type = "text" name = "title" /> <br />
    Edition Number: <input type = "text" name = "edition" /> <br />
    Copyright: <input type = "text" name = "copyright" />
    Author ID : <input type = "text" name = "id">
    <input type="hidden" id="view" name="view" value="add_book">
    <input type = "submit" value = "Submit" />
</form>
<a href="index.jsp">Home</a>
</body>
</html>
