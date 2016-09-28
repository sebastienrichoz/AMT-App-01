package ch.heigvd.amt.app01.service;

import ch.heigvd.amt.app01.model.User;

import java.util.HashMap;
import java.util.Map;

public class UserManager {

    private Map<String, User> users = new HashMap<String, User>();

    public void saveUser(User user) {
        if (users.get(user.getUsername()) != null) {
            throw new RuntimeException("This username already exists");
        }
        users.put(user.getUsername(), user);
    }

    public User findByUsername(String username) {
        return users.get(username);
    }

    public User findByUsernameAndPassword(String username, String password) {
        User user = users.get(username);
        if (user != null) {
           if (user.getPassword().equals(password)) {
               return user;
           }
        }
        return null;
    }
}
