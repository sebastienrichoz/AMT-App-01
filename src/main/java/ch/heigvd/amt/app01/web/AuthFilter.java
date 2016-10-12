package ch.heigvd.amt.app01.web;

import ch.heigvd.amt.app01.services.AuthManagerLocal;

import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "AuthFilter", urlPatterns = "/*")
public class AuthFilter implements Filter {

    @EJB
    private AuthManagerLocal authManager;

    public void destroy() {}

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        String path = request.getRequestURI().substring(request.getContextPath().length());
        boolean authentificated = authManager.isAuthentificated(request);

        if (authentificated && (path.equals("/login") || path.equals("/register"))) {
            response.sendRedirect(request.getContextPath() + "/admin");
            return;
        }
        else if (!authentificated && path.equals("/admin")) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {}
}
