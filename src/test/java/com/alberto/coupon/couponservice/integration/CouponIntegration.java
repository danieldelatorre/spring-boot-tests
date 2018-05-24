package com.alberto.coupon.couponservice.integration;


import com.alberto.coupon.couponservice.api.APIProduct;
import com.alberto.coupon.couponservice.api.ApplyCouponRequest;
import com.alberto.coupon.couponservice.api.CouponAppliedResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static com.alberto.coupon.couponservice.CouponConstants.TEN_EURO_DISCOUNT_COUPON;
import static com.alberto.coupon.couponservice.CouponConstants.UNKNOWN_COUPON_CODE;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CouponIntegration {

    @Autowired
    private TestRestTemplate testRestTemplate;


    @Test
    public void existing_coupon_applies_discount() throws Exception {
        List<APIProduct> products = generateAPIProducts();
        ApplyCouponRequest request = new ApplyCouponRequest(TEN_EURO_DISCOUNT_COUPON, products);
        HttpEntity<ApplyCouponRequest> entity = createJSONEntity(request);

        ResponseEntity<CouponAppliedResponse> responseEntity = this.testRestTemplate.
                postForEntity("/calculate", entity, CouponAppliedResponse.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().getTotal()).isEqualTo(5.0);

    }

    @Test
    public void non_existing_coupon_is_not_applied() throws Exception {
        List<APIProduct> products = generateAPIProducts();
        ApplyCouponRequest request = new ApplyCouponRequest(UNKNOWN_COUPON_CODE, products);

        HttpEntity<ApplyCouponRequest> entity = createJSONEntity(request);

        ResponseEntity<CouponAppliedResponse> responseEntity = this.testRestTemplate.
                postForEntity("/calculate", entity, CouponAppliedResponse.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().getTotal()).isEqualTo(15.0);

    }

    private HttpEntity<ApplyCouponRequest> createJSONEntity(ApplyCouponRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<ApplyCouponRequest>(request ,headers);
    }

    private List<APIProduct> generateAPIProducts() {
        return Arrays.asList(
                    new APIProduct("Camisa", 3.0),
                    new APIProduct("Traje", 12.0));
    }

}