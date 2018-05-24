package com.alberto.coupon.couponservice;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Coupon{
    @Id
    @GeneratedValue
    private Long id;
    private String code;
    private Double price;

    public Coupon() {
    }

    public Coupon(String code, Double price) {
        this.code = code;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public Double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Coupon{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", price=" + price +
                '}';
    }
}