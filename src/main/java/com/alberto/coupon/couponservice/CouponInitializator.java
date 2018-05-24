package com.alberto.coupon.couponservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
class CouponInitializator implements CommandLineRunner {

    private final CouponRepository couponRepository;

    @Autowired
    public CouponInitializator(CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }

    @Override
    public void run(String... strings) throws Exception {
        Stream.of(new Coupon("TEST", 10.0)
                , new Coupon("TEST2", 12.0) )
                .forEach(couponRepository::save);

        couponRepository.findAll().forEach(System.out::println);
    }
}