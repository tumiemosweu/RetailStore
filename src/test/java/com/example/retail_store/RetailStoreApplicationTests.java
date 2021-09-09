package com.example.retail_store;

import com.example.retail_store.models.BillingDetails;
import com.example.retail_store.models.Customer;
import com.example.retail_store.repositories.ICustomerRepository;
import com.example.retail_store.services.ICustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.ws.rs.core.MediaType;
import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class RetailStoreApplicationTests {

	@Autowired
	ICustomerService customerService;

	@Autowired
	ICustomerRepository customerRepository;

	@Test
	void calculateDiscountForRegularCustomer() {
		Customer customer = customerRepository.findCustomerByEmail("talias@gmail.com").get();
		var customerDiscount = customerService.getPercentageDiscount(customer);
		assertThat(customerDiscount).isEqualTo(BigDecimal.ZERO.setScale(2));

	}

	@Test
	void calculateDiscountForEmployeeCustomer() {
		Customer customer = customerRepository.findCustomerByEmail("jadoe@gmail.com").get();
		var customerDiscount = customerService.getPercentageDiscount(customer);
		assertThat(customerDiscount).isEqualTo(BigDecimal.valueOf(0.30).setScale(2));

	}

	@Test
	void calculateDiscountForAffiliateCustomer() {
		Customer customer = customerRepository.findCustomerByEmail("jodoe@gmail.com").get();
		var customerDiscount = customerService.getPercentageDiscount(customer);
		assertThat(customerDiscount).isEqualTo(BigDecimal.valueOf(0.10).setScale(2));

	}

	@Test
	void calculateDiscountForMoreThan2YearsCustomer() {
		Customer customer = customerRepository.findCustomerByEmail("awhite@gmail.com").get();
		var customerDiscount = customerService.getPercentageDiscount(customer);
		assertThat(customerDiscount).isEqualTo(BigDecimal.valueOf(0.05).setScale(2));

	}

	@Test
	void calculateDiscountedBillForRegularCustomer() {
		var billingDetails = new BillingDetails(BigDecimal.valueOf(1000));
		Customer customer = customerRepository.findCustomerByEmail("talias@gmail.com").get();
		var finalBill = customerService.getDiscountedAmount(customer.getId(), billingDetails);
		assertThat(finalBill).isEqualTo(BigDecimal.valueOf(950).setScale(2));

	}

	@Test
	void calculateDiscountedBillForEmployeeCustomer() {
		var billingDetails = new BillingDetails(BigDecimal.valueOf(1000));
		Customer customer = customerRepository.findCustomerByEmail("jadoe@gmail.com").get();
		var finalBill = customerService.getDiscountedAmount(customer.getId(), billingDetails);
		assertThat(finalBill).isEqualTo(BigDecimal.valueOf(650).setScale(2));

	}

	@Test
	void calculateDiscountedBillForAffiliateCustomer() {
		var billingDetails = new BillingDetails(BigDecimal.valueOf(1000));
		Customer customer = customerRepository.findCustomerByEmail("jodoe@gmail.com").get();
		var finalBill = customerService.getDiscountedAmount(customer.getId(), billingDetails);
		assertThat(finalBill).isEqualTo(BigDecimal.valueOf(850).setScale(2));

	}

	@Test
	void calculateDiscountedBillForMoreThan2YearsCustomer() {
		var billingDetails = new BillingDetails(BigDecimal.valueOf(1000));
		Customer customer = customerRepository.findCustomerByEmail("awhite@gmail.com").get();
		var finalBill = customerService.getDiscountedAmount(customer.getId(), billingDetails);

		assertThat(finalBill).isEqualTo(BigDecimal.valueOf(900).setScale(2));

	}

	@Test
	void calculateDiscountAmountForAmountsOfHundreds(){
		var totalBill = BigDecimal.valueOf(990);
		var discountedBill = customerService.getOtherDiscount(totalBill);
		assertThat(discountedBill).isEqualTo(BigDecimal.valueOf(45.0).setScale(2));
	}

	@Test
	void calculateDiscountedBillForEmployeeCustomerWithGroceries(){
		var billingDetails = new BillingDetails(BigDecimal.valueOf(300), BigDecimal.valueOf(1300));
		Customer customer = customerRepository.findCustomerByEmail("jadoe@gmail.com").get();
		var finalBill = customerService.getDiscountedAmount(customer.getId(), billingDetails);
		assertThat(finalBill).isEqualTo(BigDecimal.valueOf(635).setScale(2));
	}

}
