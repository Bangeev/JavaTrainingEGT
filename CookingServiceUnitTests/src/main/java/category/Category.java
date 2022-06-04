package category;

import dao.Identifiable;

import java.time.LocalDate;
import java.util.Objects;

public class Category implements Identifiable<Long> {

    private static long nextId = 0;
    private long id = ++nextId;
    private String name;
    private String description;
    private String tags;
    private LocalDate created;
    private LocalDate modified;

    public Category(){

    }

    public Category(String name, String description, String tags){
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return id == category.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Category - ");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", tags='").append(tags).append('\'');
        sb.append(", created=").append(created);
        sb.append(", modified=").append(modified);
        sb.append(' ');
        return sb.toString();
    }
}
