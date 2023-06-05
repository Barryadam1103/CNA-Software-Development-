package com.example.assignment2servlets;
import java.sql.*;
public class BooksDBConnectionTest {

    static final String DB_URL = "jdbc:mariadb://localhost:3308/books";
    //TODO Customize to your setup
    static final String USER = "root";
    static final String PASS = "root";

    public static void main(String[] args) {
        // Open a connection
        try(
                Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
                Statement statement = connection.createStatement();
        ){
            String sqlQuery = "SELECT * from titles";          //The Query

            //Execute the query and get the result set
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            System.out.print("ISBN \t\t\t Title");
            while (resultSet.next()) {
                System.out.printf("\n%s \t\t\t %s ",
                        resultSet.getString("isbn"), resultSet.getString("title"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }






























}
