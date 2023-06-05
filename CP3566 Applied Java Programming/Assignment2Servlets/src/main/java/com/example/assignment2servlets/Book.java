package com.example.assignment2servlets;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Adam Barry
 * The book class holds all the book class info.
 */
public class Book {

    private String isbn;
    private String title;
    private int editionNumber;
    private String copyright;
    private List<Author> authorList;

    /**
     * Book Constructor
     * @param isbn
     * @param title
     * @param editionNumber
     * @param copyright
     */
    public Book(String isbn, String title, int editionNumber, String copyright) {
        this.isbn = isbn;
        this.title = title;
        this.editionNumber = editionNumber;
        this.copyright = copyright;
        authorList = new LinkedList<>();
    }


    /**
     * Getter Isbn
     * @return String
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Setter Isbn
     * @param isbn
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * Getter Title
     * @return String
     */
    public String getTitle() {
        return title;
    }

    /**
     * Setter Title
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Getter EditionNumber
     * @return int
     */
    public int getEditionNumber() {
        return editionNumber;
    }

    /**
     * Setter EditionNumber
     * @param editionNumber
     */
    public void setEditionNumber(int editionNumber) {
        this.editionNumber = editionNumber;
    }

    /**
     * Getter CopyRight
     * @return String
     */
    public String getCopyright() {
        return copyright;
    }

    /**
     * Setter copyright
     * @param copyright
     */
    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    /**
     * Getter AuthorList
     * @return List
     */
    public List<Author> getAuthorList() {
        return authorList;
    }

    /**
     * The addAuthor method that adds an author to the author list from their book,
     * @param author
     */
    public void addAuthor(Author author){
        this.authorList.add(author);
    }

    /**
     * The printBooks methods helps to display and format the books and the authors that belong to the books.
     * @param printStream
     */
    public void printBooks(PrintStream printStream) {
        printStream.printf("\nISBN: %s \t\t Title: %-70s \t Edition #: %d \t\t Copyright: %s", this.getIsbn(), this.getTitle(), this.getEditionNumber(), this.getCopyright());
        authorList.forEach(author -> System.out.printf("\n\nFirst Name: %s \t\t Last Name: %s", author.getFirstName(), author.getLastName()));

    }



}
