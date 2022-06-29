package course.java.jaxb.user;


import course.java.enums.Gender;
import course.java.enums.Role;
import course.java.enums.Status;
import course.java.jaxb.LocalDateAdapter;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDateTime;
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "user", propOrder = {"id", "name", "username", "password", "gender", "role", "profilePhoto",
                            "description", "status", "created", "modified"})
public class User {
    @XmlAttribute
    @XmlSchemaType(name = "positiveInteger")
    private Long id;
    @XmlElement(name = "name", required = true)
    private String name;
    @XmlElement(name = "username", required = true)
    private String username;
    @XmlElement(name = "password", required = true)
    private String password;
    @XmlElement(name = "gender", required = true)
    private Gender gender;
    @XmlElement(name = "role", required = true)
    private Role role;
    @XmlElement(name = "profilePhoto", required = true)
    private String profilePhoto;
    @XmlElement(name = "description", required = true)
    private String description;
    @XmlElement(name = "status", required = true)
    private Status status;
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDateTime created;
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDateTime modified;

    public User() {
    }

    public User(Long id, String name, String username, String password, Gender gender, Role role,
                String profilePhoto, String description, Status status, LocalDateTime created, LocalDateTime modified) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.role = role;
        this.profilePhoto = profilePhoto;
        this.description = description;
        this.status = status;
        this.created = created;
        this.modified = modified;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getModified() {
        return modified;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("\n");
        sb.append("id: ").append(id).append("\n");
        sb.append("name: ").append(name).append("\n");
        sb.append("username: ").append(username).append("\n");
        sb.append("password: ").append(password).append("\n");
        sb.append("gender: ").append(gender).append("\n");
        sb.append("role:").append(role).append("\n");
        sb.append("profilePhoto: ").append(profilePhoto).append("\n");
        sb.append("description: ").append(description).append("\n");
        sb.append("status: ").append(status).append("\n");
        sb.append("created:").append(created).append("\n");
        sb.append("modified: ").append(modified).append("\n");
        return sb.toString();
    }
}
