package org.example;

import java.util.Optional;

import static org.example.DB.*;
import static org.example.UserService.returnBook;

public class AuthService {
    AdminService adminService = new AdminService();
    UserService userService = new UserService();

    public void service() {
        for (int i = 1; i <= 3; i++) {
            System.out.println(i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("waiting for the program to run...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        while (true) {
            System.out.println("""
                      \033[1m\033[35m
                      0 - exit 
                      1 - sign up 
                      2 - sign in \033[35m\033[0m
                     """);
            System.out.println("Choose: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // '\n' ni tozalash

            switch (choice) {
                case 0 -> {
                    System.out.println("\033[36mexiting the program...\033[36m");
                    return;
                }
                case 1 -> signUp();
                case 2 -> signIn();
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    private void signUp() {
        System.out.print("Enter the name: ");
        String name = DB.scanner.nextLine();
        System.out.print("Enter the surname: ");
        String surname = DB.scanner.nextLine();
        System.out.print("Enter the age: ");
        int age = DB.scanner.nextInt();
        DB.scanner.nextLine(); // '\n' ni tozalash
        System.out.print("Enter the password: ");
        String password = DB.scanner.nextLine();

        User newUser = new User(name, surname, age, java.util.UUID.randomUUID().toString(), password);
        DB.users.add(newUser);
        System.out.println("User added successfully...");
    }

    private void signIn() {
        System.out.print("Enter your ID: ");
        String id = DB.scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = DB.scanner.nextLine();

        Optional<User> user = DB.users.stream()
                .filter(u -> u.getId().equals(id) && u.getPassword().equals(password))
                .findFirst();

        if (user.isPresent()) {
            DB.currentUser = user.get();
            System.out.println("Signed in successfully!");
            if (DB.currentUser.getRole() == Role.ADMIN) {
                adminMenu();
            } else {
                userMenu();
            }
        } else {
            System.out.println("Invalid password. Try again.");
        }
    }

    private void adminMenu() {
        while (true) {
            System.out.println("\nAdmin Menu:");
            System.out.println("0 - Log out");
            System.out.println("1 - Show all users");
            System.out.println("2 - Add section");
            System.out.println("3 - Show all sections");
            System.out.print("Enter option: ");
            int option = DB.scanner.nextInt();
            DB.scanner.nextLine(); // '\n' ni tozalash

            switch (option) {
                case 0 -> {
                    System.out.println("Logging out...");
                    DB.currentUser = null;
                    return;
                }
                case 1 -> new UserService().showAllUsers();
                case 2 -> addSection();
                case 3 -> showSections();
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    private void userMenu() {
        while (true) {
            System.out.println("\nUser Menu:");
            System.out.println("0 - Log out");
            System.out.println("1 - Show available books");
            System.out.println("2 - Borrow a book");
            System.out.println("3 - Return a book");
            System.out.print("Enter option: ");
            int option = DB.scanner.nextInt();
            DB.scanner.nextLine(); // '\n' ni tozalash

            switch (option) {
                case 0 -> {
                    System.out.println("Logging out...");
                    DB.currentUser = null;
                    return;
                }
                case 1 -> showAvailableBooks();
                case 2 -> userService.borrowBook();
                case 3 -> returnBook();
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    public static void addSection() {
        System.out.print("Enter section name: ");
        String name = DB.scanner.nextLine();
        Section section = new Section(name);
        DB.sections.add(section);
        System.out.println("Section added successfully...");
    }

    private void showSections() {
        if (DB.sections.isEmpty()) {
            System.out.println("No sections available.");
        } else {
            DB.sections.forEach(System.out::println);
        }
    }

    private void showAvailableBooks() {
        if (DB.sections.isEmpty()) {
            System.out.println("No sections available.");
        } else {
            DB.sections.stream()
                    .flatMap(section -> section.getBooks().stream())
                    .filter(book -> book.getState() == State.AVAILABLE)
                    .forEach(System.out::println);
        }
    }
}
