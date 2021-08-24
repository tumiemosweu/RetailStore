package com.example.retail_store.configs;

import com.example.retail_store.repositories.ICustomerRepository;
import com.example.retail_store.models.Customer;
import com.example.retail_store.repositories.ICustomerTypeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

import static java.time.Month.JANUARY;

@Configuration
public class CustomerConfig {

    @Bean
    CommandLineRunner commandLineRunner(ICustomerRepository repository,
                                        ICustomerTypeRepository customerTypeRepository){
        return args -> {
            Customer aone = new Customer(
                    "Aone",
                    "White",
                    "awhite@gmail.com",
                    LocalDate.of(2017, JANUARY, 1)
            );

            Customer jane = new Customer(
                    "Jane",
                    "Doe",
                    "jadoe@gmail.com",
                    LocalDate.of(2020, JANUARY, 1),
                    "employee"
            );

            Customer john = new Customer(
                    "John",
                    "Doe",
                    "jodoe@gmail.com",
                    LocalDate.of(2017, JANUARY, 1),
                    "affiliate"
            );

            Customer tom = new Customer(
                    "Tom",
                    "Alias",
                    "talias@gmail.com",
                    LocalDate.of(2020, JANUARY, 1)
            );
            repository.saveAll(
                    List.of(aone, jane, john, tom)
            );
        };
    }
}
