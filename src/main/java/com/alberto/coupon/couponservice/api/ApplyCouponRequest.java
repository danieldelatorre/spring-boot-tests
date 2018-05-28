package com.alberto.coupon.couponservice.api;

import com.alberto.coupon.couponservice.Product;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;
public class ApplyCouponRequest{
    private String code;
    private List<Product> products;

    public ApplyCouponRequest(){

    }

    public ApplyCouponRequest(String code,List<Product> products) {
        this.code = code;
        this.products = products;
    }

    public String getCode() {
        return code;
    }

    public List<Product> getProducts() {
        return products;
    }

}
