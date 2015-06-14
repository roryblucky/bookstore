package com.rory.bookstore.domain;

/**
 * Created by RoryGao on 15/6/13.
 */
public class Book {
    private String id;
    private String name;
    private String author;
    private Float price;
    private String picturePath;
    private Category category;
    private String description;


    public Book() {
    }

    public Book(String id, String name, String author, Float price, String picturePath, Category category, String description) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.price = price;
        this.picturePath = picturePath;
        this.category = category;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
