package com.example.retail_store.configs;

import com.example.retail_store.models.CustomerType;
import com.example.retail_store.repositories.ICustomerTypeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.List;

@Configuration
public class CustomerTypeConfig {

    @Bean
    CommandLineRunner employeeCommandLineRunner(ICustomerTypeRepository repository){
        return args -> {
            CustomerType employee = new CustomerType(
                    "employee",
                    BigDecimal.valueOf(0.3)
            );

            CustomerType affiliate = new CustomerType(
                    "affiliate",
                    BigDecimal.valueOf(0.1)
            );

            repository.saveAll(
                    List.of(employee, affiliate)
            );
        };
    }
}
