package com.example.retail_store.configs;

import com.example.retail_store.repositories.CustomerRepository;
import com.example.retail_store.models.Customer;
import com.example.retail_store.repositories.CustomerTypeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

import static java.time.Month.JANUARY;

@Configuration
public class CustomerConfig {

    @Bean
    CommandLineRunner commandLineRunner(CustomerRepository repository,
                                        CustomerTypeRepository customerTypeRepository){
        return args -> {
            Customer tumie = new Customer(
                    "Tumie",
                    "Mosweu",
                    "imosweuk@gmail.com",
                    LocalDate.of(2020, JANUARY, 1),
                    "employee"
            );

            Customer gako = new Customer(
                    "Gako",
                    "Kamogelo",
                    "gakokam@gmail.com",
                    LocalDate.of(2017, JANUARY, 1),
                    "employee"
            );
            repository.saveAll(
                    List.of(tumie, gako)
            );
        };
    }
}
