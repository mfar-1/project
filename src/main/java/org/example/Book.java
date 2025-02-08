package org.example;

import java.util.UUID;

public class Book {
    private String id;
    private String name;
    private String author;
    private State state;
    private Section section;

    public Book(String id, String name, String author, State state, Section section) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.state = state;
        this.section = section;
    }

    public Book(String title) {
        this.name = title;
        this.author = "Unknown";  // Default qiymat
        this.state = State.AVAILABLE;
        this.section = null;
    }

    public Book(String name, String author, State state) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.author = author;
        this.state = state;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public String getTitle() {
        return name;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", state=" + state +
                ", section=" + (section != null ? section.getName() : "No Section") +
                '}';
    }


}