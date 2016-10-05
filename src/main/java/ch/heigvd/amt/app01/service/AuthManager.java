package ch.heigvd.amt.app01.service;

import ch.heigvd.amt.app01.model.User;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Singleton
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
            throw new RuntimeException("No logged user");
        }
        HttpSession session = request.getSession();
        return userManager.findByUsername((String) session.getAttribute(SESSION_ATTR_USERNAME));
    }
}
