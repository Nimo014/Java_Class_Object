import java.util.*;

@SuppressWarnings("All")    
public class LibraryDemo {

    public static class Book {
        private String title;
        private String author;
        private int count;

        Book(String title, String author, int count) {
            this.title = title;
            this.author = author;
            this.count = count;
        }

        String getTitle() {
            return title;
        }

        String getAuthor() {
            return author;
        }

        int getQuantity() {
            return count;
        }

        void borrowCopy() {
            if (count > 0) {
                count--;
            }
            else {
                System.out.println("No book available.");
            }
        }

        void returnCopy() {
            count++;
        }

    }

    // class composition ; library, list of book object
    public static class Library {
        private String LibraryName;
        private List<Book> books;

        Library(String LibraryName) {
            this.LibraryName = LibraryName;
            books = new ArrayList<>();
        }

        void addBook(Book book) {
            books.add(book);
        }

        void returnBook(Book book) {
            book.returnCopy();
            addBook(book);
        }

        Book findBook(String title) {
            for (Book book : books) {
                if (book.getTitle().equalsIgnoreCase(title) && book.getQuantity() > 0) {
                    return book;
                }
            }
            return null;
        }

        void viewLibrary() {
            if (books.isEmpty()) {
                System.out.println("\n The library is empty.");
            } else {
                System.out.println("\n Books in the " + LibraryName + ":");
                int i = 1;
                for (Book book : books) {
                    System.out.println("(" + i + ")" + " Title: " + book.getTitle() + ", Author: " + book.getAuthor() + ", Available Copies: " + book.getQuantity());
                    i++;
                }
            }
        }
        
    }

    // class composition ; book, date object
    static class BorrowedBook {
        Book book;
        Calendar borrowedDate;

        BorrowedBook(Book book, Calendar borrowedDate) {
            this.book = book;
            this.borrowedDate = borrowedDate;
        }

        Double penalty() {
            Calendar today = Calendar.getInstance();
            long diff = today.getTimeInMillis() - borrowedDate.getTimeInMillis();
            long days = diff / (1000 * 60 * 60 * 24);

            // add penalty after 7 days
            if (days > 7) {
                return (days - 7) * 100.0; 
            }
            return 0.0;
        }

        String getBorrowedDate() {
            return String.format("%1$td/%1$tm/%1$tY", borrowedDate);
        }
    }

    // class composition ; user, List of borrowed book object
    public static class User{
        public String name;
        public List<BorrowedBook> borrowedBooks = new ArrayList<>();


        User(String name) {
            this.name = name;
        }

        String getName() {
            return name;
        }

        // borrow a book
        void borrowBook(Calendar date, Book book) {
            // book already borrowed
            for (BorrowedBook borrowedBook : borrowedBooks) {
                if (borrowedBook.book.getTitle().equalsIgnoreCase(book.getTitle())) {
                    System.out.println("You have already borrowed this book: " + book.getTitle() + ", Penalty : " + borrowedBook.penalty() + " Rs.");
                    return;
                }
            }
            
            if (book.getQuantity() > 0) {
                book.borrowCopy();
                borrowedBooks.add(new BorrowedBook(book, date));
                System.out.println("--> You have borrowed: " + book.getTitle());

            } else {
                System.out.println("Book is not available for borrowing.");
            }       
        }

        // view borrowed books
        void viewBorrowedBooks() {
            if (borrowedBooks.isEmpty()) {
                System.out.println("No books borrowed.");
            } else {
                System.out.println("\n--> Borrowed Books:");
                int i = 1;
                for (BorrowedBook borrowedBook : borrowedBooks) {
                    System.out.println("(" + i + ")" + " Title: " + borrowedBook.book.getTitle() + ", Borrowed Date: " + borrowedBook.getBorrowedDate() + ", Penalty: " + borrowedBook.penalty() + " Rs.");
                    i++;
                }
            }
        }

    }
    
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
                                System.out.println("Book not found in " + library.LibraryName);
                            }
                        }
                        user.viewBorrowedBooks();
                        break;
                    case 3:
                        System.out.print("Enter the book title for return: ");
                        String returnTitle = scanner.next();
                        boolean bookReturned = false;
                        for (BorrowedBook borrowedBook : user.borrowedBooks) {
                            if (borrowedBook.book.getTitle().equalsIgnoreCase(returnTitle)) {
                                borrowedBook.book.returnCopy();
                                for (Library library : libraries) {
                                    library.returnBook(borrowedBook.book);
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
