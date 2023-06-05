package A1;
import java.util.Scanner;


/**
 * The Book Application class is a simple menu that works with the books' database.
 * The user will be prompt to enter a choice between 1-5 to perform a task in the Books database.
 * @author Adam Barry
 */
public class BookApplication {


    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        //Make a Library object and call the loadLibrary method.
        Library library = BookDatabaseManger.loadLibrary();
        char c;

        do{
                System.out.println("\n\n---------------------------------------------------------------");
                System.out.println("CP3566 Applied Java III CNA Book Store");
                System.out.println("---------------------------------------------------------------");
                System.out.println("Select one of the following choices below:");
                System.out.println("---------------------------------------------------------------");
                System.out.println("1) Print all the books and their authors");
                System.out.println("2) Print all the authors and their books");
                System.out.println("3) Add a book to the database for an existing author");
                System.out.println("4) Add a new author");
                System.out.println("5) Quit");
                System.out.print("Enter a choice (1-5): ");
                c = Character.toUpperCase(input.next().charAt(0));

                if(c=='1'){
                    for (Book book : library.getBookList()) {
                        book.printBooks(System.out);
                    }
                }
                else if (c == '2'){
                    for (Author author : library.getAuthorList()) {
                        author.printAuthors(System.out);
                    }
                } else if (c == '3') {
                    //Get authorID from user.
                    System.out.println("Enter AuthorID");
                    int authorid = Integer.parseInt(new Scanner(System.in).next());

                    //Get ISBN from user.
                    Scanner inputISBN = new Scanner(System.in);
                    System.out.println("Enter Book ISBN:  ");
                    String isbn = inputISBN.next();

                    // Get Title from user.
                    Scanner inputTitle = new Scanner(System.in);
                    System.out.println("Enter Book Title:  ");
                    String title = inputTitle.nextLine();

                    // Get book Edition from user.
                    Scanner inputEdition = new Scanner(System.in);
                    System.out.println("Enter Book Edition:  ");
                    int edition = inputEdition.nextInt();

                    // Get CopyRight from user.
                    Scanner inputCopyRight = new Scanner(System.in);
                    System.out.println("Enter Book CopyRight:  ");
                    String copyRight = inputCopyRight.next();

                    BookDatabaseManger.addBookToAuthor(DBConfiguration.getDBConnection(), library, isbn, title, edition, copyRight, authorid );
                    
                } else if (c =='4') {
                    //Get first name and last name from the user.
                    Scanner inputFirstName = new Scanner(System.in);
                    System.out.println("Enter First Name: ");
                    String firstName = inputFirstName.next();
                    Scanner inputLastName = new Scanner(System.in);
                    System.out.println("Enter Last Name:   ");
                    String lastName = inputLastName.next();
                    BookDatabaseManger.addAuthor(DBConfiguration.getDBConnection(), library, firstName, lastName);

                } else if (c =='5') {
                    System.out.println("Thank You for using the CNA Book Store....... See ya again next time :)");
                    System.exit(1);
                    
                }

        }while (c!='5');
        System.out.println("Invalid choice (Must be 1-5!)");
    }
}
