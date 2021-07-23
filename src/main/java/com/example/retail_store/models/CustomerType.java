package com.example.retail_store.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table
@Entity
public class CustomerType {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private BigDecimal discount;


    public CustomerType(String name, BigDecimal discount) {
        this.name = name;
        this.discount = discount;
    }

}
