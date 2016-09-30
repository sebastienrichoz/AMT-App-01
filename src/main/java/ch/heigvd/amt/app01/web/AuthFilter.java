package ch.heigvd.amt.app01.web;

import ch.heigvd.amt.app01.service.AuthManager;
import ch.heigvd.amt.app01.service.ServiceManager;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthFilter implements Filter {

    public void destroy() {}

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        AuthManager manager = ServiceManager.getInstance().getAuthManager();
        if (!manager.isAuthentificated(request)) {
            response.sendRedirect(request.getContextPath() + "/login");
        }
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {}
}
