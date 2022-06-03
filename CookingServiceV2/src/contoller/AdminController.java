package contoller;


import category.Category;
import model.recipe.Recipe;
import model.users.Admins;
import model.users.HomeCook;
import service.AdminsService;
import service.CategoryService;
import service.RecipeService;
import view.EntityDialog;
import view.Menu;

import java.util.List;

public class AdminController {
    private AdminsService adminsService;
    public CategoryService categoryService;
    public EntityDialog<Admins> addAdminDialog;
    public EntityDialog<Category> addNewCategory;
    private RecipeService recipeService;
    private EntityDialog<Recipe> addNewRecipe;
    private EntityDialog<HomeCook> userEdit;
    private HomeCook homeCook;


    private Menu menu;

    public AdminController(AdminsService adminsService, CategoryService categoryService,
                           EntityDialog<Admins> addAdminDialog, EntityDialog<Category> addNewCategory
    , RecipeService recipeService, EntityDialog<Recipe> addNewRecipe,EntityDialog<HomeCook> userEdit) {
        this.adminsService = adminsService;
        this.addAdminDialog = addAdminDialog;
        this.addNewCategory = addNewCategory;
        this.categoryService = categoryService;
        this.recipeService = recipeService;
        this.addNewRecipe = addNewRecipe;
        this.userEdit = userEdit;

        init();
    }

    public void init() {
//        userService.loadData();
        menu = new Menu("Admins Menu", List.of(
                new Menu.Option("Load Admins", () -> {
                    System.out.println("Loading admins ...");
                    //adminsService.loadData();
                    return "Users loaded successfully.";
                }),
                new Menu.Option("Print All Admins", () -> {
                    var admins = adminsService.getAllAdmins();
                    admins.forEach(admin -> System.out.println(admin));
                    return "Total admins count: " + admins.size();
                }),
                new Menu.Option("Add New Admin", () -> {
                    var admin = addAdminDialog.input();
                    var created = adminsService.addAdmin(admin);
                    return String.format("Admin ID:%s: '%s' added successfully.",
                            created.getId(), created.getUsername());
                }),
                new Menu.Option("Add new Category", () -> {
                    addNewCategory.input();
                    System.out.println(categoryService.getAllCategories());
                    return "All Categoryes";

                }),
                new Menu.Option("Add Recipe", () -> {
                    var recipe = addNewRecipe.input();
                    var created = recipeService.addRecipe(recipe);
                    return String.format("User ID:%s: '%s' added successfully.",
                            created.getId(), created.getTitle());
                }),
                new Menu.Option("Edit User", () -> {
                    var userEditt = userEdit.input();
                  return "editted";
                })
        ));
    }

    public void showMenu() {
        menu.show();
    }

}
