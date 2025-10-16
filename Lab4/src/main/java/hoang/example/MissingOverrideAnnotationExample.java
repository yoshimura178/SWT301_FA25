package hoang.example;

import java.util.logging.Level;
import java.util.logging.Logger;

class Animal {
    private static final Logger LOG = Logger.getLogger(Animal.class.getName());
    void speak() {
        LOG.log(Level.INFO, "Animal speaks");
    }
}

class Dog extends Animal {
    private static final Logger LOG = Logger.getLogger(Dog.class.getName());
    @Override
    void speak() {
        LOG.log(Level.INFO, "Dog barks");
    }
}
