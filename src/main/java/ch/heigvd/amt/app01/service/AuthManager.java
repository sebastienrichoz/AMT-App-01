package ch.heigvd.amt.app01.service;

import ch.heigvd.amt.app01.model.User;

import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Stateless
public class AuthManager implements AuthManagerLocal {

    private UserManager manager;

    public AuthManager(){
        this.manager = null;
    }

    public AuthManager(UserManager manager) {
        this.manager = manager;
    }

    public boolean authentificate(HttpServletRequest request, String username, String password) {
        User user = manager.findByUsernameAndPassword(username, password);
        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("authUsername", user.getUsername());
            return true;
        }
        return false;
    }

    public boolean isAuthentificated(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return manager.findByUsername((String) session.getAttribute("authUsername")) != null;
    }

    public void logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("authUsername");
    }

    public User getUser(HttpServletRequest request) {
        if (!isAuthentificated(request)) {
            throw new RuntimeException("No logged user");
        }
        HttpSession session = request.getSession();
        return manager.findByUsername((String) session.getAttribute("authUsername"));
    }
}
