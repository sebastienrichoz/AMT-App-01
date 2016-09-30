package ch.heigvd.amt.app01.web;

import ch.heigvd.amt.app01.service.ServiceManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutServlet extends HttpServlet {

    private ServiceManager serviceManager = ServiceManager.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        serviceManager.getAuthManager().logout(request);
        response.sendRedirect("home");
    }
}
