import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Section {
    private String name;
    private State state;
    private int bookAmount;
    private List<Book> books = new ArrayList<>();

    public Section(String name) {
        this.name = name;
    }

    public Section(String name, State state, int bookAmount) {
        this.name = name;
        this.state = state;
        this.bookAmount = bookAmount;
    }

    public Section(String name, State state, int bookAmount, List<Book> books) {
        this.name = name;
        this.state = state;
        this.bookAmount = bookAmount;
        this.books = books;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public int getBookAmount() {
        return bookAmount;
    }

    public void setBookAmount(int bookAmount) {
        this.bookAmount = bookAmount;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Section{" +
                "name='" + name + '\'' +
                ", state=" + state +
                ", bookAmount=" + bookAmount +
                ", books=" + books +
                '}';
    }
}
