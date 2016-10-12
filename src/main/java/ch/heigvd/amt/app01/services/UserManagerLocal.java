package ch.heigvd.amt.app01.services;

import ch.heigvd.amt.app01.models.User;

import javax.ejb.Local;
import java.util.ArrayList;

@Local
public interface UserManagerLocal {

    void saveUser(User user);

    ArrayList<User> findAll();

    User findByUsername(String username);

    User findByUsernameAndPassword(String username, String password);

}
