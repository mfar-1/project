package org.example;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private String surname;
    private int age;
    private String id;
    private String password;
    private Role role;
    private List<Book> borrowedBooks;

    public User(String name, String surname, int age, String id, String password, Role role, List<Book> borrowedBooks) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.id = id;
        this.password = password;
        this.role = role;
        this.borrowedBooks = borrowedBooks != null ? borrowedBooks : new ArrayList<>();
    }

    public User(String name, String surname, int age, String id, String password) {
        this(name, surname, age, id, password, Role.USER, new ArrayList<>());
    }

    public User(String name, String surname, int age, String password) {
    }

    public User(String admin, String root, int i, String s, String root123, Role role) {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void borrowBook(Book book) {
        if (book.getState() == State.AVAILABLE) {
            borrowedBooks.add(book);
            book.setState(State.BORROWED);
            System.out.println("Book borrowed successfully: " + book.getTitle());
        } else {
            System.out.println("Book is not available for borrowing.");
        }
    }

    public void showAllUsers() {
        if (DB.users.size() == 1) {
            System.out.println("No users available except Admin.");
            return;
        }

        DB.users.stream()
                .filter(user -> !user.getRole().equals(Role.ADMIN))
                .forEach(System.out::println);
    }

    public void returnBook(Book book) {
        if (borrowedBooks.contains(book)) {
            borrowedBooks.remove(book);
            book.setState(State.AVAILABLE);
            System.out.println("Book returned successfully: " + book.getName());
        } else {
            System.out.println("This book is not borrowed by you.");
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", id='" + id + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", borrowedBooks=" + (borrowedBooks.isEmpty() ? "No books borrowed" : borrowedBooks) +
                '}';
    }
}