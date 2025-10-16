package hoang.example;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

@Slf4j
public class ResourceLeakExample {
    public static void main(String[] args) {
        Path path = Path.of("data.txt");
        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            String line;
            while ((line = reader.readLine()) != null) {
                log.debug("{}", line);
            }
        } catch (IOException e) {
            log.error("I/O error while reading {}", path, e);
        }
    }
}
