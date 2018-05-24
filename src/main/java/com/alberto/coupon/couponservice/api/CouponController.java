package com.alberto.coupon.couponservice.api;

import com.alberto.coupon.couponservice.CouponCalculationService;
import com.alberto.coupon.couponservice.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
class CouponController {

    private CouponCalculationService calculationService;

    @Autowired
    public CouponController(CouponCalculationService calculationService) {
        this.calculationService = calculationService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/calculate",  consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CouponAppliedResponse> calculateCoupon(@RequestBody ApplyCouponRequest request) {
        //List<Product> products = convertToDomain(request.getProducts());
        List<Product> products = request.getProducts();
        //String sanitizedCouponCode = convertToDomain(request);
        String sanitizedCouponCode = request.getCode();
        Double result = calculationService.calculate(sanitizedCouponCode, products);

        return ResponseEntity.ok()
                .body(new CouponAppliedResponse(result));

    }

//    private String convertToDomain(@RequestBody ApplyCouponRequest request) {
//        return request.getCode().toUpperCase().trim();
//    }
//
//    private List<Product> convertToDomain(List<APIProduct> products) {
//        return products.stream()
//                .map(p -> new Product(p.getCode(), p.getPrice()))
//                .collect(Collectors.toList());
//    }
}