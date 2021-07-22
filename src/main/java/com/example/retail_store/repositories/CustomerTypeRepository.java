package com.example.retail_store.repositories;

import com.example.retail_store.models.CustomerType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerTypeRepository
        extends JpaRepository<CustomerType, Long> {

    Optional<CustomerType> findCustomerTypeByName(String typeName);
}
