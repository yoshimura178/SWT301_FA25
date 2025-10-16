package hoang.example;

import javax.security.sasl.AuthenticationException;

@FunctionalInterface
public interface InterfaceNamingInconsistencyExample {
    boolean login(String username, char[] password) throws AuthenticationException;
}
