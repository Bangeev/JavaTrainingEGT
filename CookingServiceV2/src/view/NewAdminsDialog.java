package view;


import common.Role;
import dao.impl.AbstractRepository;
import dao.impl.AdminRepositoryImpl;
import model.users.Admins;
import model.users.Users;

import java.util.Arrays;
import java.util.Scanner;
import java.util.StringJoiner;
import java.util.regex.Pattern;



public class NewAdminsDialog implements EntityDialog<Admins> {

    public static final String USERNAME_REGEX = "\\w{2,15}";
    public static final String VALID_EMAIL_PATTERN = "^(.+)@(.+)$";
    //            ^.*              : Start
//            (?=.{8,})        : Length
//            (?=.*[a-zA-Z])   : Letters
//            (?=.*\d)         : Digits
//            (?=.*[!#$%&? "]) : Special characters " +
//            .*$              : End
    public static final String PASSWORD_REGEX = "^.*(?=.{8,15})(?=.*[a-zA-Z])(?=.*\\d)(?=.*[!#$%&?+]).*$";
    public static final String PHONE_REGEX = "^[+()\\d\\s]{8,}$";
    public static Scanner sc = new Scanner(System.in);

    @Override
    public Admins input() {
        var admin = new Admins();
        while (admin.getFirstName() == null) {
            System.out.println("First Name:");
            var ans = sc.nextLine().trim();
            if (ans.length() < 2 || ans.length() > 15) {
                System.out.println("Error: The user fist name should be between 2 and 15 characters long.");
            } else {
                admin.setFirstName(ans);
            }
        }
        while (admin.getLastName() == null) {
            System.out.println("Last Name:");
            var ans = sc.nextLine().trim();
            if (ans.length() < 2 || ans.length() > 15) {
                System.out.println("Error: The user last name should be between 2 and 15 characters long.");
            } else {
                admin.setLastName(ans);
            }
        }
        while (admin.getUsername() == null) {
            System.out.println("Username:");
            var ans = sc.nextLine().trim();
            if (!Pattern.matches(USERNAME_REGEX, ans)) {
                System.out.println("Error: The username should be between 2 and 15 characters long and contain only word characters.");
            } else {
                admin.setUsername(ans);
            }
        }
        while (admin.getPassword() == null) {
            System.out.println("Password:");
            var ans = sc.nextLine().trim();
            if (!Pattern.matches(PASSWORD_REGEX, ans)) {
                System.out.println("Error: The password should be between 8 and 15 characters long and should have atleast one small, capital letter, special symbol and digit.");
            } else {
                admin.setPassword(ans);
            }
        }
        while (admin.getEmail() == null) {
            System.out.println("Email:");
            var ans = sc.nextLine().trim();
            if (!Pattern.matches(VALID_EMAIL_PATTERN, ans)) {
                System.out.println("Error: Email should be valid email address.");
            } else {
                admin.setEmail(ans);
                admin.setRole(Role.ADMIN);
            }
        }





        return admin;


    }
}
