package A1;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * The DBConfiguration class holds all the database information for the tables and connections etc...
 * @author Adam Barry
 */
public class DBConfiguration {
    //Database info.
    protected static final String MARIA_DB_DRIVER_NAME = "org.mariadb.jdbc.Driver";
    protected static final String DB_URL = "jdbc:mariadb://localhost:3308";
    protected static final String DB_USER = "root";
    protected static final String DB_PASSWORD = "root";

    //Books table database info
    protected static final String DB_BOOKS = "/books";
    protected static final String DB_BOOKS_TITLES_TABLE_NAME = "titles";
    protected static final String DB_BOOKS_TITLES_ISBN = "isbn";
    protected static final String DB_BOOKS_TITLES_TITLE = "title";
    protected static final String DB_BOOKS_TITLES_EDITION_NUMBER = "editionNumber";
    protected static final String DB_BOOKS_TITLES_COPYRIGHT = "copyright";


    //Authors table database info.

    protected static final  String DB_AUTHOR_TABLE_NAME = "authors";
    protected static final  String DB_AUTHOR_ID = "authorID";
    protected static final  String DB_AUTHOR_FIRST_NAME = "firstName";
    protected static final  String DB_AUTHOR_LAST_NAME = "lastName";





    //authorisbn table database information.

    protected static final String DB_BOOKS_AUTHOR_BOOKS_BRIDGE_TABLE_NAME = "authorisbn";


    /**
     * The doClassForNameRegistration methods registers.
     */
    public static void doClassForNameRegistration(){
        try {
            Class.forName(DBConfiguration.MARIA_DB_DRIVER_NAME);
            System.out.println("DB Driver Registration using Class.forName() worked! (Option 1)");
        }
        catch(ClassNotFoundException ex) {
            System.err.println("ClassNotFoundException: unable to load MariaDB driver class!");
            System.err.println("Driver name: " + DBConfiguration.MARIA_DB_DRIVER_NAME);
            System.exit(1);
        }
    }

    /**
     * doRegisterDriverMethodRegistration registers the drivers
     */
    public static void doRegisterDriverMethodRegistration(){
        try {
            Driver myDriver = new org.mariadb.jdbc.Driver();
            DriverManager.registerDriver( myDriver );
            System.out.println("DB Driver Registration using registerDriver() worked! (Option 2)");
        }
        catch (SQLException e) {
            System.err.println("SQL Exception: trying to register MariaDB Driver");
            e.printStackTrace();
        }
    }

    /**
     * The getDBConnection methods gets the connection from the database.
     * @return
     */
    public static Connection getDBConnection(){
        Connection connection = null;
        try{
            connection =  DriverManager.getConnection(DBConfiguration.DB_URL + DBConfiguration.DB_BOOKS, DBConfiguration.DB_USER, DBConfiguration.DB_PASSWORD);
        }
        catch (SQLException sqlException){
            sqlException.printStackTrace();;
        }
        return connection;
    }
}
