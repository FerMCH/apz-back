package com.aplazo.customers.repository;

import com.aplazo.customers.model.entity.Installment;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstallmentRepository extends JpaRepository<Installment, UUID> {

}
