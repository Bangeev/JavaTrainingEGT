package course.java.jaxb.user;

import course.java.enums.Gender;
import course.java.enums.Role;
import course.java.enums.Status;

import javax.xml.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "userList", propOrder = "user")
public class UserList {
    @XmlElement(name = "user")
    private List<User> user = new ArrayList<User>();

    public UserList() {
    }



    public void setUsers(List<User> users) {
        this.user = users;
    }

    public List<User> getUsers() {
        if (user == null) {
            user = new ArrayList<User>();
        }
        return this.user;
    }
}
