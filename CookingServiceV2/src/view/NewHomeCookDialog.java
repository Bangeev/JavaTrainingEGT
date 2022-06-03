package view;


import common.Role;
import model.users.Admins;
import model.users.HomeCook;

import java.util.Arrays;
import java.util.Scanner;
import java.util.StringJoiner;
import java.util.regex.Pattern;


public class NewHomeCookDialog implements EntityDialog<HomeCook> {
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
    public HomeCook input() {
        var user = new HomeCook();
        while (user.getFirstName() == null) {
            System.out.println("First Name:");
            var ans = sc.nextLine().trim();
            if (ans.length() < 2 || ans.length() > 15) {
                System.out.println("Error: The user fist name should be between 2 and 15 characters long.");
            } else {
                user.setFirstName(ans);
            }
        }
        while (user.getLastName() == null) {
            System.out.println("Last Name:");
            var ans = sc.nextLine().trim();
            if (ans.length() < 2 || ans.length() > 15) {
                System.out.println("Error: The user last name should be between 2 and 15 characters long.");
            } else {
                user.setLastName(ans);
            }
        }
        while (user.getUsername() == null) {
            System.out.println("Username:");
            var ans = sc.nextLine().trim();
            if (!Pattern.matches(USERNAME_REGEX, ans)) {
                System.out.println("Error: The username should be between 2 and 15 characters long and contain only word characters.");
            } else {
                user.setUsername(ans);
            }
        }
        while (user.getPassword() == null) {
            System.out.println("Password:");
            var ans = sc.nextLine().trim();
            if (!Pattern.matches(PASSWORD_REGEX, ans)) {
                System.out.println("Error: The password should be between 8 and 15 characters long and should have atleast one small, capital letter, special symbol and digit.");
            } else {
                user.setPassword(ans);
            }
        }
        while (user.getEmail() == null) {
            System.out.println("Email:");
            var ans = sc.nextLine().trim();
            if (!Pattern.matches(VALID_EMAIL_PATTERN, ans)) {
                System.out.println("Error: Email should be valid email address.");
            } else {
                user.setEmail(ans);
                user.setRole(Role.HOME_COOK);
            }
        }






        return user;
    }
}
