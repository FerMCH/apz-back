package com.aplazo.aplazo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.Instant;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.aplazo.aplazo.TestConstants;
import com.aplazo.customers.constants.StandarConstants;
import com.aplazo.customers.exception.error.InternalErrorException;
import com.aplazo.customers.model.entity.Installment;
import com.aplazo.customers.repository.InstallmentRepository;
import com.aplazo.customers.service.imp.InstallmentServiceImp;
import com.aplazo.customers.utils.Utils;

@ExtendWith(MockitoExtension.class)
class InstallmentServiceImpTest {

    @InjectMocks
    InstallmentServiceImp installmentServiceImp;

    @Mock
    InstallmentRepository installmentRepository;

    @Test
    void testSaveCustomer() throws InternalErrorException {
        Installment installment = new Installment();
        installment.setAmount(TestConstants.AMOUNT);
        installment.setScheduledPaymentDate(Utils
            .actualDate(Instant.now(),  TestConstants.ZONE, StandarConstants.FULL_DATE));
        installment.setStatus(TestConstants.NEXT_STATUS);

        when(installmentRepository.save(any(Installment.class))).thenAnswer(invocation -> {
            Installment installmentResponse = invocation.getArgument(0);
            installmentResponse.setId(UUID.randomUUID());
            return installmentResponse;
        });

        Installment savedInstallment = installmentServiceImp.saveInstallment(installment);

        assertThat(savedInstallment).isNotNull();

        verify(installmentRepository, times(1)).save(any(Installment.class));
    }

}
