package com.lib.v1;

import java.util.*;
import com.lib.v1.*;

public class Library {
    public String LibraryName;
    private List<Book> books;

    public Library(String LibraryName) {
        this.LibraryName = LibraryName;
        books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void returnBook(Book book) {
        book.returnCopy();
        addBook(book);
    }

    public Book findBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title) && book.getQuantity() > 0) {
                return book;
            }
        }
        return null;
    }

    public void viewLibrary() {
        if (books.isEmpty()) {
            System.out.println("\n The library is empty.");
        } else {
            System.out.println("\n Books in the " + LibraryName + ":");
            int i = 1;
            for (Book book : books) {
                System.out.println("(" + i + ")" + " Title: " + book.getTitle() + 
                    ", Author: " + book.getAuthor() + 
                    ", Available Copies: " + book.getQuantity());
                i++;
            }
        }
    }

    public String getLibraryName() {
        return LibraryName;
    }
}