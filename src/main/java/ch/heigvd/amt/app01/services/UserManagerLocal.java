package ch.heigvd.amt.app01.services;

import ch.heigvd.amt.app01.models.User;

import javax.ejb.Local;
import java.util.List;

@Local
public interface UserManagerLocal {

    void save(User user);

    void delete(User user);

    List<User> findAll();

    User findById(long id);

    User findByUsername(String username);

    User findByUsernameAndPassword(String username, String password);

}
