package ch.heigvd.amt.app01.service;

import ch.heigvd.amt.app01.model.User;

import javax.ejb.Local;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author Sébastien Richoz
 * @version 1.0
 * @date 05.10.2016
 */
@Local
public interface AuthManagerLocal {
    boolean authentificate(HttpServletRequest request, String username, String password);
    boolean isAuthentificated(HttpServletRequest request);
    void logout(HttpServletRequest request);
    User getUser(HttpServletRequest request);
}
