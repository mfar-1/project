package org.example.entity;

import java.time.LocalDateTime;
import java.util.Date;

public class Book {
    private String title;
    private  String id;
    private BookState bookState;
    private Date date;
    private int totalAmount;
    private int borrowedAmount; //olingan kitoblar
    private String borrowedUserId;
    private LocalDateTime borrowTime;
    private LocalDateTime returnedTime;
    private String lastBorrowedUserId;

    public Book(String title, String id) {
        this.title = title;
        this.id = id;

    }

    public Book(String title, int totalAmount, String id, BookState bookState) {
        this.title = title;
        this.totalAmount=totalAmount;
        this.id=id;
        this.bookState=bookState;
    }

    public Book(String title, String id, BookState bookState, Date date, int totalAmount) {
        this.title = title;
        this.id = id;
        this.bookState = bookState;
        this.date = date;
        this.totalAmount = totalAmount;
    }

    public Book(String title, String id, BookState bookState, Date date, int totalAmount, int borrowedAmount) {
        this.title = title;
        this.id = id;
        this.bookState = bookState;
        this.date = date;
        this.totalAmount = totalAmount;
        this.borrowedAmount = borrowedAmount;
    }


    public String getLastBorrowedUserId() {
        return lastBorrowedUserId;
    }

    public void setLastBorrowedUserId(String lastBorrowedUserId) {
        this.lastBorrowedUserId = lastBorrowedUserId;
    }

    public int getBorrowedAmount() {
        return borrowedAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BookState getBookState() {
        return bookState;
    }

    public void setBookState(BookState bookState) {
        this.bookState = bookState;
    }

    public LocalDateTime getReturnedTime() {
        return returnedTime;
    }

    public void setReturnedTime(LocalDateTime returnedTime) {
        this.returnedTime = returnedTime;
    }

    public LocalDateTime getBorrowTime() {
        return borrowTime;
    }

    public void setBorrowTime(LocalDateTime borrowTime) {
        this.borrowTime = borrowTime;
    }

    public String getBorrowedUserId() {
        return borrowedUserId;
    }

    public void setBorrowedUserId(String borrowedUserId) {
        this.borrowedUserId = borrowedUserId;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
