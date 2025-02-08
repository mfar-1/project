package org.example;

import java.util.ArrayList;
import java.util.List;

public class Section {
    private String name;
    private boolean enabled;
    private List<Book> books;

    public Section(String name) {
        this.name = name;
        this.enabled = true;
        this.books = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<Book> getBooks(){
        return books;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    @Override
    public String toString() {
        return "Section: " + name + "(Enabled: " + enabled + ")";
    }
}
