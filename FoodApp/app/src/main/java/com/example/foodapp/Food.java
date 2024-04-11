package com.example.foodapp;

import java.io.Serializable;

public class Food implements Serializable {
    private String title;
    private int pic;
    private Double price;
    private String description;
    private int numberInCart;

    public Food(String title, int pic, Double price, String description) {
        this.title = title;
        this.pic = pic;
        this.price = price;
        this.description = description;
    }

    public int getNumberInCart() {
        return numberInCart;
    }

    public void setNumberInCart(int numberInCart) {
        this.numberInCart = numberInCart;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
