package org.example.entity;

import java.util.List;

public class User {
    private  String name;
    private String surname;
    private int age;
    private String id;
    private String password;
    private String login;
    private List<Book> borrowedBooks;



    public User(String name, String surname, int age, String id, String password) {
        setName(name);
        setSurname(surname);
        setAge(age);
        this.id = id;
        this.password=password;
    }

    public User(String name, String surname, int age, String id, String password, String login) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.id = id;
        this.password = password;
        this.login = login;
    }

    public User(String login, String password) {
        this.login=login;
        this.password = password;

    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name.isBlank() || name.length()<3){
            throw new IllegalArgumentException("\033[34mName should include more than 3 characters...please try again!!!\033[34m");
        }
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(List<Book> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }

    public void setSurname(String surname) {
        if(surname.isBlank() || surname.length()<3){
            throw new IllegalArgumentException("\033[34mSurname should include more than 3 characters...please try again!!!\033[34m");
        }
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if(age<=10 || age>=70){
            throw new IllegalArgumentException("\033[34mSorry, your age is not proper to get a membership...\033[34m");
        }
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void borrowBooks(Book book) {
        if(borrowedBooks.size()<5){
            borrowedBooks.add(book);
        }else {
            System.out.println("you cannot borrow more than 5 books! Pls, return some books first...");
        }
    }
    public void returnBook(Book book){
        borrowedBooks.remove(book);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", id='" + id + '\'' +
                ", password='" + password + '\'' +
                ", borrowedBooks=" + borrowedBooks +
                '}';
    }
}
