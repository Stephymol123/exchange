package com.example.Controllerpackage;

import com.example.Daopackage.Daocls;
import com.example.Beanpackage.Beancls;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/")
public class Controllercls extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private Daocls userDao;

    public void init() {
        userDao = new Daocls();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            switch (action) {
                case "register":
                    registerUser(request, response);
                    break;
                case "login":
                    loginUser(request, response);
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void registerUser(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        int phnno = Integer.parseInt(request.getParameter("phnno"));
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        Beancls beancls = new Beancls();
        beancls.setFirstname(firstname);
        beancls.setLastname(lastname);
        beancls.setPhnno(phnno);
        beancls.setUsername(username);
        beancls.setPassword(password);
        beancls.setEmail(email);

        userDao.registerUser(beancls);
        response.sendRedirect("login.jsp");
    }

    private void loginUser(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Beancls beancls = userDao.validateUser(username, password);
        if (beancls!= null) {
            request.setAttribute("user", beancls);
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } else {
            request.setAttribute("message", "Invalid username or password");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
