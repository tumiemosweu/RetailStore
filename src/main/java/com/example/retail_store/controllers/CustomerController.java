package com.example.retail_store.controllers;

import com.example.retail_store.models.BillingDetails;
import com.example.retail_store.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;

@RestController
@RequestMapping(path="api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {

        this.customerService = customerService;
    }

    @PostMapping(value = "/retail-store/billing-discount/{customerId}", produces = "application/json")
    @ResponseBody
    public BillingDetails discountedBill(@Valid @RequestBody BillingDetails billingDetails,
                                     @PathVariable("customerId") Long customerId){

        BigDecimal discountedBillAmount = customerService
                .getDiscountedAmount(customerId, billingDetails);

        billingDetails.setDiscountedBill(discountedBillAmount);
        return billingDetails;
    }

}
