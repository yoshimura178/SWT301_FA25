package hoang.example;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UnreachableCodeExample {
    public static int getNumber() {
        log.debug("Computing number...");
        return 42;
    }

    public static void main(String[] args) {
        log.info("Number: {}", getNumber());
    }
}
