package com.example.retail_store;

import com.example.retail_store.models.BillingDetails;
import com.example.retail_store.models.Customer;
import com.example.retail_store.services.ICustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class RetailStoreApplicationTests {

	@Autowired
	ICustomerService customerService;

	@Test
	void calculateDiscountForRegularCustomer() {
//		var billingDetails = new BillingDetails(BigDecimal.valueOf(1000));
		var customer = new Customer(
				"Jane",
				"Doe",
				"jdoe@gmail.com",
				LocalDate.of(2020,6,14));
		var customerDiscount = customerService.getPercentageDiscount(customer);
		assertThat(customerDiscount).isEqualTo(BigDecimal.valueOf(0));

	}

	@Test
	void calculateDiscountedBillForRegularCustomer() {
		var billingDetails = new BillingDetails(BigDecimal.valueOf(1000));
		var customer = new Customer(
				"Jane",
				"Doe",
				"jdoe@gmail.com",
				LocalDate.of(2020,6,14));
		var finalBill = customerService.getDiscountedAmount(customer.getId(), billingDetails);
		assertThat(finalBill).isEqualTo(BigDecimal.valueOf(900));

	}

}
