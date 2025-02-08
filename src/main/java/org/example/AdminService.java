package org.example;

import java.util.Scanner;

public class AdminService {
    private final Scanner scanner = DB.scanner;

    public void addUser() {
        System.out.println("Enter the name: ");
        String name = scanner.nextLine();
        System.out.println("Enter the surname: ");
        String surname = scanner.nextLine();
        System.out.println("Enter the age: ");
        int age = DB.scanner.nextInt();
        System.out.println("Enter the password: ");
        String password = scanner.nextLine();

        User newUser = new User(name, surname, age, password);
        DB.users.add(newUser);
        System.out.println("User added successfully...");
    }

    public void addSection() {
        System.out.println("Enter section name: ");
        String sectionName = scanner.nextLine();

        Section newSection = new Section(sectionName);
        DB.sections.add(newSection);
        System.out.println("Section added successfully...");
    }

    public void removeSection() {
        System.out.println("Enter section name to remove: ");
        String sectionName = scanner.nextLine();

        Section section = DB.sections.stream()
                .filter(s -> s.getName().equalsIgnoreCase(sectionName))
                .findFirst()
                .orElse(null);

        if (section != null) {
            DB.sections.remove(section);
            System.out.println("Section removed successfully.");
        } else {
            System.out.println("Section not found.");
        }
    }

    public void addBookToSection() {
        System.out.println("Enter section name: ");
        String sectionName = scanner.nextLine();

        Section section = DB.sections.stream()
                .filter(s -> s.getName().equalsIgnoreCase(sectionName))
                .findFirst()
                .orElse(null);

        if (section == null) {
            System.out.println("Section not found.");
            return;
        }

        System.out.println("Enter book title: ");
        String title = scanner.nextLine();
        System.out.println("Enter book author: ");
        String author = scanner.nextLine();

        Book newBook = new Book(title, author, State.AVAILABLE);
        section.addBook(newBook);
        System.out.println("Book added to section successfully.");
    }

    public void removeBookFromSection() {
        System.out.println("Enter section name: ");
        String sectionName = scanner.nextLine();

        Section section = DB.sections.stream()
                .filter(s -> s.getName().equalsIgnoreCase(sectionName))
                .findFirst()
                .orElse(null);

        if (section == null) {
            System.out.println("Section not found.");
            return;
        }

        System.out.println("Enter book title to remove: ");
        String title = scanner.nextLine();

        Book book = section.getBooks().stream()
                .filter(b -> b.getTitle().equalsIgnoreCase(title))
                .findFirst()
                .orElse(null);

        if (book != null) {
            section.getBooks().remove(book);
            System.out.println("Book removed successfully.");
        } else {
            System.out.println("Book not found.");
        }
    }

    public void toggleSectionState() {
        System.out.println("Enter section name: ");
        String sectionName = scanner.nextLine();

        Section section = DB.sections.stream()
                .filter(s -> s.getName().equalsIgnoreCase(sectionName))
                .findFirst()
                .orElse(null);

        if (section != null) {
            section.setEnabled(!section.isEnabled());
            String state = section.isEnabled() ? "enabled" : "disabled";
            System.out.println("Section is now " + state + ".");
        } else {
            System.out.println("Section not found.");
        }
    }
}
