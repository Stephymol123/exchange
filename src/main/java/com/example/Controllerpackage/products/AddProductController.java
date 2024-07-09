package com.example.Controllerpackage.products;

import com.example.Beanpackage.category.CategoryBeanCls;
import com.example.Beanpackage.category.CategoryDAO;
import com.example.Beanpackage.product.ProductBeanCls;
import com.example.Beanpackage.product.ProductDAO;
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

@WebServlet("/addProduct")
public class AddProductController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private ProductDAO productDAO;

    public void init() {
        productDAO = new ProductDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        addProduct(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CategoryDAO categoryDAO = new CategoryDAO(); // Instantiate CategoryDAO
        List<CategoryBeanCls> categories = categoryDAO.selectAllCategories(); // Fetch all categories

        request.setAttribute("categories", categories); // Set categories as request attribute

        // Forward to user-add-item.jsp
        request.getRequestDispatcher("/user-add-item.jsp").forward(request, response);

    }

    private void addProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve form parameters
        int userId = Integer.parseInt(request.getParameter("user_id"));
        int categoryId = Integer.parseInt(request.getParameter("category_id"));
        String pname = request.getParameter("pname");
        double price = Double.parseDouble(request.getParameter("price"));
        String description = request.getParameter("description");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String pimage = request.getParameter("pimage");

        // Create a new ProductBeanCls object
        ProductBeanCls newProduct = new ProductBeanCls();
        newProduct.setUser_id(userId);
        newProduct.setCategory_id(categoryId);
        newProduct.setPname(pname);
        newProduct.setPrice(price);
        newProduct.setDescription(description);
        newProduct.setQuantity(quantity);
        newProduct.setPimage(pimage);

        Connection connection = null;
        try {
            // Establish database connection
            connection = DatabaseConnection.connect();

            // Insert the new product into the database
            productDAO.insertProduct(connection, newProduct);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("Database error: " + e.getMessage()); // Forwarding the SQLException
        } finally {
            // Close the database connection
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        // Redirect to a confirmation page or the list of products
        response.sendRedirect("user-home.jsp");
    }
}
