package com.alberto.coupon.couponservice.unittests;

import com.alberto.coupon.couponservice.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.alberto.coupon.couponservice.CouponConstants.TEN_EURO_DISCOUNT_COUPON;
import static com.alberto.coupon.couponservice.CouponConstants.UNKNOWN_COUPON_CODE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CouponCalculatorServiceTests {


    private CouponCalculationService service;
    @Mock
    private CouponRepository couponRepository;
    private List<Product> products;
    private Product product1 = new Product("productcode1", 3.0);
    private Product product2 = new Product("productcode2", 12.0);


    @Before
    public void setUp(){
        service = new DefaultCouponCalculationService(couponRepository);

        products = new ArrayList<Product>(){{
            add(product1);
            add(product2);
        }};
    }
    @Test
    public void unknown_coupon_returns_prices_added(){
        double result = service.calculate(UNKNOWN_COUPON_CODE, Collections.emptyList());

        assertThat(result).isEqualTo(0.0);
    }

    @Test
    public void unknown_coupon_with_multiple_products_returns_sum_of_products(){

        double result = service.calculate(UNKNOWN_COUPON_CODE, products);

        assertThat(result).isEqualTo(15.0);
    }

    @Test
    public void discount_coupon_is_appplied(){
        Coupon coupon = new Coupon(TEN_EURO_DISCOUNT_COUPON, 10.0);
        when(couponRepository.findByCode(TEN_EURO_DISCOUNT_COUPON)).thenReturn(Optional.of(coupon));

        double result = service.calculate(TEN_EURO_DISCOUNT_COUPON, products);

        assertThat(result).isEqualTo(5.0);
    }

}
