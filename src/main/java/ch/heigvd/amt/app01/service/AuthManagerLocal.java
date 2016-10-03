package ch.heigvd.amt.app01.service;

import ch.heigvd.amt.app01.model.User;

import javax.ejb.Local;
import javax.servlet.http.HttpServletRequest;

@Local
public interface AuthManagerLocal {

    boolean authentificate(HttpServletRequest request, String username, String password);

    boolean isAuthentificated(HttpServletRequest request);

    void logout(HttpServletRequest request);

    User getUser(HttpServletRequest request);

}
