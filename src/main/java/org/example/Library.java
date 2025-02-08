package org.example;

import java.util.ArrayList;
import java.util.List;

public class Library {
    public static final List<Book> books = new ArrayList<>();

    static {
        books.add(new Book("The Great Gatsby", "F. Scott Fitzgerald", State.AVAILABLE));
        books.add(new Book("1984", "George Orwell", State.AVAILABLE));
        books.add(new Book("To Kill a Mockingbird", "Harper Lee", State.AVAILABLE));
        books.add(new Book("Moby Dick", "Herman Melville", State.AVAILABLE));
        books.add(new Book("The Catcher in the Rye", "J.D. Salinger", State.AVAILABLE));
        books.add(new Book("Pride and Prejudice", "Jane Austen", State.AVAILABLE));
        books.add(new Book("War and Peace", "Leo Tolstoy", State.AVAILABLE));
        books.add(new Book("Jane Eyre", "Charlotte Bronte", State.AVAILABLE));
        books.add(new Book("Crime and Punishment", "Fyodor Dostoevsky", State.AVAILABLE));
        books.add(new Book("The Hobbit", "J.R.R. Tolkien", State.AVAILABLE));
    }

    public static void showBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available.");
        } else {
            books.forEach(System.out::println);
        }
    }
}
