package com.example.assignment2servlets;
import java.sql.*;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.sql.DriverManager;
import java.sql.Connection;

/**
 * @author Adam Barry
 * March 21st 2023
 * The DBConnection class handles all the connection and operations that are performed on the database.
 */
public class DBConnection {


    /**
     * initDatabase methods connects to the database
     * @return
     * @throws SQLException
     */
    public static Connection initDatabase() throws SQLException {
        try {
            Class.forName("org.mariadb.jdbc.Driver").newInstance();
            System.out.println("Option 1: Find the class worked!");
        } catch (ClassNotFoundException ex) {
            System.err.println("Error: unable to load driver class!");
        } catch (IllegalAccessException ex) {
            System.err.println("Error: access problem while loading!");
        } catch (InstantiationException ex) {
            System.err.println("Error: unable to instantiate driver!");
        }
        return DriverManager.getConnection(BookDBInfo.DB_URL, BookDBInfo.DB_USER, BookDBInfo.DB_PASSWORD);


    }


    /**
     * BookDBInfo is an abstract class that holds all database information
     */
    private class BookDBInfo {
        protected static final String MARIA_DB_DRIVER_NAME = "org.mariadb.jdbc.Driver";
        protected static final String DB_URL = "jdbc:mariadb://localhost:3308/books";
        protected static final String DB_USER = "root";
        protected static final String DB_PASSWORD = "root";

        protected static final String DB_BOOKS = "/books";
        protected static final String DB_BOOKS_TITLES_TABLE_NAME = "titles";
        protected static final String DB_BOOKS_TITLES_ISBN = "isbn";
        protected static final String DB_BOOKS_TITLES_TITLE = "title";
        protected static final String DB_BOOKS_TITLES_EDITION_NUMBER = "editionNumber";
        protected static final String DB_BOOKS_TITLES_COPYRIGHT = "copyright";


        //Authors table database info.

        protected static final String DB_AUTHOR_TABLE_NAME = "authors";
        protected static final String DB_AUTHOR_ID = "authorID";
        protected static final String DB_AUTHOR_FIRST_NAME = "firstName";
        protected static final String DB_AUTHOR_LAST_NAME = "lastName";
    }

    /**
     * addAuthor method adds an author to the database.
     * @param authors
     * @throws SQLException
     */
    public static void addAuthor(Library library, Author authors) throws SQLException{
        Connection connection = initDatabase();
        //Sorts the author list and compares the int with authors ID.
        library.getAuthorList().sort(Comparator.comparingInt(Author::getAuthorID));

        String query = "INSERT INTO " + BookDBInfo.DB_AUTHOR_TABLE_NAME + " VALUES (?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setInt(1,library.getAuthorList().get(library.getAuthorList().size()-1).getAuthorID()+1);
        preparedStatement.setString(2, authors.getFirstName());
        preparedStatement.setString(3, authors.getLastName());
        //preparedStatement.executeQuery();
        preparedStatement.executeUpdate();
        connection.close();
    }


    /**
     * addBook method adds a book to the database.
     * @param book
     * @throws SQLException
     */
    public static void addBook(Library library, int id, Book book) throws SQLException {
        try{
            Connection connection = initDatabase();

            book.addAuthor(library.getAuthorList().get(id));
            library.getAuthorList().get(id).addBook(book);
            library.getBookList().add(book);
            // Prepared statement to insert the following values into the titles database which stores the books.
            String sql = "INSERT INTO " + BookDBInfo.DB_BOOKS_TITLES_TABLE_NAME + " VALUES (?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, book.getIsbn());
            preparedStatement.setString(2, book.getTitle());
            preparedStatement.setInt(3, book.getEditionNumber());
            preparedStatement.setString(4, book.getCopyright());
            preparedStatement.executeQuery();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    /**
     * Loads the lists from the library class.
     * @return library
     * @throws SQLException
     */
    public static Library loadLibrary() throws SQLException {
        //Make a library object
        Library library = new Library();
        // Get a connection
        Connection connection = DBConnection.initDatabase();

        //Load the author and book lists
        library.setBookList(loadBookList(connection));
        library.setAuthorList(loadAuthorList(connection));
        booksRelationShips(connection, library.getBookList(), library.getAuthorList());

        return library;
    }


    /**
     * The loadBookList method loads all the books from the database.
     * @param connection
     * @return
     */
    public static List<Book> loadBookList(Connection connection){
        LinkedList<Book> bookLinkedList = new LinkedList<>();
        try{
            Statement statement = connection.createStatement();
            //SQL statement to get the titles data
            String sql = "SELECT * FROM titles;";
            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next()){
                bookLinkedList.add(new Book(
                        resultSet.getString(DBConnection.BookDBInfo.DB_BOOKS_TITLES_ISBN),
                        resultSet.getString(DBConnection.BookDBInfo.DB_BOOKS_TITLES_TITLE),
                        resultSet.getInt(DBConnection.BookDBInfo.DB_BOOKS_TITLES_EDITION_NUMBER),
                        resultSet.getString(DBConnection.BookDBInfo.DB_BOOKS_TITLES_COPYRIGHT)
                ));
            }
            statement.close();

        }
        catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        return bookLinkedList;

    }

    /**
     * The loadAuthorList method loads all the authors from the books database
     * @param connection
     * @return authourLinkedList
     */
    public static List<Author> loadAuthorList(Connection connection){
        LinkedList<Author> authorLinkedList = new LinkedList<>();
        try{
            Statement statement = connection.createStatement();
            //SQL statement to get the authors data
            String sql = "SELECT * FROM authors;";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()){
                authorLinkedList.add(new Author(
                        resultSet.getInt(DBConnection.BookDBInfo.DB_AUTHOR_ID),
                        resultSet.getString(DBConnection.BookDBInfo.DB_AUTHOR_FIRST_NAME),
                        resultSet.getString(DBConnection.BookDBInfo.DB_AUTHOR_LAST_NAME)
                ));
            }
            statement.close();
        }
        catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        return authorLinkedList;
    }


    /**
     * The booksRelationShips method connects the books and authors together to perform tasks the books' database.
     * @param connection
     * @param bookList
     * @param authorList
     */
    private static void booksRelationShips(Connection connection, List<Book> bookList, List<Author> authorList){
        try {
            Statement statement = connection.createStatement();
            //sql statement to get the data from the authorisbn table
            String sql = "SELECT * FROM authorisbn;";

            //Executing the sql query.
            ResultSet resultSet = statement.executeQuery(sql);

            // A while loop which does and grabs the authorID & books isbn.
            while (resultSet.next()){
                int authorID = resultSet.getInt(DBConnection.BookDBInfo.DB_AUTHOR_ID);
                String isbn = resultSet.getString(DBConnection.BookDBInfo.DB_BOOKS_TITLES_ISBN);

                // Use the filter and stream methods to check and get the authorId and bookisbn to create the relationship between the two tables.
                Author author = authorList.stream().filter(a -> a.getAuthorID() == authorID).findAny().get();
                Book book = bookList.stream().filter(b -> b.getIsbn().equals(isbn)).findAny().get();

                //Add a book to an author and an author to a book.
                author.addBook(book);
                book.addAuthor(author);
            }
            statement.close();
        }
        catch (SQLException sqlException){
            sqlException.printStackTrace();
        }

    }



}
