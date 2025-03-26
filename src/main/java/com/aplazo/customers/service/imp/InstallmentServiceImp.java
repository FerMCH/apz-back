package com.aplazo.customers.service.imp;

import com.aplazo.customers.model.entity.Installment;
import com.aplazo.customers.repository.InstallmentRepository;
import com.aplazo.customers.service.InstallmentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class InstallmentServiceImp implements InstallmentService{

    private InstallmentRepository installmentRepository;

    @Override
    public Installment saveInstallment(Installment installment) {
        return this.installmentRepository.save(installment);
    }

}
