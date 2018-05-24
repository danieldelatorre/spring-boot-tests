package com.alberto.coupon.couponservice.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;
public class ApplyCouponRequest implements Serializable {
    private String code;
    private List<APIProduct> products;

    @JsonCreator
    public ApplyCouponRequest(@JsonProperty("code") String code,@JsonProperty("products") List<APIProduct> products) {
        this.code = code;
        this.products = products;
    }

    public String getCode() {
        return code;
    }

    public List<APIProduct> getProducts() {
        return products;
    }

}
