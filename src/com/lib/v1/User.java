package com.lib.v1;

import java.util.*;

public class User {

    public String name;
    public List<BorrowedBook> borrowedBooks = new ArrayList<>();


    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // borrow a book
    public void borrowBook(Calendar date, Book book) {
        // book already borrowed
        for (BorrowedBook borrowedBook : borrowedBooks) {
            if (borrowedBook.getBook().getTitle().equalsIgnoreCase(book.getTitle())) {
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
    public void viewBorrowedBooks() {
        if (borrowedBooks.isEmpty()) {
            System.out.println("No books borrowed.");
        } else {
            System.out.println("\n--> Borrowed Books:");
            int i = 1;
            for (BorrowedBook borrowedBook : borrowedBooks) {
                System.out.println("(" + i + ")" + " Title: " + borrowedBook.getBook().getTitle() + ", Borrowed Date: " + borrowedBook.getBorrowedDate() + ", Penalty: " + borrowedBook.penalty() + " Rs.");
                i++;
            }
        }
    }

    
}
