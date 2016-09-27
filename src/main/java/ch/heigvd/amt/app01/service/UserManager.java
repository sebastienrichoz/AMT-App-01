package ch.heigvd.amt.app01.service;

import ch.heigvd.amt.app01.model.User;

import java.util.LinkedList;
import java.util.List;

public class UserManager {

    private static UserManager instance;
    private List<User> users = new LinkedList<User>();

    private UserManager() {}

    public static UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
        }
        return instance;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public User getUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public boolean authentificate(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                // TODO: 27.09.16 créer une session
                return true;
            }
        }
        return false;
    }

    public boolean isAuthentificated() {
        // TODO: 27.09.16 checker si une session existe et si elle est valide
        return false;
    }
}
