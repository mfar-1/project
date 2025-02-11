package org.example.service;

public class AdminService {
    package service;
import entity.*;

import java.util.Date;
import java.util.UUID;

import static db.DataSource.*;
    public class AdminService {
        public void service() {
            while (true) {
                System.out.println("""
                 
                    0- exit 
                    1- add section 
                    2- show all sections 
                    3- show a section 
                    4- set section state 
                    5- add book 
                    6- remove a book 
                    7- show book  state
                    8- show all users 
                    9- show an user
                     
                    """);
                switch (scannerInt.nextInt()) {
                    case 0 -> {
                        System.out.println("exiting the page...");
                        return;
                    }
                    case 1 -> addSection();
                    case 2 -> showAllSections();
                    case 3 -> showSection();
                    case 4 -> setSectionState();
                    case 5 -> addBook();
                    case 6 -> removeBook();
                    case 7 -> showBookState();
                    case 8 -> showAllUsers();
                    case 9 -> showUser();
                }
            }
        }


        private void showUser() {
            showAllUsers();
            int i=1;
            for (User user : users) {
                System.out.println(i + "." + user);
                i++;
            }
            System.out.println("select the user:");
            int selectedUserIndex = scannerInt.nextInt();
            User selectedUser = users.get(selectedUserIndex-1);
            System.out.println(selectedUser);
        }
        private void showAllUsers () {
            for (User user : users) {
                System.out.println(user);
            }
        }
        private void showBookState () {
            if(books.isEmpty()){
                System.out.println("there is no any books so far...");
                return;
            }
            System.out.println("enter the book name to show state: ");
            String bookName = scannerStr.nextLine().trim();
            Book chosenBook=null;
            for (Book book : books) {
                if(book.getTitle().equalsIgnoreCase(bookName)){
                    chosenBook=book;
                    break;
                }
            }
            if(chosenBook==null){
                System.out.println("book not found❌..., try again!!!");
                return;
            }
            System.out.println("\nbook information:");
            System.out.println("- -- - -- - -- - -- - -- - -- - -- - -- - -- - --");
            System.out.println("book: " + chosenBook.getTitle());
            System.out.println("total amount in the library " + chosenBook.getTotalAmount());
            System.out.println("amount of borrowed : " + chosenBook.getBorrowedAmount());
            System.out.println("available in the library: " + (chosenBook.getTotalAmount()-chosenBook.getBorrowedAmount()));
            if(chosenBook.getBorrowedAmount()>0){
                System.out.println("Status BORROWED");
                System.out.println("borrowed by:");
                for (User user : users) {
                    if (user.getBorrowedBooks().contains(chosenBook)){
                        System.out.println(user.getName() + "ID: " + user.getId());
                    }
                }
            }else {
                System.out.println("STATUS AVAILABLE");
            }
            System.out.println("- -- - -- - -- - -- - -- - -- - -- - -- - -- - --");
        }
        private void removeBook () {
            showAllSections();
            System.out.println("choose a section to remove a book:");
            int i =scannerInt.nextInt();
            scannerInt.nextLine();
            if(i<1 || i>sections.size()){
                System.out.println("invalid input...pls try again!!!");
                return;
            }
            Section chosenSection = sections.get(i-1);

            if(chosenSection.getBooks().isEmpty()){
                System.out.println("there is no any books in this section...");
                return;
            }
            System.out.println("books: ");
            int bookIndex=1;
            for (Book book : chosenSection.getBooks()) {
                System.out.println(bookIndex + "." + book.getTitle());
                bookIndex++;
            }
            System.out.println("choose a book to remove: ");
            int removingBookIndex= scannerInt.nextInt();
            scannerInt.nextLine();
            if(removingBookIndex<1 || removingBookIndex>chosenSection.getBooks().size()){
                System.out.println("invalid input, pls try again...");
                return;
            }

            Book removingBook= chosenSection.getBooks().get(removingBookIndex-1);
            chosenSection.getBooks().remove(removingBook);
            System.out.println(removingBook.getTitle() + " ->  removed successfully✅");

        }

