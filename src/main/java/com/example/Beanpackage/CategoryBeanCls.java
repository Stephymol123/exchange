package com.example.Beanpackage;

import java.io.Serializable;

public class CategoryBeanCls implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String category;

    // No-argument constructor
    public CategoryBeanCls() {
    }

    // Constructor with parameters
    public CategoryBeanCls(int id, String category) {
        this.id = id;
        this.category = category;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}
