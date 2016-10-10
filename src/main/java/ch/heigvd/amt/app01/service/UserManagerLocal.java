package ch.heigvd.amt.app01.service;

import ch.heigvd.amt.app01.model.User;

import javax.ejb.Local;
import java.util.List;
import java.util.Map;

/**
 * @author Sébastien Richoz
 * @version 1.0
 * @date 05.10.2016
 */
@Local
public interface UserManagerLocal {
    void saveUser(User user);
    User findById(int id);
    User findByUsername(String username);
    User findByUsernameAndPassword(String username, String password);
    void deleteById(int id);
    Map<Integer, User> getUsers();
}
