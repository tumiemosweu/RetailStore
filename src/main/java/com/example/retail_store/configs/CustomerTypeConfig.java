package com.example.retail_store.configs;

import com.example.retail_store.models.CustomerType;
import com.example.retail_store.repositories.CustomerTypeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static java.time.Month.JANUARY;

@Configuration
public class CustomerTypeConfig {

    @Bean
    CommandLineRunner employeeCommandLineRunner(CustomerTypeRepository repository){
        return args -> {
            CustomerType employee = new CustomerType(
                    "Employee",
                    BigDecimal.valueOf(0.3)
            );

            repository.saveAll(
                    List.of(employee)
            );
        };
    }
}
