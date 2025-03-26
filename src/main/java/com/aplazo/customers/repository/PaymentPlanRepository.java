package com.aplazo.customers.repository;

import com.aplazo.customers.model.entity.PaymentPlan;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentPlanRepository extends JpaRepository<PaymentPlan, UUID> {

}
