package hoang.example;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OvercatchingExceptionExample {
    public static void main(String[] args) {
        try {
            int[] arr = new int[5];
            log.info("Value: {}", arr[10]);
        } catch (ArrayIndexOutOfBoundsException e) {
            log.error("Index out of bounds while accessing array", e);
        }
    }
}
