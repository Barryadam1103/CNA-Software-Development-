package com.example.assignment2servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Adam Barry
 * March 21st 2023
 *
 * The LibraryData class handles all the Doget and doPosts to the jsps/servlets.
 */
@WebServlet(name = "LibraryData", value = "/library-data")
public class LibraryData extends HttpServlet {

    private String welcomeMessage;
    private Library library = DBConnection.loadLibrary();

    public LibraryData() throws SQLException {
    }

    public void init() {
        welcomeMessage = "Landing page for library data";

    }

    /**
     * doGet gets the lists of authors and books and displays them on the server.
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        String view = request.getParameter("view");

        if (view.equals("books")) {
            List<Book> bookList = null;
            try {
                bookList = library.getBookList();
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/viewallbooks.jsp");
                request.setAttribute("booklist", bookList);
                requestDispatcher.forward(request, response);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else if (view.equals("authors")) {
            List<Author> authorList = null;
            try {
                authorList = library.getAuthorList();
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/viewallauthors.jsp");
                request.setAttribute("authorlist", authorList);
                requestDispatcher.forward(request, response);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * doPost posts the information form the addbook and addauthor jsp.
     * @param request
     * @param response
     * @throws IOException
     */

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String view = request.getParameter("view");

        if (view.equals("add_book")) {
            try {
                DBConnection.addBook(
                        library,
                        Integer.parseInt(request.getParameter("id")),
                        new Book(
                                request.getParameter("isbn"),
                                request.getParameter("title"),
                                Integer.valueOf(request.getParameter("edition")),
                                request.getParameter("copyright")
                        ));
                        //new Author(0, request.getParameter("firstname"), request.getParameter("lastname"));
                response.sendRedirect("confirm.jsp");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (view.equals("add_author")) {
            try {
                DBConnection.addAuthor(
                        library,
                        new Author(
                                Integer.valueOf(request.getParameter("id")),
                                request.getParameter("firstname"),
                                request.getParameter("lastname")
                        ));
                response.sendRedirect("confirm.jsp");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

