package com.example.retail_store.services;

import com.example.retail_store.models.CustomerType;
import com.example.retail_store.repositories.CustomerTypeRepository;
import com.example.retail_store.models.Customer;
import com.example.retail_store.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerTypeRepository customerTypeRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository,
                           CustomerTypeRepository customerTypeRepository) {

        this.customerRepository = customerRepository;
        this.customerTypeRepository = customerTypeRepository;
    }

    public BigDecimal getDiscountedAmount(Long customerId, BigDecimal billAmount) {

        Optional<Customer> customerOptional = customerRepository.findById(customerId);

        var discountedBillAmount = billAmount;

        if(customerOptional.isPresent()){
            if (customerOptional.get().getYearsActive() >= 2){

            }
            else{
                discountedBillAmount = billAmount.multiply(getPercentageDiscount(
                        customerOptional.get())).setScale(2, RoundingMode.HALF_EVEN);

            }
        }

        return billAmount.subtract(getHundredDollarDiscount(discountedBillAmount));

    }

    private BigDecimal getHundredDollarDiscount(BigDecimal billAmount){

        var discountAmount = billAmount.setScale(0, RoundingMode.HALF_DOWN);

        if(billAmount.compareTo(BigDecimal.ZERO) != 0 ) {

            discountAmount = discountAmount.multiply(BigDecimal.valueOf(0.05));
        }

        return discountAmount.setScale(2, RoundingMode.HALF_EVEN);
    }

    private BigDecimal getPercentageDiscount(Customer customer){

        return customer.getCustomerType().getDiscount();
    }

}
