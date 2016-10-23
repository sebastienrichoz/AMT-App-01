package ch.heigvd.amt.app01.web;

import ch.heigvd.amt.app01.services.AuthManagerLocal;

import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebFilter(filterName = "AuthFilter", urlPatterns = "/*")
public class AuthFilter implements Filter {

    private static List<String> UNAUTHENTIFICATED_BLACKLIST = Arrays.asList("/users");
    private static List<String> AUTHENTIFICATED_BLACKLIST = Arrays.asList("/login", "/register");

    @EJB
    private AuthManagerLocal authManager;

    public void destroy() {}

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        String path = request.getRequestURI().substring(request.getContextPath().length());
        boolean isAuthentificated = authManager.isAuthentificated(request);
        request.setAttribute("isAuthentificated", isAuthentificated);

        if (!isAuthentificated && UNAUTHENTIFICATED_BLACKLIST.contains(path)) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        else if (isAuthentificated && AUTHENTIFICATED_BLACKLIST.contains(path)) {
            response.sendRedirect(request.getContextPath() + "/users");
            return;
        }

        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {}
}
