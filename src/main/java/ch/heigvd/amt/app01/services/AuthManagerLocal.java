package ch.heigvd.amt.app01.services;

import ch.heigvd.amt.app01.models.User;

import javax.ejb.Local;
import javax.servlet.http.HttpServletRequest;

/**
 * Interface to manage users authentication.
 *
 * Permit also to use the AuthManager as a managed service.
 *
 * @author Damien Rochat <damien.rochat@heig-vd.ch> & Sébastien Richoz <sebastien.richoz1@heig-vd.ch>
 */
@Local
public interface AuthManagerLocal {

    /**
     * Try to authenticate an user.
     *
     * @param request the request context.
     * @param username the username for the connection attempt.
     * @param password the password for the connection attempt.
     * @return true if the authentication was successful or wrong if not.
     */
    boolean authentificate(HttpServletRequest request, String username, String password);

    /**
     * Method allowing to quickly check if the current visitor is authenticated.
     *
     * @param request the request scope.
     * @return true if user is authenticate or wrong if not.
     */
    boolean isAuthentificated(HttpServletRequest request);

    /**
     * Deauthenticate the current visitor.
     *
     * @param request the request scope.
     */
    void logout(HttpServletRequest request);

    /**
     * Get the currently authenticate user.
     *
     * @param request the request scope.
     * @return the user entity representing the connected user.
     */
    User getUser(HttpServletRequest request);
}
