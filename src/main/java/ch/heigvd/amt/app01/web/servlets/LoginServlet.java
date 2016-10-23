package ch.heigvd.amt.app01.web.servlets;

import ch.heigvd.amt.app01.services.AuthManagerLocal;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet handling the requests for the login page.
 *
 * @author Damien Rochat <damien.rochat@heig-vd.ch> & Sébastien Richoz <sebastien.richoz1@heig-vd.ch>
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    @EJB
    private AuthManagerLocal authManager;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Get the authentication information
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Redirect to the login page if the authentication failed
        if (!authManager.authentificate(request, username, password)) {
            request.setAttribute("errorMessage", "Username or password incorrect");
            request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
            return;
        }

        // Show the protected page
        response.sendRedirect(request.getContextPath() + "/users");
    }
}
