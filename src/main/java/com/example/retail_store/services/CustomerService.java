package com.example.retail_store.services;

import com.example.retail_store.models.BillingDetails;
import com.example.retail_store.models.CustomerType;
import com.example.retail_store.models.Customer;
import com.example.retail_store.repositories.ICustomerRepository;
import com.example.retail_store.repositories.ICustomerTypeRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

@Service
public class CustomerService implements ICustomerService{

    @Resource
    ICustomerRepository customerRepository;

    @Resource
    ICustomerTypeRepository customerTypeRepository;

//    @Autowired
//    public CustomerService(ICustomerRepository customerRepository,
//                           ICustomerTypeRepository customerTypeRepository) {
//
//        this.customerRepository = customerRepository;
//        this.customerTypeRepository = customerTypeRepository;
//    }

    @Override
    public BigDecimal getDiscountedAmount(Long customerId, BillingDetails billingDetails) {

        Optional<Customer> customerOptional = customerRepository.findById(customerId);

        var discountedBillAmount = billingDetails.getTotalBillAmount()
                .subtract(billingDetails.getGroceriesBillAmount());

        if(customerOptional.isPresent()){
            if (customerOptional.get().getYearsActive() >= 2){

            }
            else{
                discountedBillAmount = discountedBillAmount.multiply(getPercentageDiscount(
                        customerOptional.get())).setScale(2, RoundingMode.HALF_EVEN);

            }
        }
        return discountedBillAmount.subtract(getOtherDiscount(discountedBillAmount));

    }

    @Override
    public BigDecimal getOtherDiscount(BigDecimal billAmount){

        var discountAmount = billAmount.setScale(0, RoundingMode.HALF_DOWN);

        if(billAmount.compareTo(BigDecimal.ZERO) != 0 ) {

            discountAmount = discountAmount.multiply(BigDecimal.valueOf(0.05));
        }

        return discountAmount.setScale(2, RoundingMode.HALF_EVEN);
    }

    @Override
    public BigDecimal getPercentageDiscount(Customer customer){

        Optional<CustomerType> customerTypeOptional = customerTypeRepository
                .findCustomerTypeByName(customer.getCustomerType());

        if(customerTypeOptional.isPresent()){
            return customerTypeOptional.get().getDiscount();
        }

        return BigDecimal.valueOf(0);
    }

}
