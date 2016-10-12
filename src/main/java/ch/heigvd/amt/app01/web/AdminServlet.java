package ch.heigvd.amt.app01.web;

import ch.heigvd.amt.app01.services.UserManagerLocal;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AdminServlet", urlPatterns = {"/admin"})
public class AdminServlet extends HttpServlet {

    @EJB
    private UserManagerLocal userManager;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("users", userManager.findAll());
        request.getRequestDispatcher("/WEB-INF/pages/admin.jsp").forward(request, response);
    }
}
