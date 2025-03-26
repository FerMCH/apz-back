package com.aplazo.customers.repository;

import com.aplazo.customers.model.entity.Loan;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanReposritory extends JpaRepository<Loan, UUID> {

}
