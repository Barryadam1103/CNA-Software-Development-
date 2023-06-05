package A1;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Adam Barry
 * The Library class holds the bookList and AuthorList for the program.
 */
public class Library {

    //Book List
    private List<Book> bookList;

    //Author List
    private List<Author> authorList;

    /**
     * Library Constructor
     */
    public Library(){
        this.bookList = new LinkedList<>();
        this.authorList = new LinkedList<>();
    }

    /**
     * Getter for bookList
     * @return bookList List
     */
    public List<Book> getBookList() {
        return bookList;
    }

    /**
     * Setter BookList
     * @param bookList
     */
    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    /**
     * Getter authorList
     * @return authorList
     */
    public List<Author> getAuthorList() {
        return authorList;
    }

    /**
     * Setter authorList
     * @param authorList
     */
    public void setAuthorList(List<Author> authorList) {
        this.authorList = authorList;
    }



}