        private void addBook () {
            showAllSections();
            System.out.println("choose a section to add a book:");
            int i = scannerInt.nextInt();
            scannerInt.nextLine();
            if (i < 1 || i > sections.size()) {
                System.out.println("\\033[32minvalid input...pls try again!!!\\033[32m");
                return;
            }
            Section chosenSection = sections.get(i - 1);
            System.out.println(chosenSection.getName() + "  section is chosen...");
            System.out.println("enter the book name:");
            String name = scannerStr.nextLine().trim();
            Book existingBook = null;
            for (Book book : books) {
                if (book.getTitle().equalsIgnoreCase(name)) {
                    existingBook = book;
                    break;
                }
            }
            if (existingBook != null) {
                System.out.println("this book already exists.." + existingBook.getTotalAmount() + " copies");
                while (true) {
                    System.out.println("""
                            do you want to add more:
                           1-  yes
                           2-  no 
                            """);
                    switch (scannerInt.nextInt()) {
                        case 1 -> {
                            System.out.println("how many copies you want to add:");
                            int amountBooks = scannerInt.nextInt();
                            if (amountBooks <= 0) {
                                System.out.println("incorrect input...try again!");
                                return;
                            }
                            existingBook.setTotalAmount(existingBook.getTotalAmount() + amountBooks);
                            System.out.println(existingBook.getTitle() + " -> added " + amountBooks + "\ntotal amount:" + existingBook.getTotalAmount());
                            return;
                        }
                        case 2 -> {
                            System.out.println("no copies added..");
                            return;
                        }

                    }
                }

            } else {
                System.out.println("how many copies do you want to add:");
                int amountBooks = scannerInt.nextInt();
                scannerInt.nextLine();
                if(amountBooks<=0){
                    System.out.println("invalid input! try again...");
                    return;
                }
                Book newBook = new Book(name, UUID.randomUUID().toString(), BookState.AVAILABLE, new Date(), amountBooks);
                books.add(newBook);
                chosenSection.getBooks().add(newBook);
                System.out.println("book added successfully✅...");
            }
        }

        private void setSectionState () {
            showAllSections();
            System.out.println("choose a section: ");
            int i = scannerInt.nextInt();
            scannerInt.nextLine();
            if (i < 1 || i > sections.size()) {
                System.out.println("invalid input, pls try again...");
                return;
            }
            Section chosenSection = sections.get(i - 1);
            System.out.println(chosenSection.getName() + " is chosen...");
            while (true) {
                System.out.println("""
                            0 - back to main page 
                            1 - set section Disabled
                            2 - set section Enabled
                            """);
                switch (scannerInt.nextInt()) {
                    case 0 -> {
                        System.out.println("existing the page...");
                        return;
                    }
                    case 1 -> {
                        if (chosenSection.getState().equals(State.ENABLED)) {
                            chosenSection.setState(State.DISABLED);
                            System.out.println("section state turned to DISABLED...");
                        } else {
                            System.out.println("chosen section is already DISABLED...");
                            return;
                        }
                    }
                    case 2 -> {
                        if (chosenSection.getState().equals(State.DISABLED)) {
                            chosenSection.setState(State.ENABLED);
                            System.out.println("section state turned to ENABLED...");
                        } else {
                            System.out.println("chosen section is already ENABLED...");
                            return;
                        }
                    }
                }
            }
        }




        private void showSection() {
            showAllSections();
            System.out.println("choose a section: ");
            int i = scannerInt.nextInt();
            scannerInt.nextLine();
            if (i < 1 || i > sections.size()) {
                System.out.println("invalid input, pls try again...");
                return;
            }
            Section chosenSection = sections.get(i - 1);
            System.out.println("section name: "  + chosenSection.getName() + "\nbook amount:  " + chosenSection.getBookAmount() +
                    "\nbooks:" + chosenSection.getBooks() + "\nsection state: " + chosenSection.getState());
        }


        private void showAllSections () {
            int i=1;
            for (Section section : sections) {
                System.out.println((i)+" " + section.getName());
                i++;

            }
            System.out.println("\033[34m****************************\033[34m");
        }

        private void addSection() {
            System.out.println("enter the name of the section:");
            String name = scannerStr.nextLine();
            for (Section section : sections) {
                if(section.getName().equalsIgnoreCase(name)){
                    System.out.println("section with this name already exists...please choose another...");
                    return;
                }
            }
            sections.add(new Section(name));
            System.out.println("\033[35msection added successfully...");

        }
    }

}
