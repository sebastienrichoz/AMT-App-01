package ch.heigvd.amt.app01.service;

import ch.heigvd.amt.app01.model.User;

import javax.ejb.Stateless;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Stateless
public class UserManager implements UserManagerLocal {

    private Map<Integer, User> users = new HashMap<Integer, User>();
    private int id = 0;

    public void saveUser(User user) {
        if (users.get(user.getId()) != null) {
            throw new RuntimeException("This username already exists");
        }
        user.setId(id++);
        users.put(user.getId(), user);
    }

    public User findById(int id) {
        return users.get(id);
    }

    @Override
    public User findByUsername(String username) {
        return users.entrySet().stream()
                .filter(u -> username.equals(u.getValue().getUsername()))
                .map(map -> map.getValue())
                .findFirst()
                .get();
    }

    public User findByUsernameAndPassword(String username, String password) {
        return users.entrySet().stream()
                .filter(u -> username.equals(u.getValue().getUsername()) && username.equals(u.getValue().getPassword()))
                .map(map -> map.getValue())
                .findFirst()
                .get();
    }

    @Override
    public void deleteById(int id) {
        users.remove(id);
    }

    @Override
    public Map<Integer, User> getUsers() {
        return new HashMap<Integer, User>(users);
    }
}
