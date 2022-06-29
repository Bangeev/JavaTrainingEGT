package homework.model;

import homework.enums.Gender;
import homework.enums.Role;
import homework.enums.Status;
import lombok.*;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.mapper.reflect.ColumnName;
import org.jdbi.v3.core.statement.StatementContext;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.springframework.lang.NonNull;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Builder

public class User {

@ColumnName("id")
    private Long id;
    @NonNull
    @NotNull
    @ColumnName("name")
    private String name;
    @NonNull
    @NotNull
    @Size(max = 15)
    @ColumnName("username")
    private String username;
    @NonNull
    @NotNull
    @Pattern(regexp = "^.*(?=.{8,15})(?=.*[a-zA-Z])(?=.*\\d)(?=.*[!#$%&?+]).*$",
            message = "Password should contain at least 8 characters, at least one digit, capital letter, and none-letter character")
    @ColumnName("password")
    private String password;
    @ColumnName("gender")
    private Gender gender;
    @NotNull
    @NonNull
    @ColumnName("role")
    private Role role;
    @ColumnName("url")
    private String url;
    @Size(max = 512)
    @ColumnName("shortDescription")
    private String shortDescription;
    @NotNull
    @NonNull
    @ColumnName("status")
    private Status status;
    @ColumnName("created")
    private LocalDateTime created = LocalDateTime.now();
    @ColumnName("modified")
    private LocalDateTime modified = LocalDateTime.now();

    public User(@NonNull String name, @NonNull String username, @NonNull String password, Gender gender, @NonNull Role role, String url, String shortDescription, @NonNull Status status, LocalDateTime created, LocalDateTime modified) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.role = role;
        this.url = url;
        this.shortDescription = shortDescription;
        this.status = status;
        this.created = created;
        this.modified = modified;
    }

    public static class UserMapper implements RowMapper<User> {

        @Override
        public User map(ResultSet rs, StatementContext ctx) throws SQLException {
            return User.builder()

                    .name(rs.getString("name"))
                    .username(rs.getString("username"))
                    .password(rs.getString("password"))
                    .gender(Gender.valueOf(rs.getString("gender")))
                    .role(Role.valueOf(rs.getString("role")))
                    .url(rs.getString("url"))
                    .shortDescription(rs.getString("shortDescription"))
                    .status(Status.valueOf(rs.getString("status")))

                    .build();
        }


    }
}
