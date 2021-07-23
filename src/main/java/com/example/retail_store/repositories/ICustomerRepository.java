package com.example.retail_store.repositories;

import com.example.retail_store.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICustomerRepository
        extends JpaRepository<Customer, Long> {

    Optional<Customer> findCustomerByEmail(String email);
}
