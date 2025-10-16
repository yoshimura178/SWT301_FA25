package hoang.example;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CatchGenericExceptionExample {
    public static void main(String[] args) {
        try {
            String s = null;
            log.info("Length: {}", s.length());
        } catch (NullPointerException e) {
            log.error("Input string is null", e);
        }
    }
}
