package com.example.foodapp;

public class Category {
    private String title;
    private int image;

    public Category(String title, int image) {
        this.title = title;
        this.image = image;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public int getImage() {
        return image;
    }
}
