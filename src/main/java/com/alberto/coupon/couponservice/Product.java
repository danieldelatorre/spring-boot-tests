package com.alberto.coupon.couponservice;

public class Product {
    private String code;
    private Double price;

    public Product(String code, Double price) {
        this.code = code;
        this.price = price;
    }

    public String getCode() {
        return code;
    }

    public Double getPrice() {
        return price;
    }
}
