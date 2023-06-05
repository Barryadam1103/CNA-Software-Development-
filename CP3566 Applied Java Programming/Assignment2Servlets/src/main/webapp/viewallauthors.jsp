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
    <title>View All Authors</title>
</head>
<body>

<% List<Author> authorList =  (List<Author>) request.getAttribute("authorlist"); %>

<table>
    <tr>
        <th>AuthorID</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Books by author</th>
    </tr>

    <%
        for (Author author: authorList) {
            out.println("<tr valign='top'>");
            out.println("<td>" + author.getAuthorID() + "</td>");
            out.println("<td>" + author.getFirstName() + "</td>");
            out.println("<td>" + author.getLastName() + "</td>");
            out.println("<td>");
            for (Book book : author.getBookList()) {
                out.println(book.getTitle());
                out.println(book.getIsbn());
                out.println(book.getEditionNumber());
                out.println(book.getCopyright());
                out.println("<br>");
            }
            out.println("</td>");
            out.println("</tr>");
        }
    %>

</table>
<a href="index.jsp">Go Back Home</a>


</body>
</html>
