package com.lib.v1;

public class Book {
    private String title;
    private String author;
    private int count;

    public Book(String title, String author, int count) {
        this.title = title;
        this.author = author;
        this.count = count;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getQuantity() {
        return count;
    }

    public void borrowCopy() {
        if (count > 0) {
            count--;
        }
        else {
            System.out.println("No book available.");
        }
    }

    public void returnCopy() {
        count++;
    }
}