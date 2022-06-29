package homework.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class File {
    private Long id;
    private String fileName;
    private String version;
    private String fileContent;


    public File(String fileName, String version, String fileContent) {
        this.fileName = fileName;
        this.version = version;
        this.fileContent = fileContent;
    }
}
