package contoller;



import model.recipe.Recipe;
import model.users.Admins;
import model.users.HomeCook;
import model.users.Users;
import service.AdminsService;
import service.HomeCookService;
import service.RecipeService;
import view.EntityDialog;
import view.Menu;

import java.util.List;

public class UserController {
    private HomeCookService homeCookService;
    private EntityDialog<HomeCook> addHomeCookDialog;
    private RecipeService recipeService;
    private EntityDialog<Recipe> addNewRecipe;
    private Menu menu;

    public UserController(HomeCookService homeCookService,
                          EntityDialog<HomeCook> addHomeCookDialog,
                          RecipeService recipeService, EntityDialog<Recipe> addNewRecipe) {
        this.homeCookService = homeCookService;
        this.addHomeCookDialog = addHomeCookDialog;
        this.recipeService = recipeService;
        this.addNewRecipe = addNewRecipe;
        init();
    }

    public void init() {
//        userService.loadData();
        menu = new Menu("Users Menu", List.of(
                new Menu.Option("Add New User", () -> {
                    var user = addHomeCookDialog.input();
                    var created = homeCookService.addHomeCook(user);
                    return String.format("User ID:%s: '%s' added successfully.",
                            created.getId(), created.getUsername());
                }),
                new Menu.Option("Add Recipe", () -> {
                    var recipe = addNewRecipe.input();
                    var created = recipeService.addRecipe(recipe);
                    return String.format("User ID:%s: '%s' added successfully.",
                            created.getId(), created.getTitle());
                })
        ));
    }

    public void showMenu() {
        menu.show();
    }

}
