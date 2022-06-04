import category.Category;
import common.Gender;
import common.Status;
import dao.*;
import dao.impl.DaoFactoryImpl;
import exception.InvalidEntityDataException;
import exception.NoneExistingEntityException;
import model.recipe.Recipe;
import model.users.Admins;
import model.users.HomeCook;
import model.users.Users;
import service.AdminsService;
import service.CategoryService;
import service.HomeCookService;
import service.RecipeService;
import service.impl.AdminsServiceImpl;
import service.impl.CategoryServiceImpl;
import service.impl.HomeCookServiceImpl;
import service.impl.RecipeServiceImpl;
import util.*;

import java.util.ArrayList;
import java.util.Collection;


public class Main {
    public static void main(String[] args) throws InvalidEntityDataException, NoneExistingEntityException {

        //TODO Да добавя  util package за да направя валидациите отделно от service,
        // както ми се каза в feedback  (ГОТОВО)



        var user1 = new Admins("Georgi", "Bangeev", "test@gmail.com", "admin123"
                , "admin123qdwqwW!", Gender.MALE.toString(), Status.ACTIVE.toString(), new ArrayList<>(), new ArrayList<>());
        var user2 = new HomeCook("Ivan", "Ivanov", "Ivanov@abv.bg", "homecook12"
                , "ivanov94", Gender.MALE.toString(), Status.ACTIVE.toString(), new ArrayList<>());
        Category category = new Category("CategoryOne", "Bakedacsacascascascasc", "test");
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
        AdminsServiceImpl us = new AdminsServiceImpl(ur, new AdminValidator());
        HomeCookRepository hc = daoFactory.createHomeCookRepository();
        HomeCookServiceImpl hcs = new HomeCookServiceImpl(hc, new HomeCookValidator());
        RecipeRepository rr = daoFactory.createRecipeRepository();
        RecipeServiceImpl rs = new RecipeServiceImpl(rr,  new RecipeValidator());



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
        HomeCook homeCook = new HomeCook();
        System.out.println("LOGGERS");
        AdminRepository log1 = LoggingHandler.withLogging(ur, AdminRepository.class);
        log1.changeUserData(new Users(),"","");
        log1.addNewRecipes(recipe);
        log1.addNewCategories(category);
        HomeCookRepository log2 = LoggingHandler.withLogging(hc, HomeCookRepository.class);
        log2.addNewRecipe();
        log2.changePassword(homeCook,"");
        log2.updateFirstName(homeCook, "");
        log2.updateLastName(homeCook,"");
        RecipeRepository log3 = LoggingHandler.withLogging(rr, RecipeRepository.class);
        log3.findBySearchingInCategory();
        log3.filterByCookingTime();
        log3.findBySearching("","","","");
        DaoFactory log4 = LoggingHandler.withLogging(daoFactory, DaoFactory.class);
        log4.createAdminRepository();
        log4.createHomeCookRepository();
        log4.createRecipeRepository();
        log4.createCategoryRepository();

        AdminsService log5 = LoggingHandler.withLogging(us, AdminsService.class);
        log5.addAdmin(user1);
        log5.addNewCategory(category);
        log5.addNewRecipe(recipe);
        log5.changeUserData(user2,"","");
        log5.deleteAdminById(1L);
        log5.getAllAdmins();
        log5.updateAdmin(user1);
        HomeCookService log6 = LoggingHandler.withLogging(hcs, HomeCookService.class);
        log6.getAllHomeCooks();
        log6.addHomeCook(user2);
        log6.changePassword(user2,"");
        log6.updateFirstName(user2,"");
        log6.deleteHomeCookById(2L);
        log6.updateHomeCook(user2);
        log6.updateLastName(user2,"");
        RecipeService log7 = LoggingHandler.withLogging(rs, RecipeService.class);
        log7.addRecipe(recipe);
        log7.filterByCookingTime();
        log7.findByBrowsingCategories();
        log7.getAllRecipes();
        CategoryRepository categoryRepository = daoFactory.createCategoryRepository();
        CategoryServiceImpl categoryService = new CategoryServiceImpl(categoryRepository, new CategoryValidator());
        CategoryService log8 = LoggingHandler.withLogging(categoryService, CategoryService.class);
        log8.addCategory(category);
        log8.getAllCategories();
        log8.updateCategory(category);

//TODO Да изчистя мейна.




    }
}

