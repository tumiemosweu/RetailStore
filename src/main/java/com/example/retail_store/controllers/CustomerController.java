package com.example.retail_store.controllers;

import com.example.retail_store.models.BillingDetails;
import com.example.retail_store.services.CustomerService;
import com.example.retail_store.services.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.math.BigDecimal;

/**
 * A REST API controller that calculates customer discount based on the customer type and benefits
 *
 * @author  I.Mosweu
 * @version 1.0
 * @since   24/07/2021
 */


@RestController
@RequestMapping(path="retail-store/v1")
public class CustomerController {

    @Resource
    ICustomerService customerService;

    /**
     * API action that receives billing information from front-end and calculates customer discount
     * @param billingDetails request body
     * @param customerId
     * @return BillingDetails type response body
     */
    @GetMapping(value = "/billing-discount/{customerId}", produces = "application/json")
    public BillingDetails discountedBill(@Valid @RequestBody BillingDetails billingDetails,
                                     @PathVariable("customerId") Long customerId){

        BigDecimal discountedBillAmount = customerService
                .getDiscountedAmount(customerId, billingDetails);

        billingDetails.setDiscountedBill(discountedBillAmount);
        return billingDetails;
    }

}
