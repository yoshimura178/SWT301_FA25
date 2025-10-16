package hoang.example;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NullPointerExample {
    public static void main(String[] args) {
        String text = null;

        String safe = (text == null) ? "" : text;

        if (!safe.isEmpty()) {
            log.info("Text is not empty");
        } else {
            log.debug("Text is null or empty");
        }
    }
}
