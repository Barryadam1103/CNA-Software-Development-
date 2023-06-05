package com.example.assignment2servlets;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;


/**
 * @author Adam Barry
 * The Author Class holds all info for the Author.
 */
public class Author {

    private int authorID;
    private String firstName;
    private String lastName;
    private List<Book> bookList;

    /**
     * Author Constructor
     * @param authorID
     * @param firstName
     * @param lastName
     */
    public Author(int authorID, String firstName, String lastName) {
        this.authorID = authorID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bookList = new LinkedList<>();
    }


    /**
     * Getter authorID
     * @return int
     */
    public int getAuthorID() {
        return authorID;
    }

    /**
     * Setter authorID
     * @param authorID
     */
    public void setAuthorID(int authorID) {
        this.authorID = authorID;
    }

    /**
     * Getter firstname
     * @return String
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Setter FirstName
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Getter LastName
     * @return String
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Setter LastName
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Getter BookList
     * @return List
     */
    public List<Book> getBookList(){
        return bookList;
    }

    /**
     * The addBook method used to add a book to the book list that an author has written.
     * @param book
     */
    public void addBook(Book book){
        this.bookList.add(book);
    }

    /**
     * The printAuthors method helps to display and format the authors and the books that belong to the author.
     * @param printStream
     */
    public void printAuthors(PrintStream printStream) {
        printStream.printf("\n\nAuthor ID: %d \t\t First Name: %-10s \t\t Last Name: %-10s", this.getAuthorID(),this.getFirstName(),this.getLastName());
        bookList.forEach(book -> System.out.printf("\nISBN: %-15s Title: %-60s Edition: %-5s CopyRight: %-7s", book.getIsbn(), book.getTitle(), book.getEditionNumber(),book.getCopyright()));
    }


}
