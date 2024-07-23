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
import java.util.List;

@WebServlet("/allProducts")
public class AllProductsController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private ProductDAO productDAO;

    public void init() {
        productDAO = new ProductDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (Connection connection = DatabaseConnection.connect()) {
            List<ProductBeanCls> products = productDAO.getAllProducts(connection);
            request.setAttribute("products", products);
            request.getRequestDispatcher("/all-products.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Database access error", e);
        }
    }
}
