package org.example.service;

public class UserService {
    package service;
import entity.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static db.DataSource.*;
    public class UserService {
        public void service() {
            while (true) {
                System.out.println("""
                    0- exit
                    1- show sections
                    2- show a section 
                    3- borrow a book 
                    4- return a book 
                    5- current borrowed books
                    6- history  
                      """);
                System.out.println("------------------------------");
                System.out.println("choose an option:");
                switch (scannerInt.nextInt()) {
                    case 0 -> {
                        System.out.println("exiting the page...");
                        return;
                    }
                    case 1 -> showSections();
                    case 2 -> showSection();
                    case 3 -> borrowBook();
                    case 4 -> returnBook();
                    case 5 -> currentBorrowedBooks(getCurrentUser().getId());
                    case 6 -> history();

                }
            }
        }

        private void history() {
            String currentUserId = getCurrentUser().getId();
            List<Book> books = getBooks();
            boolean found = false;
            System.out.println("===" + currentUser.getName() + " -> your history===");
            for (Book book : books) {
                if (book.getBorrowTime()!=null &&
                        currentUserId.equals(book.getBorrowedUserId()) || currentUserId.equals(book.getLastBorrowedUserId())) {
                    System.out.println("- -- - -- - -- - -- - -- - -- - -- - -- - -- - -- - -- ");
                    System.out.println("Book: " + book.getTitle());
                    System.out.println("borrowed: " + book.getBorrowTime());
                    System.out.println("returned: " + (book.getReturnedTime() != null ? book.getReturnedTime() : "not returned yet.."));
                    System.out.println("- -- - -- - -- - -- - -- - -- - -- - -- - -- - -- - -- ");
                    found = true;

                }
            }
            if (!found) {
                System.out.println("your history is clear...");
            }
        }

        private void currentBorrowedBooks(String id) {
            System.out.println("your current borrowed books: ");
            List<Book> books = getBooks();
            boolean found = false;
            for (Book book : books) {
                if (book.getBookState() == BookState.BORROWED && id.equals(book.getBorrowedUserId())) {
                    System.out.println("book: " + book.getTitle()  + "/nid: " + book.getId() +  "\nborrowed at: " + book.getBorrowTime());
                    found = true;
                }
            }
            if (!found) {
                System.out.println("you have no any books borrowed...");
            }

        }

        private void returnBook() {
            System.out.println("enter your borrowed book id:");
            String borrowedBookId = scannerStr.nextLine();
            List<Book> books = getBooks();
            Book borrowedBook = null;
            String currentUserId = getCurrentUser().getId();
            for (Book book : books) {
                if (book.getId().equals(borrowedBookId) && book.getBookState().equals(BookState.BORROWED)
                        && currentUserId.equals(book.getBorrowedUserId())) {
                    borrowedBook = book;
                    break;
                }
            }
            if (borrowedBook != null) {
                LocalDateTime borrowTime = borrowedBook.getBorrowTime();
                LocalDateTime returnedTime = LocalDateTime.now();
                Duration duration = Duration.between(borrowTime, returnedTime);
                int minutesUsed = (int) duration.toMinutes();
                double paymentForBook = minutesUsed * 100;
                borrowedBook.setLastBorrowedUserId(borrowedBook.getBorrowedUserId()); //historyga saqlash
                borrowedBook.setBorrowedUserId(null);//boshqa userga berish
                borrowedBook.setBookState(BookState.AVAILABLE);
                // borrowedBook.setBorrowTime(null);
                borrowedBook.setReturnedTime(returnedTime);
                System.out.println(borrowedBook.getTitle() + " book is returned...");
                System.out.println("returned time: " + returnedTime);
                System.out.println("you " + minutesUsed + " minutes used " + borrowedBook.getTitle() + " book!");
                System.out.println("you should pay: " + paymentForBook + " soums!");
            } else {
                System.out.println("you entered incorrect id or you did not borrowed this book...");
            }

        }

        private void borrowBook() {
            String userId = getCurrentUser().getId();
            int borrowedBooksCount = 0;
            for (Book book : getBooks()) {
                if (book.getBookState() == BookState.BORROWED && userId.equals(book.getBorrowedUserId())) {
                    borrowedBooksCount++;
                }
            }
            if (borrowedBooksCount >= 5) {
                System.out.println("you can borrow up to 5 books, pls return before if you want to borrow again...");
                return;
            }
            System.out.println("enter the name of the book you want to borrow: ");
            scannerInt.nextLine();
            String bookNameToBorrow = scannerStr.nextLine().trim();
            List<Book> allBooks =new ArrayList<>();
            for (Section section : sections) {
                allBooks.addAll(section.getBooks());
            }
            Book availableBook = null;
            for (Book book : allBooks) {
                if (book.getTitle().equalsIgnoreCase(bookNameToBorrow) &&
                        book.getBookState().equals(BookState.AVAILABLE)
                        && book.getTotalAmount()>0) {
                    availableBook = book;
                    break;
                }
            }
            if (availableBook != null) {
                availableBook.setBookState(BookState.BORROWED);
                availableBook.setBorrowedUserId(userId);
                availableBook.setBorrowTime(LocalDateTime.now());
                if(availableBook.getTotalAmount()>1){
                    Book newBook = new Book(availableBook.getTitle(), availableBook.getTotalAmount()-1, UUID.randomUUID().toString(), BookState.AVAILABLE );
                    books.add(newBook);

                }
                for (Section section : sections) {
                    for (Book book : section.getBooks()) {
                        if (book.getTitle().equalsIgnoreCase(availableBook.getTitle())) {
                            book.setTotalAmount(book.getTotalAmount() - 1);
                        }
                    }
                }
                System.out.println(availableBook.getTitle() + " book is borrowed...");
                System.out.println("your borrowed book id: " + availableBook.getId());
                System.out.println("time you borrowed: " + availableBook.getBorrowTime());
                //   int  availableRemained = availableBook.getTotalAmount();
            } else {
                System.out.println("this book is not available or all are borrowed already...");
            }

        }

        private void showSection() {
            showSections();
            System.out.println("choose a section: ");
            int chosenSectionIndex = scannerInt.nextInt();
            scannerInt.nextLine();
            if (chosenSectionIndex < 1 || chosenSectionIndex > sections.size()) {
                System.out.println("invalid input! pls try again...");
                return;
            }


            Section chosenSection = sections.get(chosenSectionIndex - 1);
            System.out.println(chosenSection.getName() + " chosen...");
            if (chosenSection.getBooks().isEmpty()) {
                System.out.println("no any books in this section..");
                return;
            }
            System.out.println("books: ");
            for (Book book : chosenSection.getBooks()) {
                System.out.println(book.getTitle());

            }
            System.out.println("**************");
        }
        private void showSections() {
            int i=1;
            boolean found = false;
            System.out.println("=================");
            for (Section section : getSections()) {
                if(section.getState().equals(State.ENABLED)){
                    System.out.println(i + ". " +  section.getName());
                    i++;
                    found= true;
                }
            }
            System.out.println("=================");
            if(!found){
                System.out.println("no any sections so far...");
            }
        }

    }

}
