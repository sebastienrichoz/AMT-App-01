package ch.heigvd.amt.app01.services;

import ch.heigvd.amt.app01.models.User;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

// TODO: 18.10.16 Etre indépendant de HttpServletRequest

@Stateless
public class AuthManager implements AuthManagerLocal {

    @EJB
    private UserManagerLocal userManager;

    private static final String SESSION_ATTR_USERNAME = "authUsername";

    public boolean authentificate(HttpServletRequest request, String username, String password) {
        User user = userManager.findByUsernameAndPassword(username, password);
        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute(SESSION_ATTR_USERNAME, user.getUsername());
            return true;
        }
        return false;
    }

    public boolean isAuthentificated(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return userManager.findByUsername((String) session.getAttribute(SESSION_ATTR_USERNAME)) != null;
    }

    public void logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute(SESSION_ATTR_USERNAME);
    }

    public User getUser(HttpServletRequest request) {
        if (!isAuthentificated(request)) {
            throw new RuntimeException("No logged user"); // TODO: 12.10.16
        }
        HttpSession session = request.getSession();
        return userManager.findByUsername((String) session.getAttribute(SESSION_ATTR_USERNAME));
    }
}
