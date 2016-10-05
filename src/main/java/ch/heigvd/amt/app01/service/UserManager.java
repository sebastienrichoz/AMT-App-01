package ch.heigvd.amt.app01.service;

import ch.heigvd.amt.app01.model.User;

import javax.ejb.Singleton;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Singleton
public class UserManager implements UserManagerLocal {

    private Map<String, User> users = new HashMap<String, User>();

    public void saveUser(User user) {
        if (users.get(user.getUsername()) != null) {
            throw new RuntimeException("This username already exists");
        }
        users.put(user.getUsername(), user);
    }

    public ArrayList<User> findAll() {
        return new ArrayList<User>(users.values());
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
