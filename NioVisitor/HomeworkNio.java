package course.java.nio2;

import java.io.IOException;
import java.nio.file.*;
import java.util.stream.Collectors;

public class HomeworkNio {
    public static void main(String[] args) throws IOException {

        var projectSources = Paths.get("./src");
        var javaFilesSources = FileSystems.getDefault().getPathMatcher("glob:**/*.java");
        Files.walk(projectSources, FileVisitOption.FOLLOW_LINKS)
                .filter(Files::isRegularFile)
                .map(Path::toAbsolutePath)
                .filter(javaFilesSources::matches)
                .forEach(HomeworkNio::tryToParse);
    }

    private static void tryToParse(Path e) {
        System.out.println("FILE NAME ---> " + e.getFileName().toString());
        try {
            String text = Files.lines(e).filter(s -> !s.isEmpty()).collect(Collectors.joining("\n"));

            for (int position = 0; position < text.length(); ) {
                if (text.charAt(position) == '"') {
                    position = ifIsAString(text, position + 1);
                } else if (text.charAt(position) == '\'') {
                    position = ifIsACharacter(text, position + 1);
                } else if (text.charAt(position) == '/') {
                    if (isEqual(text, position, "/**") && checkForWhiteSpace(text.charAt(position + 3))) {
                        position = ifIsAJavaDocComment(text, position + 4);
                    } else if (isEqual(text, position, "/**/"))
                        position += 4; // we dont print empty comments
                    else if (isEqual(text, position, "/*")) {
                        position = ifIsABlockComment(text, position + 2);
                    } else if (isEqual(text, position, "//")) {
                        position = ifIsANormalComment(text, position + 2);
                    } else {
                        position++;
                    }

                } else {
                    position++;
                }
            }

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private static boolean isEqual(String text, int position, String match) {
        if (position + match.length() > text.length())
            return false;
        for (int i = 0; i < match.length(); i++)
            if (text.charAt(position + i) != match.charAt(i))
                return false;
        return true;
    }

    // test for white space
    private static boolean checkForWhiteSpace(char c) {
        return c == ' ' || c == '\t' || c == '\n' || c == '\r';
    }

    private static int ifIsACharacter(String fullFile, int position) {
        StringBuilder stringBuilder = new StringBuilder();
        while (fullFile.charAt(position) != '\'') {
            stringBuilder.append(fullFile.charAt(position));
            if (fullFile.charAt(position) == '\\') {
                // skip character
                position++;
                stringBuilder.append(fullFile.charAt(position));
            }
            position++;
        }

        return position + 1;
    }

    private static int ifIsAString(String fullFile, int position) {
        StringBuilder stringBuilder = new StringBuilder();
        while (fullFile.charAt(position) != '"') {
            stringBuilder.append(fullFile.charAt(position));
            if (fullFile.charAt(position) == '\\') {
                // skip character
                position++;
                stringBuilder.append(fullFile.charAt(position));
            }
            position++;
        }
        return position + 1;
    }

    private static int ifIsAJavaDocComment(String fullFile, int position) {
        StringBuilder stringBuilder = new StringBuilder();
        for (; !isEqual(fullFile, position, "*/"); position++)
            stringBuilder.append(fullFile.charAt(position));
        printResult("THIS IS A JAVADOC COMMENT", stringBuilder.toString());
        return position + 2;
    }

    private static int ifIsABlockComment(String fullFile, int position) {
        StringBuilder stringBuilder = new StringBuilder();
        for (; !isEqual(fullFile, position, "*/"); position++)
            stringBuilder.append(fullFile.charAt(position));
        printResult("THIS IS A BLOCK COMMENT", stringBuilder.toString());
        return position + 2;
    }

    private static int ifIsANormalComment(String fullFile, int position) {
        StringBuilder stringBuilder = new StringBuilder();

        while (fullFile.charAt(position) != '\n') {
            stringBuilder.append(fullFile.charAt(position));
            position++;
            if (position >= fullFile.length()) {
                break;
            }
        }
        printResult("THIS IS A NORMAL COMMENT", stringBuilder.toString());
        return position + 1;
    }

    private static void printResult(String commentIs, String stringBuilder) {
        //if we want without a star for Block and javadoc comments(*)
        String s = stringBuilder.trim().replaceAll("\\*", "");
        //if we want with star (*)
        //String s = stringBuilder.trim();

        // empty comments will not display
        if (s.length() > 0) {
            System.out.println(commentIs);
            System.out.println(s + "\r\n");

        }
    }
}
