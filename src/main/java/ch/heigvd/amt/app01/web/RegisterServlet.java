package ch.heigvd.amt.app01.web;

import ch.heigvd.amt.app01.models.User;
import ch.heigvd.amt.app01.services.AuthManagerLocal;
import ch.heigvd.amt.app01.services.UserManagerLocal;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

@WebServlet(name = "RegisterServlet", urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet {

    @EJB
    private UserManagerLocal userManager;

    @EJB
    private AuthManagerLocal authManager;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String passwordCtrl = request.getParameter("passwordCtrl");

        // TODO: 12.10.16 VerificationService ?

        if (username.isEmpty() || password.isEmpty()) {
            request.setAttribute("errorMessage", "Provide at least an username and a password");
            request.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(request, response);
            return;
        }

        if (!password.equals(passwordCtrl)) {
            request.setAttribute("errorMessage", "The two passwords must be identical");
            request.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(request, response);
            return;
        }

        if (userManager.findByUsername(username) != null) {
            request.setAttribute("errorMessage", "This username is not available");
            request.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(request, response);
            return;
        }

        User user = new User(firstname, lastname, email, username, password);
        userManager.save(user);
        authManager.authentificate(request, username, password);
        response.sendRedirect(request.getContextPath() + "/login");
    }
}
