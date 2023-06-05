<%--
  Created by IntelliJ IDEA.
  User: barry
  Date: 2023-03-12
  Time: 1:22 p.m.
  To change this template use File | Settings | File Templates.
--%>

<%@ page import="java.util.List" %>
<%@ page import="com.example.assignment2servlets.Book" %>
<%@ page import="com.example.assignment2servlets.Author" %>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>View All My Books!</title>
</head>
<body>
<% List<Book> bookList = (List<Book>) request.getAttribute("booklist"); %>

<table>
    <tr>
        <th>ISBN</th>
        <th>Title</th>
        <th>Edition</th>
        <th>Copyright</th>
        <th>Author(s)</th>
    </tr>

        <%
        for (Book book : bookList) {
            out.print("<tr>");
            out.print("<td>" + book.getIsbn() + "</td>");
            out.print("<td>" + book.getTitle() + "</td>");
            out.print("<td>" + book.getEditionNumber() + "</td>");
            out.print("<td>" + book.getCopyright() + "</td>");
            out.print("<td>");
            for (Author author : book.getAuthorList()) {  out.println(author.getFirstName() + " " + author.getLastName() + ", ");
            }
            out.print("</td>");
            out.print("</tr>");
        }
    %>
</table>
<a href="index.jsp">Go Back Home</a>
</body>
</html>
