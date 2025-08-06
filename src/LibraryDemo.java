import java.util.*;
import com.lib.v1.*;


@SuppressWarnings("All")    
public class LibraryDemo {

    // class composition ; book, author, count

    // class composition ; library, list of book object
    
    // class composition ; book, date object
   
    // class composition ; user, List of borrowed book object
    
    
    public static void main(String[] args) {

        // List of libraries
        List<Library> libraries = new ArrayList<>();

        // Library objects
        Library library1 = new Library("Library 1");
        Library library2 = new Library("Library 2");
        
        // Book objects 
        Book book1 = new Book("title1", "author1", 2);
        Book book2 = new Book("title2", "author2", 1);
        Book book3 = new Book("title3", "author3", 3);
        Book book4 = new Book("title4", "author4", 2);
        Book book5 = new Book("title5", "author5", 2);
        Book book6 = new Book("title6", "author6", 2);

        // Class composition
        library1.addBook(book1);
        library1.addBook(book2);
        library1.addBook(book3);
        library2.addBook(book4);
        library2.addBook(book5);
        library2.addBook(book6);


        libraries.add(library1);
        libraries.add(library2);

        HashMap<String, User> users = new HashMap<>();
        User user1 = new User("Nirav");
        User user2 = new User("Nirav2");

        users.put(user1.getName(), user1);
        users.put(user2.getName(), user2);


        String option = "yes";
        Scanner scanner = new Scanner(System.in);
        while (option.equalsIgnoreCase("yes")) {

            // Select User
            System.out.println("\n\nSelect a user name:");
            for (String userName : users.keySet()) {
                System.out.println(userName);
            }

            String userName = scanner.next();
            User user = users.get(userName);
            if (user == null) {
                System.out.println("User not found!");
                continue;
            }   // Invalid then skip loop
            System.out.println("\nWelcome," + user.getName());

            option = "yes";
            while (option.equalsIgnoreCase("yes")) {
                System.out.println("\n 1. View Libraries");
                System.out.println(" 2. Borrow Book");
                System.out.println(" 3. Return Books");
                System.out.println(" 4. Logout"); 
                System.out.print("Select an option: ");
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:

                        System.out.println("\n---> Libraries: ");
                        for (Library library : libraries) {
                            library.viewLibrary();
                        }

                        System.out.println("\n---> User borrowed books: ");
                        user.viewBorrowedBooks();
                        System.out.println("\n");
                        break;
                    case 2:
                        System.out.print("Enter the book title to borrow: ");
                        String bookTitle = scanner.next();
                        for (Library library : libraries) {
                            Book book = library.findBook(bookTitle);
                            if (book != null) {
                                Calendar date = Calendar.getInstance();
                               // date.add(Calendar.DATE, -8);
                                user.borrowBook(date, book);
                                break;
                            } else {
                                System.out.println("Book not found in " + library.getLibraryName());
                            }
                        }
                        user.viewBorrowedBooks();
                        break;
                    case 3:
                        System.out.print("Enter the book title for return: ");
                        String returnTitle = scanner.next();
                        boolean bookReturned = false;
                        for (BorrowedBook borrowedBook : user.borrowedBooks) {
                            if (borrowedBook.getBook().getTitle().equalsIgnoreCase(returnTitle)) {
                                borrowedBook.getBook().returnCopy();
                                for (Library library : libraries) {
                                    library.returnBook(borrowedBook.getBook());
                                }
                                user.borrowedBooks.remove(borrowedBook);
                                bookReturned = true;
                                System.out.println("Book returned successfully: " + returnTitle);
                            }
                        }
                        if (!bookReturned) {
                            System.out.println("You have not borrowed this book: " + returnTitle);
                        }
                        user.viewBorrowedBooks();
                        System.out.println("\n");
                        System.out.println("--> Available books after return:");
                        for (Library library : libraries) {
                            library.viewLibrary();
                        }
                        System.out.println("\n");
                       
                        break;
                    case 4:
                        option = "no";
                        break;
                    default:
                        System.out.println("Invalid option!!!");
                }
            }
            System.out.println("Do you want to select another user or exit?(yes/no)");
            option = scanner.next();
        }   
    }

}
