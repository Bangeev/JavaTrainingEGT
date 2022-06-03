import category.Category;
import common.Gender;
import common.Status;
import contoller.AdminController;
import contoller.MainController;
import contoller.UserController;
import dao.*;
import dao.impl.*;
import exception.InvalidEntityDataException;
import exception.NoneExistingEntityException;
import model.recipe.Recipe;
import model.users.Admins;
import model.users.HomeCook;
import service.AdminsService;
import service.CategoryService;
import service.HomeCookService;
import service.RecipeService;
import service.impl.AdminsServiceImpl;
import service.impl.CategoryServiceImpl;
import service.impl.HomeCookServiceImpl;
import service.impl.RecipeServiceImpl;
import view.*;

import java.util.ArrayList;


public class Main {
    public static void main(String[] args) throws InvalidEntityDataException, NoneExistingEntityException {




        AdminRepository adminRepository = new AdminRepositoryImpl();
        AdminsService adminsService = new AdminsServiceImpl(adminRepository);
        var addAdminDialog = new NewAdminsDialog();
        var addNewCategory = new AddNewCategory();

        RecipeRepository recipeRepository = new RecipeRepositoryMemoryImpl();
        RecipeService recipeService = new RecipeServiceImpl(recipeRepository);

        CategoryRepository categoryRepository = new CategoryRepositoryImpl();
        CategoryService categoryService = new CategoryServiceImpl(categoryRepository);

        var addUserUpdateDialog = new UserUpdateDialog();

        HomeCookRepository homeCookRepository = new HomeCookRepositoryImpl();
        HomeCookService homeCookService = new HomeCookServiceImpl(homeCookRepository);
        var addHomeCookDialog = new NewHomeCookDialog();
        var addNewRecipe = new AddNewRecipe();


        AdminController adminController = new AdminController(adminsService,categoryService,
                addAdminDialog, addNewCategory,recipeService,addNewRecipe, addUserUpdateDialog);
        UserController userController = new UserController(homeCookService, addHomeCookDialog,recipeService,addNewRecipe);
        MainController mainController = new MainController(adminController,userController);
        mainController.showMenu();



/*
        var user1 = new Admins("Georgi", "Bangeev", "test@gmail.com", "admin123"
                , "admin123", Gender.MALE.toString(), Status.ACTIVE.toString(), new ArrayList<>(), new ArrayList<>());
        var user2 = new HomeCook("Ivan", "Ivanov", "Ivanov@abv.bg", "homecook12"
                , "ivanov94", Gender.MALE.toString(), Status.ACTIVE.toString(), new ArrayList<>());
        Category category = new Category("CategoryOne", "Baked", "test");
        Category category2 = new Category("CategoryTwo", "Baked2", "test2");

        Recipe recipe = new Recipe(category, "Bread", "Black", "Really Good Food",
                120, "Water,Salt,Flour,Milch,Eggs", "https://bg.wikipedia.org/wiki/%D0%A5%D0%BB%D1%8F%D0%B1#/media/%D0%A4%D0%B0%D0%B9%D0%BB:Anadama_bread_(1).jpg",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua." +
                        " Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit" +
                        " in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident," +
                        " sunt in culpa qui officia deserunt mollit anim id est laborum.", "Bread");
        Recipe recipe3 = new Recipe(category, "Bread", "Marian", "Really Good Food",
                10, "Water,Salt,Flour,Milch,Eggs", "https://bg.wikipedia.org/wiki/%D0%A5%D0%BB%D1%8F%D0%B1#/media/%D0%A4%D0%B0%D0%B9%D0%BB:Anadama_bread_(1).jpg",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua." +
                        " Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure " +
                        "dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non" +
                        " proident, sunt in culpa qui officia deserunt mollit anim id est laborum.Lorem ipsum dolor sit amet, consectetur adipiscing elit," +
                        " sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris " +
                        "nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor i" +
                        "n reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident," +
                        " sunt in culpa qui officia deserunt mollit anim id est laborum.", "Bread");
        Recipe recipe2 = new Recipe(category, "Bread2", "Marian", "Really Good Food",
                1, "Water,Salt,Flour,Milch,Eggs", "https://bg.wikipedia.org/wiki/%D0%A5%D0%BB%D1%8F%D0%B1#/media/%D0%A4%D0%B0%D0%B9%D0%BB:Anadama_bread_(1).jpg",
                "Lorem Ipsum is simply dummy text of ", "Bread");

        DaoFactory daoFactory = new DaoFactoryImpl();

        AdminRepository ur = daoFactory.createAdminRepository();
        AdminsServiceImpl us = new AdminsServiceImpl(ur);
        HomeCookRepository hc = daoFactory.createHomeCookRepository();
        HomeCookServiceImpl hcs = new HomeCookServiceImpl(hc);
        RecipeRepository rr = daoFactory.createRecipeRepository();
        RecipeServiceImpl rs = new RecipeServiceImpl(rr);


        us.addAdmin(user1);

        us.addNewCategory(category);
        us.addNewRecipe(recipe);

        us.addNewCategory(category2);
        us.addNewRecipe(recipe2);


        hcs.addHomeCook(user2);
        hcs.changePassword(user2, "newPassword123");

        System.out.println();
        for (HomeCook allHomeCook : hcs.getAllHomeCooks()) {
            System.out.println(allHomeCook);
        }

        us.changeUserData(user2, "username", "number1");
        us.changeUserData(user2, "last name", "gosho");


        for (HomeCook allHomeCook : hcs.getAllHomeCooks()) {
            System.out.println(allHomeCook);
        }

        us.updateAdmin(user1);

        System.out.println();
        for (Admins allAdmin : us.getAllAdmins()) {
            System.out.println(allAdmin);
        }


        System.out.println();
        try {
            us.addNewRecipe(rs.addRecipe(recipe));
            us.addNewRecipe(rs.addRecipe(recipe3));
            //us.addNewRecipes(rs.addRecipe(recipe2));
        } catch (InvalidEntityDataException ex) {
            ex.printStackTrace();
        }


        //        try {
//            rs.addRecipe(recipe);
//            rs.addRecipe(recipe2);
//        } catch (InvalidEntityDataException ex){
//            ex.printStackTrace();
//        }
        System.out.println();
        rs.filterByCookingTime();

        System.out.println();

*/

        //////////////////////////////////////////////////////





    }
}

