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
    long saveUser(User user);
    long updateUser(long id, User user);
    User findById(long id);
    User findByUsername(String username);
    User findByEmail(String email);
    User findByUsernameAndEmail(String username, String email);
    User findByUsernameAndPassword(String username, String password);
    void deleteById(long id);
    Map<Long, User> getUsers();
}
