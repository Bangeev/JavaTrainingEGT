package homework.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.lang.NonNull;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recipe {

    private Long id;
    @NonNull
    @NotNull
    private Long userId;
    @NonNull
    @NotNull
    @Size(max = 80)
    private String name;
    @NonNull
    @NotNull
    @Size(max = 256)
    private String shortDescription;
    @NonNull
    @NotNull
    private Integer cookingTimeMinutes;

    private String products;
    @NonNull
    @NotNull
    private String image;
    @NonNull
    @NotNull
    @Size(max = 2048)
    private String longDescription;

    private String tags;
    private LocalDateTime created = LocalDateTime.now();
    private LocalDateTime modified = LocalDateTime.now();




}
