package model.recipe;


import category.Category;
import dao.Identifiable;

import java.time.LocalDate;
import java.util.Objects;

public class Recipe implements Identifiable<Long>, Comparable<Recipe>{

    private static long nextId = 0;
    private long id = ++nextId;
    private Category category;
    private String title;
    private String author;
    private String shortDescription;
    private int cookingTime;
    private String usedProducts;
    private String picture;
    private String description;
    private String tags;
    private LocalDate created;
    private LocalDate modified;

    public Recipe(){

    }

    public Recipe(Recipe recipe){

    }

    public Recipe(Category category, String title, String author, String shortDescription,
                  int cookingTime, String usedProducts, String picture, String description, String tags) {

        this.category = category;
        this.title = title;
        this.author = author;
        this.shortDescription = shortDescription;
        this.cookingTime = cookingTime;
        this.usedProducts = usedProducts;
        this.picture = picture;
        this.description = description;
        this.tags = tags;
        this.created = LocalDate.now();
        this.modified = LocalDate.now();
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(long id) {

        this.id = id;
    }

    public static long getNextId() {
        return nextId;
    }

    public static void setNextId(long nextId) {
        Recipe.nextId = nextId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public int getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(int cookingTime) {
        this.cookingTime = cookingTime;
    }

    public String getUsedProducts() {
        return usedProducts;
    }

    public void setUsedProducts(String usedProducts) {
        this.usedProducts = usedProducts;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Recipe recipe = (Recipe) obj;
        return id == recipe.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Recipe - ");
        sb.append("id=").append(id);
        sb.append(", category=").append(category);
        sb.append(", title='").append(title).append('\'');
        sb.append(", author='").append(author).append('\'');
        sb.append(", shortDescription='").append(shortDescription).append('\'');
        sb.append(", cookingTime=").append(cookingTime);
        sb.append(", usedProducts='").append(usedProducts).append('\'');
        sb.append(", picture='").append(picture).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", tags='").append(tags).append('\'');
        sb.append(", created=").append(created);
        sb.append(", modified=").append(modified);
        sb.append(' ');
        return sb.toString();
    }


    @Override
    public int compareTo(Recipe with) {
        return Integer.compare(getCookingTime(), with.getCookingTime());
    }
}
