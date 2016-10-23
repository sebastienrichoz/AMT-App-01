package ch.heigvd.amt.app01.services;

import ch.heigvd.amt.app01.models.User;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Implementation of the AuthManager interface.
 *
 * Can be used as a stateless managed service.
 *
 * @author Damien Rochat <damien.rochat@heig-vd.ch> & Sébastien Richoz <sebastien.richoz1@heig-vd.ch>
 */
@Stateless
public class AuthManager implements AuthManagerLocal {

    @EJB
    private UserManagerLocal userManager;

    private static final String SESSION_ATTR_USERNAME = "authUsername";

    @Override
    public boolean authentificate(HttpServletRequest request, String username, String password) {
        User user = userManager.findByUsernameAndPassword(username, password);
        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute(SESSION_ATTR_USERNAME, user.getUsername());
            return true;
        }
        return false;
    }

    @Override
    public boolean isAuthentificated(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return userManager.findByUsername((String) session.getAttribute(SESSION_ATTR_USERNAME)) != null;
    }

    @Override
    public void logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute(SESSION_ATTR_USERNAME);
    }

    @Override
    public User getUser(HttpServletRequest request) {
        if (!isAuthentificated(request)) {
            throw new RuntimeException("No logged user");
        }
        HttpSession session = request.getSession();
        return userManager.findByUsername((String) session.getAttribute(SESSION_ATTR_USERNAME));
    }
}
