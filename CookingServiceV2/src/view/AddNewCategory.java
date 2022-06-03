package view;

import category.Category;
import common.Role;
import model.users.Admins;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.regex.Pattern;

public class AddNewCategory implements EntityDialog<Category>{
    Scanner sc = new Scanner(System.in);

   /*  this.name = name;
        this.description = description;
        this.tags = tags;
        this.created = LocalDate.now();
        this.modified = LocalDate.now();*/
    @Override
    public Category input() {
        var category = new Category();
        while (category.getName() == null) {
            System.out.println("Category Name:");
            var ans = sc.nextLine().trim();
            if (ans.length() < 2 || ans.length() > 120) {
                System.out.println("Category name length should be between 2 and 120 characters.");
            } else {
                category.setName(ans);
            }
        }
        while (category.getDescription() == null) {
            System.out.println("Description info");
            var ans = sc.nextLine().trim();
            if (ans.length() < 10 || ans.length() > 500) {
                System.out.println("Category description  length should be between 10 and 500 characters.");
            } else {
                category.setDescription(ans);
            }
        }

       return category;

    }
}
