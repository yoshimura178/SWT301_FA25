package hoang.example;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ViolationOfEncapsulationExample {
    @Getter private final String name;
    @Getter private final int age;

    public ViolationOfEncapsulationExample(String name, int age) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("name must not be blank");
        }
        if (age < 0) {
            throw new IllegalArgumentException("age must be >= 0");
        }
        this.name = name;
        this.age = age;
    }

    public void display() {
        log.info("Name: {}, Age: {}", name, age);
    }
}
