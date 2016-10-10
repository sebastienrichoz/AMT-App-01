package ch.heigvd.amt.app01.service;

import javax.ejb.Singleton;

// TODO: 28.09.16 en attendant de voir quelque chose pour l'injection de dépendances
@Singleton
public class ServiceManager implements ServiceManagerLocal {

    private UserManager userManager;
    private AuthManager authManager;

    private ServiceManager() {
        userManager = new UserManager();
        authManager = new AuthManager(userManager);
    }

    public UserManager getUserManager() {
        return userManager;
    }

    public AuthManager getAuthManager() {
        return authManager;
    }
}
