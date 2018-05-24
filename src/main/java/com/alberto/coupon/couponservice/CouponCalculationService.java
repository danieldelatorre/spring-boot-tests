package com.alberto.coupon.couponservice;

import java.util.List;

public interface CouponCalculationService {
    Double calculate(String couponCode, List<Product> products);
}
