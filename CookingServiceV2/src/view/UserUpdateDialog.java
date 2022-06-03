package view;


import dao.AdminRepository;
import dao.impl.AbstractRepository;
import dao.impl.AdminRepositoryImpl;
import model.users.HomeCook;
import service.HomeCookService;
import service.impl.AdminsServiceImpl;

import java.util.*;


public class UserUpdateDialog implements EntityDialog<HomeCook> {
    AdminsServiceImpl adminsService;
    HomeCook homeCook;
    HomeCookService homeCookService;

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
        System.out.println("Enter valid username to search");
        while (homeCook.getUsername().equals(sc.nextLine().trim())) {
            System.out.println(homeCook.getUsername());
            AdminRepository adminRepository = new AdminRepositoryImpl();
            System.out.println("What to edit  and then to waht to edit");
            adminRepository.changeUserData(homeCook, sc.nextLine().trim(), sc.nextLine().trim());

            System.out.println("result");
            System.out.println(homeCook.getUsername());
        }


        return homeCook;
    }


}

