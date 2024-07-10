package com.example.Controllerpackage.products;

import com.example.Beanpackage.CategoryBeanCls;
import com.example.Daopackage.CategoryDAO;
import com.example.Beanpackage.ProductBeanCls;
import com.example.Daopackage.ProductDAO;
import com.example.Beanpackage.Beancls;
import com.example.utils.DatabaseConnection;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Acl;
import com.google.firebase.cloud.StorageClient;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@WebServlet("/addProduct")
public class AddProductController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private ProductDAO productDAO;

    public void init() {
        productDAO = new ProductDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");
        } else {
            addProduct(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");
        } else {
            CategoryDAO categoryDAO = new CategoryDAO();
            List<CategoryBeanCls> categories = categoryDAO.selectAllCategories();
            request.setAttribute("categories", categories);
            request.getRequestDispatcher("/user-add-item.jsp").forward(request, response);
        }
    }

    private void addProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Beancls user = (Beancls) session.getAttribute("user");
        int userId = user.getId();

        String pname = null;
        double price = 0;
        String description = null;
        int quantity = 0;
        int categoryId = 0;
        List<String> imageUrls = new ArrayList<>();

        if (ServletFileUpload.isMultipartContent(request)) {
            try {
                FileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload upload = new ServletFileUpload(factory);
                List<FileItem> items = upload.parseRequest(request);

                for (FileItem item : items) {
                    if (item.isFormField()) {
                        String fieldName = item.getFieldName();
                        String fieldValue = item.getString();

                        switch (fieldName) {
                            case "pname":
                                pname = fieldValue;
                                break;
                            case "price":
                                price = Double.parseDouble(fieldValue);
                                break;
                            case "description":
                                description = fieldValue;
                                break;
                            case "quantity":
                                quantity = Integer.parseInt(fieldValue);
                                break;
                            case "category_id":
                                categoryId = Integer.parseInt(fieldValue);
                                break;
                        }
                    } else {
                        InputStream fileContent = item.getInputStream();
                        String fileName = UUID.randomUUID().toString() + "-" + item.getName();

                        Bucket bucket = StorageClient.getInstance().bucket();
                        Blob blob = bucket.create(fileName, fileContent, item.getContentType());
                        blob.createAcl(Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER));
                        String imageUrl = "https://storage.googleapis.com/" + bucket.getName() + "/" + fileName;

                        imageUrls.add("\"" + imageUrl + "\"");
                    }
                }

                ProductBeanCls newProduct = new ProductBeanCls();
                newProduct.setUser_id(userId);
                newProduct.setCategory_id(categoryId);
                newProduct.setPname(pname);
                newProduct.setPrice(price);
                newProduct.setDescription(description);
                newProduct.setQuantity(quantity);
                newProduct.setPimages(String.join(",", imageUrls));

                try (Connection connection = DatabaseConnection.connect()) {
                    productDAO.insertProduct(connection, newProduct);
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new ServletException("Database error: " + e.getMessage());
                }

                response.sendRedirect("user-home.jsp");

            } catch (Exception e) {
                e.printStackTrace();
                throw new ServletException("File upload error: " + e.getMessage());
            }
        } else {
            throw new ServletException("Form must be multipart/form-data");
        }
    }
}
