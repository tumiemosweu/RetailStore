package com.example.retail_store.services;

import com.example.retail_store.models.BillingDetails;
import com.example.retail_store.models.Customer;

import java.math.BigDecimal;

public interface ICustomerService {

    BigDecimal getDiscountedAmount(Long customerId, BillingDetails billingDetails);

    BigDecimal getPercentageDiscount(Customer customer);

    BigDecimal getOtherDiscount(BigDecimal billAmount);


}
