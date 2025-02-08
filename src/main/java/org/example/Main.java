package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final List<Section> sections = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("""
                    1. Show sections
                    2. Add new section
                    3. Add book to section
                    4. Show books in section
                    5. Borrow book
                    6. Return book
                    0. Exit
                    """);
            System.out.print("Choose: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> showSections();
                case 2 -> addSection(scanner);
                case 3 -> addBookToSection(scanner);
                case 4 -> showBooksInSection(scanner);
                case 5 -> borrowBook(scanner);
                case 6 -> returnBook(scanner);
                case 0 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }


    private static void showSections() {
        if (sections.isEmpty()) {
            System.out.println("No sections available.");
        } else {
            sections.forEach(System.out::println);
        }
    }

    private static void addSection(Scanner scanner) {
        System.out.println("Enter section name: ");
        String name = scanner.nextLine();
        sections.add(new Section(name));
        System.out.println("Section added.\n");
    }

    private static void addBookToSection(Scanner scanner) {
        showSections();
        System.out.println("Enter section name: ");
        String sectionName = scanner.nextLine();

        Section section = findSectionByName(sectionName);
        if (section != null) {
            System.out.println("Enter book title: ");
            String title = scanner.nextLine();
            section.addBook(new Book(title));
            System.out.println("Book added.\n");
        } else {
            System.out.println("Section not found.");
        }
    }

    private static void showBooksInSection(Scanner scanner) {
        showSections();
        System.out.println("Enter section name: ");
        String sectionName = scanner.nextLine();

        Section section = findSectionByName(sectionName);
        if (section!= null) {
            if (section.getBooks().isEmpty()) {
                System.out.println("No books in this section.");
            } else {
                section.getBooks().forEach(System.out::println);
            }
        } else {
            System.out.println("Section not found.");
        }
    }

    private static void borrowBook(Scanner scanner) {
        showSections();
        System.out.print("Enter section name: ");
        String sectionName = scanner.nextLine();

        Section section = findSectionByName(sectionName);
        if (section != null) {
            System.out.print("Enter book title: ");
            String title = scanner.nextLine();

            Book book = findBookInSection(section, title);
            if (book != null && !book.isBorrowed()) {
                book.setBorrowed(true);
                System.out.println("Book borrowed.\n");
            } else if (book != null) {
                System.out.println("Book is already borrowed.");
            } else {
                System.out.println("Book not found.");
            }
        } else {
            System.out.println("Section not found.");
        }
    }

    private static void returnBook(Scanner scanner) {
        showSections();
        System.out.print("Enter section name: ");
        String sectionName = scanner.nextLine();

        Section section = findSectionByName(sectionName);
        if (section != null) {
            System.out.print("Enter book title: ");
            String title = scanner.nextLine();

            Book book = findBookInSection(section, title);
            if (book != null && book.isBorrowed()) {
                book.setBorrowed(false);
                System.out.println("Book returned.\n");
            } else if (book != null) {
                System.out.println("Book is not borrowed.");
            } else {
                System.out.println("Book not found.");
            }
        } else {
            System.out.println("Section not found.");
        }
    }

    private static Section findSectionByName(String name) {
        return sections.stream()
                .filter(section -> section.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    private static Book findBookInSection(Section section, String title) {
        return section.getBooks().stream()
                .filter(book -> book.getTitle().equalsIgnoreCase(title))
                .findFirst()
                .orElse(null);
    }
}