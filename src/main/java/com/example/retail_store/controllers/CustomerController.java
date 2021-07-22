package com.example.retail_store.controllers;

import com.example.retail_store.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping(path="api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {

        this.customerService = customerService;
    }

    @GetMapping(path = "{customerId}")
    public BigDecimal discountedBill(@PathVariable("customerId") Long customerId,
                          @RequestParam(required = true) BigDecimal billAmount){

        BigDecimal discountedBillAmount = customerService.getDiscountedAmount(customerId, billAmount);
        return discountedBillAmount;
    }

}
