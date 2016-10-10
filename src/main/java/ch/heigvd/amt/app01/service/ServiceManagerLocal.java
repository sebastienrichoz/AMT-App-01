package ch.heigvd.amt.app01.service;

import javax.ejb.Local;

/**
 * @author Sébastien Richoz
 * @version 1.0
 * @date 05.10.2016
 */
@Local
public interface ServiceManagerLocal {
    UserManager getUserManager();
    AuthManager getAuthManager();
}
