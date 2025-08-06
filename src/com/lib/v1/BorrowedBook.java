package com.lib.v1;

import java.util.Calendar;

public class BorrowedBook {
    private Book book;
    private Calendar borrowedDate;

    public BorrowedBook(Book book, Calendar borrowedDate) {
        this.book = book;
        this.borrowedDate = borrowedDate;
    }

    public Double penalty() {
        Calendar today = Calendar.getInstance();
        long diff = today.getTimeInMillis() - borrowedDate.getTimeInMillis();
        long days = diff / (1000 * 60 * 60 * 24);

        // add penalty after 7 days
        if (days > 7) {
            return (days - 7) * 100.0; 
        }
        return 0.0;
    }

    public String getBorrowedDate() {
        return String.format("%1$td/%1$tm/%1$tY", borrowedDate);
    }

    public Book getBook() {
        return book;
    }
}