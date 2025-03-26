package com.aplazo.customers.repository;

import com.aplazo.customers.model.entity.Customer;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, UUID>{

}
