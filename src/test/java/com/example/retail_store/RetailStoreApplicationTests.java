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
import java.math.RoundingMode;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class RetailStoreApplicationTests {

	@Autowired
	ICustomerService customerService;

	@Autowired
	ICustomerRepository customerRepository;

	@Autowired
	private MockMvc mvc;

	@Test
	public void getHello() throws Exception {
		mvc.perform(MockMvcRequestBuilders.post("/retail-store/v1/billing-discount/1/")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{'totalBillAmount': 200.00}")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	void calculateDiscountForRegularCustomer() {
		Customer customer = customerRepository.findCustomerByEmail("awhite@gmail.com").get();
		var customerDiscount = customerService.getPercentageDiscount(customer);
		assertThat(customerDiscount).isEqualTo(BigDecimal.ZERO.setScale(2));

	}

	@Test
	void calculateDiscountedBillForRegularCustomer() {
		var billingDetails = new BillingDetails(BigDecimal.valueOf(1000));
		Customer customer = customerRepository.findCustomerByEmail("awhite@gmail.com").get();
		var finalBill = customerService.getDiscountedAmount(customer.getId(), billingDetails);
		assertThat(finalBill).isEqualTo(BigDecimal.valueOf(950).setScale(2));

	}

	@Test
	void calculateDiscountAmountForAmountsOfHundreds(){
		var totalBill = BigDecimal.valueOf(890);
		var discountedBill = customerService.getOtherDiscount(totalBill);
		assertThat(discountedBill).isEqualTo(BigDecimal.valueOf(40.0).setScale(2));
	}

	@Test
	void calculatePercentageDiscountForCustomer(){

		Customer customer = customerRepository.findCustomerByEmail("imosweu@gmail.com").get();
		var customerDiscount = customerService.getPercentageDiscount(customer);
		assertThat(customerDiscount).isEqualTo(BigDecimal.valueOf(0.30).setScale(2));
	}

}
