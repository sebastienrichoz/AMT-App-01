package ch.heigvd.amt.app01.web.filters;

import ch.heigvd.amt.app01.services.AuthManagerLocal;

import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Authentication filter that check is the user is logged in to access some protected pages.
 * It also allow to refuse access to logged in users to others pages (like the login page or the registration page).
 *
 * The filter set a variable on the request defining if the user is logged in that can be accessed with the
 * requestScope from the JSP pages.
 *
 * @author Damien Rochat <damien.rochat@heig-vd.ch> & Sébastien Richoz <sebastien.richoz1@heig-vd.ch>
 */
@WebFilter(filterName = "AuthFilter", urlPatterns = "/*")
public class AuthFilter implements Filter {

    private static List<String> UNAUTHENTIFICATED_BLACKLIST = Arrays.asList("/users");
    private static List<String> AUTHENTIFICATED_BLACKLIST = Arrays.asList("/login", "/register");

    @EJB
    private AuthManagerLocal authManager;

    @Override
    public void destroy() {}

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        // Get the page of the page
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String path = request.getRequestURI().substring(request.getContextPath().length());

        // Define the isAuthenticated "global variable"
        boolean isAuthenticated = authManager.isAuthentificated(request);
        request.setAttribute("isAuthenticated", isAuthenticated);

        // Check access, redirect if unauthorized
        if (!isAuthenticated && UNAUTHENTIFICATED_BLACKLIST.contains(path)) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        else if (isAuthenticated && AUTHENTIFICATED_BLACKLIST.contains(path)) {
            response.sendRedirect(request.getContextPath() + "/users");
            return;
        }

        chain.doFilter(req, resp);
    }

    @Override
    public void init(FilterConfig config) throws ServletException {}
}
