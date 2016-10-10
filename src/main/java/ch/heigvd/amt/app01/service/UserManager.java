package ch.heigvd.amt.app01.service;

import ch.heigvd.amt.app01.model.User;
import com.sun.org.apache.xpath.internal.FoundIndex;

import javax.ejb.Stateless;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Stateless
public class UserManager implements UserManagerLocal {

    private Map<Long, User> users = new HashMap<Long, User>();
    private long id = 0;

    public long saveUser(User user) {
        if (findByEmail(user.getEmail()) != null) {
            throw new RuntimeException("This email is already registered");
        }
        if (findByUsername(user.getUsername()) != null) {
            throw new RuntimeException("This username is already registered");
        }

        users.put(++id, user);
        return id;
    }

    @Override
    public long updateUser(long id, User user) {
        if (findByEmail(user.getEmail()) != null) {
            throw new RuntimeException("This email is already registered");
        }
        if (findByUsername(user.getUsername()) != null) {
            throw new RuntimeException("This username is already registered");
        }

        findById(id).update(user);
        return id;
    }

    public User findById(long id) {
        return users.get(id);
    }

    @Override
    public User findByUsername(String username) {
        for (Map.Entry<Long, User> userEntry : users.entrySet()) {
            User user = userEntry.getValue();
            if (user.getUsername().equals(username))
                return user;
        }

        // No user found
        return null;
    }

    @Override
    public User findByEmail(String email) {
        for (Map.Entry<Long, User> userEntry : users.entrySet()) {
            User user = userEntry.getValue();
            if (user.getEmail().equals(email))
                return user;
        }

        // No user found
        return null;
    }

    @Override
    public User findByUsernameAndEmail(String username, String email) {
        for (Map.Entry<Long, User> userEntry : users.entrySet()) {
            User user = userEntry.getValue();
            if (user.getUsername().equals(username)
                    && user.getEmail().equals(email))
                return user;
        }

        // No user Found
        return null;
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        for (Map.Entry<Long, User> userEntry : users.entrySet()) {
            User user = userEntry.getValue();
            if (user.getUsername().equals(username)
                    && user.getPassword().equals(password))
                return user;
        }

        // No user found
        return null;
    }

    @Override
    public void deleteById(long id) {
        users.remove(id);
    }

    @Override
    public Map<Long, User> getUsers() {
        return new HashMap<Long, User>(users);
    }
}
