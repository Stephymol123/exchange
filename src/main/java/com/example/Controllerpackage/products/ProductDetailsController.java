package com.example.Controllerpackage.products;

import com.example.Beanpackage.ProductBeanCls;
import com.example.Daopackage.ProductDAO;
import com.example.utils.DatabaseConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/productDetails")
public class ProductDetailsController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private ProductDAO productDAO;

    public void init() {
        productDAO = new ProductDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        if (id == null || id.isEmpty()) {
            response.sendRedirect("allProducts");
            return;
        }

        try (Connection connection = DatabaseConnection.connect()) {
            ProductBeanCls product = productDAO.getProductById(connection, Integer.parseInt(id));
            if (product == null) {
                response.sendRedirect("allProducts");
                return;
            }
            request.setAttribute("product", product);
            request.getRequestDispatcher("/product-details.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Database access error", e);
        }
    }
}
