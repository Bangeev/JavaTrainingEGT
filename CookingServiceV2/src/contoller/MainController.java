package contoller;



import view.Menu;

import java.util.List;

public class MainController {
    private AdminController adminController;
    private UserController userController;
    private Menu menu;

    public MainController(AdminController adminController, UserController userController) {
        this.adminController = adminController;
        this.userController = userController;
        init();
    }

    public void init() {


        menu = new Menu("Main Menu", List.of(new Menu.Option("Manage Admins", () -> {
           adminController.showMenu();
            return "";
        }), new Menu.Option("Manage Users", () -> {
            userController.showMenu();
            return "";
        })));
    }

    public void showMenu() {
        menu.show();
    }

}
