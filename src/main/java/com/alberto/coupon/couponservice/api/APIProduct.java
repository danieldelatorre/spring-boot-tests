package com.alberto.coupon.couponservice.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;


public class APIProduct implements Serializable {
    private String code;
    private Double price;

    @JsonCreator
    public APIProduct(@JsonProperty("code") String code,@JsonProperty("price") Double price) {
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
