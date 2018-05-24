package com.alberto.coupon.couponservice.unittests;

import com.alberto.coupon.couponservice.CouponCalculationService;
import com.alberto.coupon.couponservice.Product;
import com.alberto.coupon.couponservice.api.APIProduct;
import com.alberto.coupon.couponservice.api.ApplyCouponRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static com.alberto.coupon.couponservice.CouponConstants.TEN_EURO_DISCOUNT_COUPON;
import static com.alberto.coupon.couponservice.CouponConstants.UNKNOWN_COUPON_CODE;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class CouponControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CouponCalculationService calculationService;

    private List<Product> products = Arrays.asList(
            new Product("camisa", 3.0),
            new Product("traje", 12.0));

    @Test
    public void existingDiscountCode_returns_discounted_total() throws Exception {
        String json = new ObjectMapper().writeValueAsString(new ApplyCouponRequest(TEN_EURO_DISCOUNT_COUPON, products ));
        when(this.calculationService.calculate(anyString(), anyList())).thenReturn(5.0);

        this.mockMvc.perform(post("/calculate")
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("total").value(5.0));

    }

    @Test
    public void nonexistingDiscountCode_returns_discounted_total() throws Exception {
        String json = new ObjectMapper().writeValueAsString(new ApplyCouponRequest(UNKNOWN_COUPON_CODE, products ));
        when(this.calculationService.calculate(anyString(), anyList())).thenReturn(15.0);

        this.mockMvc.perform(post("/calculate")
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("total").value(15.0));

    }

}