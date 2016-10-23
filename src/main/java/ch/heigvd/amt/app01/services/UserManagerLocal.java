package ch.heigvd.amt.app01.services;

import ch.heigvd.amt.app01.models.User;

import javax.ejb.Local;
import java.util.List;

/**
 * Interface to create an user manager for creating/updating/reading/deleting users.
 *
 * Permit also to use the UserManager as a managed service.
 *
 * @author Damien Rochat <damien.rochat@heig-vd.ch> & Sébastien Richoz <sebastien.richoz1@heig-vd.ch>
 */
@Local
public interface UserManagerLocal {

    /**
     * Persist an user entity.
     * Can be used for creating or updating an user.
     *
     * @param user the user to save.
     */
    void save(User user);

    /**
     * Delete an user.
     *
     * @param user the user to delete.
     */
    void delete(User user);

    /**
     * Find all the saved user.
     *
     * @return the list of user entity.
     */
    List<User> findAll();

    /**
     * Find an user by his ID.
     *
     * @param id the id of the user to find.
     * @return the user entity or null of there was no result.
     */
    User findById(long id);

    /**
     * Find an user by his username.
     *
     * @param username the username of the user to find.
     * @return the user entity or null of there was no result.
     */
    User findByUsername(String username);

    User findByUsernameAndPassword(String username, String password); // TODO: 23.10.16 remove me
}
