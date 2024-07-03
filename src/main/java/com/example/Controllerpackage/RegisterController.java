package com.example.Controllerpackage;

import com.example.Daopackage.Daocls;
import com.example.Beanpackage.Beancls;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);

    private Daocls userDao;

    public void init() {
        userDao = new Daocls();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        // Create Beancls object and set properties
        Beancls beancls = new Beancls();
        beancls.setFirstname(firstname);
        beancls.setLastname(lastname);
        beancls.setUsername(username);
        beancls.setPassword(password);
        beancls.setEmail(email);

        // Call DAO method to register user
        try {
            userDao.registerUser(beancls);
            // Redirect to login page after successful registration
            response.sendRedirect("login.jsp");
        } catch (Exception e) {
            // Handle registration failure
            logger.error("Error registering user", e);
            throw new ServletException("Error registering user", e);
        }
    }
}
