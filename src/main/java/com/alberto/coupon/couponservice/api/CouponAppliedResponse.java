package com.alberto.coupon.couponservice.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class CouponAppliedResponse {
    private Double total;

    public CouponAppliedResponse(){

    }
    //@JsonCreator
    public CouponAppliedResponse(/*@JsonProperty("total")*/Double total) {
        this.total = total;
    }

    public Double getTotal() {
        return total;
    }
}
