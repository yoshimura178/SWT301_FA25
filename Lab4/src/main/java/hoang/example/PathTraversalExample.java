package hoang.example;

import lombok.extern.slf4j.Slf4j;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;

@Slf4j
public class PathTraversalExample {
    public static void main(String[] args) {
        String userInput = "../secret.txt";
        Path path = Paths.get(userInput);

        if (Files.exists(path)) {
            try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
                log.info("Reading file: {}", path);
                reader.reset();
            } catch (IOException e) {
                log.error("I/O error when reading {}", path, e);
            }
        } else {
            log.warn("File not found: {}", path);
        }
    }
}
