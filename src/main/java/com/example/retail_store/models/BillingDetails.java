package com.example.retail_store.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@RequiredArgsConstructor
public class BillingDetails {

    @DecimalMin(message = "Minimum groceries bill amount should be P0.00 ", value = "0.00")
    private BigDecimal groceriesBillAmount = BigDecimal.ZERO;;

    @DecimalMin(message = "Minimum total bill amount should be $0.01", value = "0.01")
    @NotNull(message = "The bill amount is required.")
    private BigDecimal totalBillAmount;

    @DecimalMin(message = "Minimum discounted bill amount should be $0.00 ", value = "0.00")
    private BigDecimal discountedBill;

    public BillingDetails(BigDecimal totalBillAmount) {

        this.totalBillAmount = totalBillAmount;
    }

    public BillingDetails(BigDecimal groceriesBillAmount, BigDecimal totalBillAmount) {
        this.groceriesBillAmount = groceriesBillAmount;

        if(groceriesBillAmount != null)
            this.totalBillAmount = totalBillAmount;

    }
}
