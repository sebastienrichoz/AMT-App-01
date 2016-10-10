package ch.heigvd.amt.app01.web;

import ch.heigvd.amt.app01.service.AuthManager;
import ch.heigvd.amt.app01.service.ServiceManagerLocal;

import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Sébastien Richoz
 * @version 1.0
 * @date 05.10.2016
 */
@WebFilter(filterName = "LoginFilter", urlPatterns = "/login")
public class LoginFilter implements Filter {

    @EJB
    ServiceManagerLocal serviceManager;

    public void destroy() {}

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        AuthManager manager = serviceManager.getAuthManager();
        if (manager.isAuthentificated(request)) {
            response.sendRedirect(request.getContextPath() + "/admin");
        }

        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {}
}
