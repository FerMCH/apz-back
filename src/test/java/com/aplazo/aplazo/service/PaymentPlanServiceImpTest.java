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
import com.aplazo.customers.constants.Properties;
import com.aplazo.customers.exception.error.InternalErrorException;
import com.aplazo.customers.model.entity.Installment;
import com.aplazo.customers.model.entity.PaymentPlan;
import com.aplazo.customers.repository.PaymentPlanRepository;
import com.aplazo.customers.service.InstallmentService;
import com.aplazo.customers.service.imp.PaymentPlanServiceImp;

@ExtendWith(MockitoExtension.class)
class PaymentPlanServiceImpTest {

    @InjectMocks
    PaymentPlanServiceImp paymentPlanServiceImp;

    @Mock
    PaymentPlanRepository paymentPlanRepository;

    @Mock
    InstallmentService installmentService;

    @Mock
    private Properties properties;

    @Test
    void testsavPaymentPlan() throws InternalErrorException {

        when(properties.getZone()).thenReturn(TestConstants.ZONE);

        when(paymentPlanRepository.save(any(PaymentPlan.class))).thenAnswer(invocation -> {
            PaymentPlan paymentPlanResponse = invocation.getArgument(0);
            paymentPlanResponse.setId(UUID.randomUUID());
            return paymentPlanResponse;
        });

        when(installmentService.saveInstallment(any(Installment.class)))
            .thenReturn(new Installment());

        PaymentPlan paymentPlan = paymentPlanServiceImp.savPaymentPlan(Instant.now(), TestConstants.AMOUNT);

        assertThat(paymentPlan).isNotNull();

        verify(paymentPlanRepository, times(1)).save(any(PaymentPlan.class));
        verify(installmentService, times(1)).saveInstallment(any(Installment.class));
    }

}
