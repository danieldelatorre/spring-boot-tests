package com.alberto.coupon.couponservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultCouponCalculationService implements CouponCalculationService {

    private CouponRepository couponRepository;

    @Autowired
    public DefaultCouponCalculationService(CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }

    @Override
    public Double calculate(String couponCode, List<Product> products) {
        Double products_total = getTotalPriceFrom(products);
        Double discounted_amount = discountedAmount(couponCode);

        Double total_result = calculateTotal(products_total, discounted_amount);
        return total_result;
    }

    private Double calculateTotal(Double products_total, Double discounted_amount) {
        return products_total - discounted_amount;
    }

    private double discountedAmount(String couponCode) {
        Optional<Coupon> coupon = couponRepository.findByCode(couponCode);

        return coupon.map(Coupon::getPrice).orElse(0.0);
    }

    private Double getTotalPriceFrom(List<Product> products) {
        return products.stream()
                    .map(Product::getPrice)
                    .reduce(0.0, Double::sum);
    }
}
