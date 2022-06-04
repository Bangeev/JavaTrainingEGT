package model.users;

import category.Category;
import common.Role;
import dao.Identifiable;
import model.recipe.Recipe;

import java.time.LocalDate;
import java.util.*;

public class Users implements Identifiable<Long> {

    private static long nextId = 0;
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private String gender;
    private Role role;
    private String status;
    private LocalDate created;
    private LocalDate modified;
    private List<Recipe> recipes = new ArrayList<>();

    Map<Category, List<Recipe>> allCategories = new HashMap<>();

    public Users() {
        id = ++nextId;
    }

    public Users(String firstName, String lastName, String email, String username, String password,
                 String gender, Role role, String status, List<Recipe> recipes) {
        id = ++nextId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.role = role;
        this.status = status;
        this.recipes = recipes;
        this.created = LocalDate.now();
        this.modified = LocalDate.now();
    }


    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public LocalDate getModified() {
        return modified;
    }

    public void setModified(LocalDate modified) {
        this.modified = modified;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    public Map<Category, List<Recipe>> getAllCategories() {
        return allCategories;
    }

    public void setAllCategories(Map<Category, List<Recipe>> allCategories) {
        this.allCategories = allCategories;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Users users = (Users) obj;
        return Objects.equals(id, users.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Users - ");
        sb.append("id=").append(id);
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", username='").append(username).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", gender='").append(gender).append('\'');
        sb.append(", role=").append(role);
        sb.append(", status='").append(status).append('\'');
        sb.append(", created=").append(created);
        sb.append(", modified=").append(modified);
        sb.append(", recipes=").append(recipes);
        sb.append(", allCategories=").append(allCategories);
        sb.append(", findRecipeByCategories='").append(findRecipeByCategories()).append('\'');
        sb.append(' ');
        return sb.toString();
    }

    public String findRecipeByCategories() {
        if (getRole().equals(Role.ADMIN)) {
            // make a messege
            Map<Category, List<Recipe>> searchInRecipe = new HashMap<>();

            for (var recipe : getAllCategories().entrySet()) {
                if (searchInRecipe.containsKey(recipe.getKey())) {
                    searchInRecipe.get(recipe.getKey()).add((Recipe) recipe.getValue());

                } else {
                    searchInRecipe.put(recipe.getKey(), new ArrayList<>());
                    searchInRecipe.get(recipe.getKey()).add(null);
                }
            }
            StringBuilder stringBuilder = new StringBuilder();
            for (Map.Entry<Category, List<Recipe>> element : searchInRecipe.entrySet()) {
                stringBuilder.append(element.getKey())
                        .append(System.lineSeparator())
                        .append("-")
                        .append(element.getValue())
                        .append(System.lineSeparator());
            }
            return stringBuilder.toString();
        } else return "Command is invalid!";
    }
}
