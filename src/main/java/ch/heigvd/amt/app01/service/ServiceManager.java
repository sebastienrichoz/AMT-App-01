package ch.heigvd.amt.app01.service;

// TODO: 28.09.16 en attendant de voir quelque chose pour l'injection de dépendances
public class ServiceManager {

    private static ServiceManager instance;

    private UserManager userManager;
    private AuthManager authManager;

    private ServiceManager() {
        userManager = new UserManager();
        authManager = new AuthManager(userManager);
    }

    public static ServiceManager getInstance() {
        if (instance == null) {
            instance = new ServiceManager();
        }
        return instance;
    }

    public UserManager getUserManager() {
        return userManager;
    }

    public AuthManager getAuthManager() {
        return authManager;
    }
}
