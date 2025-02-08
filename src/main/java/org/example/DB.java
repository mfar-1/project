package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class DB {
    public static final Scanner scanner = new Scanner(System.in);

    public static final List<User> users = new ArrayList<>();
    public static final List<Section> sections = new ArrayList<>();

    public static User currentUser;
    public static final User admin = new User("Admin", "Root", 0, "admin-uuid", "root123", Role.ADMIN);

    static {
        users.add(admin);
        users.add(new User("Ali", "Aliyev", 23, UUID.randomUUID().toString(), "ali123"));
        users.add(new User("Vali", "Valiyev", 30, UUID.randomUUID().toString(), "vali123"));
        users.add(new User("Karim", "Karimov", 20, UUID.randomUUID().toString(), "karim123"));
        users.add(new User("Salim", "Salimov", 33, UUID.randomUUID().toString(), "salim123"));
        users.add(new User("Komil", "Komilov", 45, UUID.randomUUID().toString(), "komil123"));
        users.add(new User("Qobil", "Qobilov", 18, UUID.randomUUID().toString(), "qobil123"));

        addSection("Science Fiction", new String[][]{
                {"Dune", "Frank Herbert", "AVAILABLE"},
                {"Neuromancer", "William Gibson", "AVAILABLE"}
        });

        addSection("History", new String[][]{
                {"Sapiens", "Yuval Noah Harari", "AVAILABLE"},
                {"The Silk Roads", "Peter Frankopan", "BORROWED"}
        });

        addSection("Technology", new String[][]{
                {"Clean Code", "Robert C. Martin", "AVAILABLE"},
                {"Introduction to Algorithms", "Thomas H. Cormen", "AVAILABLE"}
        });
    }

    private static void addSection(String name, String[][] books) {
        Section section = new Section(name);
        for (String[] book : books) {
            section.addBook(new Book(book[0], book[1], State.valueOf(book[2])));
        }
        sections.add(section);
    }
}