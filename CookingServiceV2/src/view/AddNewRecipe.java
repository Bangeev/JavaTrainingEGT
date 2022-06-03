package view;

import category.Category;
import model.recipe.Recipe;

import java.util.Scanner;

public class AddNewRecipe implements EntityDialog<Recipe>{
    Scanner sc = new Scanner(System.in);

   /*  this.name = name;
        this.description = description;
        this.tags = tags;
        this.created = LocalDate.now();
        this.modified = LocalDate.now();*/
    @Override
    public Recipe input() {
        var recipe = new Recipe();
        while (recipe.getTitle() == null) {
            System.out.println("Recipe Name:");
            var ans = sc.nextLine().trim();
            if (ans.length() < 2 || ans.length() > 120) {
                System.out.println("Recipe title length should be between 2 and 120 characters.");
            } else {
                recipe.setTitle(ans);
            }
        }
        while (recipe.getShortDescription() == null) {
            System.out.println("Description info");
            var ans = sc.nextLine().trim();
            if (ans.length() < 2 || ans.length() > 250) {
                System.out.println("Recipe short description  length should be between 2 and 250 characters.");
            } else {
                recipe.setShortDescription(ans);
            }
        }
        while (recipe.getCookingTime() <= 0) {
            System.out.println("Enter Time for cook");
            System.out.println("Recipe cooking time can not be negative.");
            var ans = sc.nextLine().trim();
            recipe.setCookingTime(Integer.parseInt(ans));

        }
        while (recipe.getUsedProducts() == null) {
            System.out.println("Used Products");
            var ans = sc.nextLine().trim();
            if (ans.length() < 20 || ans.length() > 500) {
                System.out.println("Recipe used products  length should be between 20 and 500 characters.");
            } else {
                recipe.setUsedProducts(ans);
            }
        }
        while (recipe.getDescription() == null) {
            System.out.println("Description info");
            var ans = sc.nextLine().trim();
            if (ans.length() < 10 || ans.length() > 500) {
                System.out.println("Category description  length should be between 10 and 500 characters.");
            } else {
                recipe.setDescription(ans);
            }
        }

       return recipe;

    }
}
