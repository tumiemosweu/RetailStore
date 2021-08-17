package com.example.retail_store.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


import java.time.LocalDate;
import java.time.Period;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
@Entity
public class Customer {

    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate joinDate;
    private String customerType;

    @Transient
    private Integer yearsActive;

    public Customer(String firstName,
                    String lastName,
                    String email,
                    LocalDate joinDate,
                    String customerType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.joinDate = joinDate;
        this.customerType = customerType;
    }

    public Customer(String firstName,
                    String lastName,
                    String email,
                    LocalDate joinDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.joinDate = joinDate;
    }

    public Integer getYearsActive() {

        return Period.between(getJoinDate(), LocalDate.now()).getYears();
    }
}
