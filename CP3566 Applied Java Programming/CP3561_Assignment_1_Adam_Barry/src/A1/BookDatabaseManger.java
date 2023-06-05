package A1;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * The BookDatabaseManager class handles all database connections and queries for the books database.
 * @author Adam Barry
 */

public class BookDatabaseManger {

    /**
     * loadLibrary method that loads the lists from the Library.Java class.
     * @return
     */
    public static Library loadLibrary(){
        //Make a library object
        Library library = new Library();
        // Get a connection
        Connection connection = DBConfiguration.getDBConnection();

        //Load the author and book lists
        library.setBookList(loadBookList(connection));
        library.setAuthorList(loadAuthorList(connection));
        booksRelationShips(connection, library.getBookList(), library.getAuthorList());

        return library;
    }


    /**
     * The loadBookList methods loads all books from the books database
     * @param connection
     * @return bookLinkedList
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
                    resultSet.getString(DBConfiguration.DB_BOOKS_TITLES_ISBN),
                    resultSet.getString(DBConfiguration.DB_BOOKS_TITLES_TITLE),
                    resultSet.getInt(DBConfiguration.DB_BOOKS_TITLES_EDITION_NUMBER),
                    resultSet.getString(DBConfiguration.DB_BOOKS_TITLES_COPYRIGHT)
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
                    resultSet.getInt(DBConfiguration.DB_AUTHOR_ID),
                    resultSet.getString(DBConfiguration.DB_AUTHOR_FIRST_NAME),
                    resultSet.getString(DBConfiguration.DB_AUTHOR_LAST_NAME)
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
                int authorID = resultSet.getInt(DBConfiguration.DB_AUTHOR_ID);
                String isbn = resultSet.getString(DBConfiguration.DB_BOOKS_TITLES_ISBN);

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


    /**
     * The addAuthor methods adds an author to the database.
     * @param connection
     * @param library
     * @param firstname
     * @param lastname
     */
    public static void addAuthor(Connection connection, Library library, String firstname, String lastname) {
        try{
            //Get the index of the next author.
            library.getAuthorList().forEach(author -> System.out.printf("\n%d %s, %s", author.getAuthorID(), author.getLastName(), author.getFirstName()));
            int authid = library.getAuthorList().get(library.getAuthorList().size()-1).getAuthorID() + 1;


            //Prepared Statement to add the author to the database.
            String sql = "INSERT INTO authors (firstName, lastName) values (?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, firstname);
            preparedStatement.setString(2, lastname);
            preparedStatement.executeQuery();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * The addBookToAuthor method adds a book to an author that already exists in the database.
     * @param connection
     * @param library
     * @param isbn
     * @param title
     * @param editionNumber
     * @param copyright
     * @param authorid
     */

    public static void addBookToAuthor(Connection connection, Library library, String isbn, String title, Integer editionNumber, String copyright, int authorid) {
        try{
            //Create a new book
            Book newbook = new Book(isbn, title, editionNumber, copyright);

            for (int i = 0; i < library.getAuthorList().size(); i++) {
                System.out.printf("\n%d: %s, %s",
                        i, library.getAuthorList().get(i).getLastName(), library.getAuthorList().get(i).getFirstName());
            }
            System.out.print("\n");

            //Add the newbook to the author this is where below also the relationship is made.
            newbook.addAuthor(library.getAuthorList().get(authorid-1));
            library.getAuthorList().get(authorid-1).addBook(newbook);
            library.getBookList().add(newbook);
            // Prepared statement to insert the following values into the titles database which stores the books.
            String sql = "INSERT INTO titles (isbn, title, editionNumber, copyright) values (?,?,?,?)";
            String sqlisbn = "Insert INTO authorisbn(authorId, isbn) values (?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            PreparedStatement preparedStatement1 = connection.prepareStatement(sqlisbn);
            preparedStatement.setString(1, isbn);
            preparedStatement.setString(2, title);
            preparedStatement.setInt(3, editionNumber);
            preparedStatement.setString(4, copyright);
            preparedStatement.executeQuery();


        //Catch runtime exception.
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }



}
