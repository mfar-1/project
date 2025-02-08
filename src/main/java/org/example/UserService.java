package org.example;

import java.util.Optional;
import java.util.Scanner;

public class UserService {
    private final Scanner scanner = DB.scanner;

    public void showAllUsers() {
        if (DB.users.size() == 1) {
            System.out.println("No users available except Admin.");
            return;
        }

        DB.users.stream()
                .filter(user -> !user.getRole().equals(Role.ADMIN))
                .forEach(System.out::println);
    }

    public void showUser(String id) {
        User user = DB.users.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (user != null) {
            if (user.getRole().equals(Role.ADMIN)) {
                System.out.println("This is an Admin user.");
            }
            System.out.println(user);
        } else {
            System.out.println("User not found.");
        }
    }

    public static Book findBookInSection(Section section, String title) {
        return section.getBooks().stream()
                .filter(book -> book.getTitle().equalsIgnoreCase(title))
                .findFirst()
                .orElse(null);
    }

    public static Section findSectionByName(String name) {
        return DB.sections.stream()
                .filter(section -> section.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    public void borrowBook() {
        if (DB.currentUser == null) {
            System.out.println("You must be logged in to borrow a book.");
            return;
        }

        System.out.print("Enter book name: ");
        String bookName = DB.scanner.nextLine();

        Optional<Book> book = DB.sections.stream()
                .flatMap(section -> section.getBooks().stream())
                .filter(b -> b.getName().equalsIgnoreCase(bookName) && b.getState() == State.AVAILABLE)
                .findFirst();

        if (book.isPresent()) {
            DB.currentUser.borrowBook(book.get());
            System.out.println("Book borrowed successfully!");
        } else {
            System.out.println("Book not available.");
        }
    }

    public static void returnBook() {
        if (DB.currentUser == null) {
            System.out.println("You must be logged in to return a book.");
            return;
        }

        System.out.print("Enter book name: ");
        String bookName = DB.scanner.nextLine();

        Optional<Book> book = DB.currentUser.getBorrowedBooks().stream()
                .filter(b -> b.getName().equalsIgnoreCase(bookName))
                .findFirst();

        if (book.isPresent()) {
            DB.currentUser.returnBook(book.get()); // Foydalanuvchining returnBook() metodini chaqiramiz
            System.out.println("Book returned successfully!");
        } else {
            System.out.println("You did not borrow this book.");
        }
    }

}