package com.example.retail_store;

import com.example.retail_store.models.BillingDetails;
import com.example.retail_store.models.Customer;
import com.example.retail_store.repositories.ICustomerRepository;
import com.example.retail_store.services.ICustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class RetailStoreApplicationTests {

	@Autowired
	ICustomerService customerService;

	@Autowired
	ICustomerRepository customerRepository;

	@Test
	void calculateDiscountForRegularCustomer() {
		Customer customer = customerRepository.findCustomerByEmail("awhite@gmail.com").get();
		var customerDiscount = customerService.getPercentageDiscount(customer);
		assertThat(customerDiscount).isEqualTo(BigDecimal.valueOf(0));

	}

	@Test
	void calculateDiscountedBillForRegularCustomer() {
		var billingDetails = new BillingDetails(BigDecimal.valueOf(1000));
		Customer customer = customerRepository.findCustomerByEmail("awhite@gmail.com").get();
		var finalBill = customerService.getDiscountedAmount(customer.getId(), billingDetails);
		assertThat(finalBill).isEqualTo(BigDecimal.valueOf(900));

	}

	@Test
	void calculateDiscountAmountForAmountsOfHundreds(){
		var totalBill = BigDecimal.valueOf(890);
		var discountedBill = customerService.getOtherDiscount(totalBill);
		assertThat(discountedBill).isEqualTo(BigDecimal.valueOf(40.0).setScale(2, RoundingMode.HALF_EVEN));
	}

	@Test
	void calculatePercentageDiscountForCustomer(){

		Customer customer = customerRepository.findCustomerByEmail("imosweu@gmail.com").get();
		var customerDiscount = customerService.getPercentageDiscount(customer);
		assertThat(customerDiscount).isEqualTo(BigDecimal.valueOf(0.30).setScale(2, RoundingMode.HALF_EVEN));
	}

}
