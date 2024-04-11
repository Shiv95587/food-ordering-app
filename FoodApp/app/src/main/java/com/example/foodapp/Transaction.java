package com.example.foodapp;

import java.io.Serializable;

public class Transaction implements Serializable {
    private String phoneNumber;
    private Food fooditem;
    private String foodTotalFee;
    private String deliveryFee;
    private String tax;
    private String date;
    private String total;

    public Transaction(String phoneNumber, Food fooditem, String foodTotalFee, String deliveryFee, String tax, String date, String total) {
        this.phoneNumber = phoneNumber;
        this.fooditem = fooditem;
        this.foodTotalFee = foodTotalFee;
        this.deliveryFee = deliveryFee;
        this.tax = tax;
        this.date = date;
        this.total = total;
    }

    public String getFoodTotalFee() {
        return foodTotalFee;
    }

    public void setFoodTotalFee(String foodTotalFee) {
        this.foodTotalFee = foodTotalFee;
    }

    public String getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(String deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", fooditem=" + fooditem +
                ", date='" + date + '\'' +
                ", total='" + total + '\'' +
                '}';
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Food getFooditem() {
        return fooditem;
    }

    public void setFooditem(Food fooditem) {
        this.fooditem = fooditem;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
