package com.example.retail_store.models;

import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class BillingDetails {

    @DecimalMin(message = "Minimum groceries bill amount should be P0.00 ", value = "0.00")
    private BigDecimal groceriesBillAmount;

    @DecimalMin(message = "Minimum total bill amount should be P0.01 (1 thebe) ", value = "0.01")
    @NotNull(message = "The bill amount is required.")
    private BigDecimal totalBillAmount;

    @DecimalMin(message = "Minimum discounted bill amount should be P0.00 ", value = "0.00")
    private BigDecimal discountedBill;

}
