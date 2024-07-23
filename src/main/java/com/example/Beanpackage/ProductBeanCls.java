package com.example.Beanpackage;

public class ProductBeanCls {
    private int id;
    private int user_id;
    private int category_id;
    private String pname;
    private double price;
    private String description;
    private int quantity;
    private String pimages; // Comma-separated image URLs
    private String firstImageUrl; // First image URL
    private String categoryName; // Category name

    // Getters and setters for all fields
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getPimages() {
        return pimages;
    }

    public void setPimages(String pimages) {
        if (pimages != null && !pimages.isEmpty()) {
            this.pimages = pimages;
            String[] urls = pimages.split(",");
            this.firstImageUrl = urls[0].replace("\"", "").trim();
        } else {
            this.pimages = null;
            this.firstImageUrl = null;
        }
    }

    public String getFirstImageUrl() {
        return firstImageUrl;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
