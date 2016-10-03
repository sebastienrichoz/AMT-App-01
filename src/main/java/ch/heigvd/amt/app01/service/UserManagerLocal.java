package ch.heigvd.amt.app01.service;

import ch.heigvd.amt.app01.model.User;

import javax.ejb.Local;

@Local
public interface UserManagerLocal {

    void saveUser(User user);

    User findByUsername(String username);

    User findByUsernameAndPassword(String username, String password);

}
