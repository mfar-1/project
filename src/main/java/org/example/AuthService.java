package org.example;

import db.DataSource;
import entity.User;
import java.io.IOException;
import java.util.UUID;

import static db.DataSource.*;
public class AuthService {
    AdminService adminService= new AdminService();
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
            switch (scannerInt.nextInt()) {
                case 0 -> {
                    System.out.println("\033[36mexiting the program...\033[36m");
                    return;
                }
                case 1  -> signUp();
                case 2  -> signIn();
            }
        }
    }

    private void signUp() {
        System.out.println("enter the name: ");
        String name = scannerStr.nextLine();
        System.out.println("enter the surname: ");
        String surname = scannerStr.nextLine();
        System.out.println("enter the age:");
        int age = scannerInt.nextInt();
        scannerStr.nextLine();

        System.out.println("enter the password:");
        String password = scannerStr.nextLine();
        User user  =new User(name, surname, age, UUID.randomUUID().toString(), password);
        users.add(user);
        try {
            System.out.println("\033[33muser added successfully...\033[33m");
        }catch (IllegalArgumentException e ){
            System.out.println(e.getMessage());
        }
    }

    private void signIn() {
        System.out.println("enter your login:");
        String login = scannerStr.nextLine();
        System.out.println("enter your password:");
        String password = scannerStr.nextLine();
        if (login.equals("admin") && password.equals("root123")) {
            System.out.println("\033[36mwelcome to admin menu....\033[36m");
            setCurrentUser(admin);
            adminService.service();
            return;
        }
        for (User user : users) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                System.out.println("\033[36mwelcome to user menu...\033[36m");
                setCurrentUser(user);
                userService.service();
                return;
            }
        }
        System.out.println("\033[32muser not found...\033[32m");
    }


}