package com.aplazo.aplazo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyFloat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.aplazo.aplazo.TestConstants;
import com.aplazo.customers.constants.Properties;
import com.aplazo.customers.exception.error.BadRequestException;
import com.aplazo.customers.exception.error.InternalErrorException;
import com.aplazo.customers.exception.error.NotFoundException;
import com.aplazo.customers.model.entity.Installment;
import com.aplazo.customers.model.entity.Loan;
import com.aplazo.customers.model.entity.PaymentPlan;
import com.aplazo.customers.model.request.LoanRequest;
import com.aplazo.customers.model.response.CustomerResponse;
import com.aplazo.customers.model.response.LoanResponse;
import com.aplazo.customers.repository.LoanReposritory;
import com.aplazo.customers.service.PaymentPlanService;
import com.aplazo.customers.service.imp.CustomerServiceImp;
import com.aplazo.customers.service.imp.LoanServiceImp;

import jakarta.persistence.EntityNotFoundException;

@ExtendWith(MockitoExtension.class)
class LoanServiceImpTest {

    @InjectMocks
    LoanServiceImp loanServiceImp;

    @Mock
    LoanReposritory loanReposritory;

    @Mock
    PaymentPlanService paymentPlanService;
    
    @Mock
    CustomerServiceImp customerServiceImp;
    
    @Mock
    Properties properties;


    @Test
    void testSaveLoan() throws BadRequestException, NotFoundException, InternalErrorException {

        when(customerServiceImp.getCustomer(anyString()))
            .thenReturn(new CustomerResponse());

        when(properties.getZone()).thenReturn(TestConstants.ZONE);

        PaymentPlan paymentPlan = new PaymentPlan();
        Installment installment = new Installment();
        List<Installment> installments = new ArrayList<>();
        installments.add(installment);
        paymentPlan.setInstallments(installments);

        when(paymentPlanService.savPaymentPlan(any(Instant.class), anyFloat()))
            .thenReturn(paymentPlan);

        when(loanReposritory.save(any(Loan.class))).thenAnswer(invocation -> {
            Loan loan = invocation.getArgument(0);
            loan.setId(UUID.randomUUID());
            return loan;
        });

        LoanRequest loanRequest = new LoanRequest();
        loanRequest.setAmount(TestConstants.AMOUNT);
        loanRequest.setCustomerId(TestConstants.ID);
        

        LoanResponse loan = loanServiceImp.saveLoan(loanRequest);

        assertThat(loan).isNotNull();

        verify(paymentPlanService, times(1)).savPaymentPlan(any(Instant.class), anyFloat());
        verify(loanReposritory, times(1)).save(any(Loan.class));
        verify(customerServiceImp, times(1)).getCustomer(anyString());
    }

    @Test
    void testSaveLoanCustomerNotFound() throws BadRequestException, NotFoundException {

        when(customerServiceImp.getCustomer(anyString()))
            .thenThrow(NotFoundException.class);


        LoanRequest loanRequest = new LoanRequest();
        loanRequest.setAmount(TestConstants.AMOUNT);
        loanRequest.setCustomerId(TestConstants.ID);


        assertThatThrownBy(() -> loanServiceImp.saveLoan(loanRequest))
            .isInstanceOf(NotFoundException.class);

        verify(customerServiceImp, times(1)).getCustomer(anyString());
    }

    @Test
    void testNotCustomer() throws BadRequestException, NotFoundException {

        when(customerServiceImp.getCustomer(anyString()))
            .thenReturn(null);


        LoanRequest loanRequest = new LoanRequest();
        loanRequest.setAmount(TestConstants.AMOUNT);
        loanRequest.setCustomerId(TestConstants.ID);


        assertThatThrownBy(() -> loanServiceImp.saveLoan(loanRequest))
            .isInstanceOf(NotFoundException.class);

        verify(customerServiceImp, times(1)).getCustomer(anyString());
    }


    @Test
    void testGetLoan() throws BadRequestException, NotFoundException {

        PaymentPlan paymentPlan = new PaymentPlan();
        Installment installment = new Installment();
        List<Installment> installments = new ArrayList<>();
        installments.add(installment);
        paymentPlan.setInstallments(installments);
        Loan loan = new Loan();
        loan.setPaymentPlan(paymentPlan);

        when(loanReposritory.getReferenceById(UUID.fromString(TestConstants.ID)))
            .thenReturn(loan);
        LoanResponse loanResponse = loanServiceImp.getLoan(TestConstants.ID);

        assertThat(loanResponse).isNotNull();

        verify(loanReposritory, times(1)).getReferenceById(any());


    }

    @Test
    void testGetLoanInvalidId() throws BadRequestException, NotFoundException {

        PaymentPlan paymentPlan = new PaymentPlan();
        Installment installment = new Installment();
        List<Installment> installments = new ArrayList<>();
        installments.add(installment);
        paymentPlan.setInstallments(installments);
        Loan loan = new Loan();
        loan.setPaymentPlan(paymentPlan);

        when(loanReposritory.getReferenceById(UUID.fromString(TestConstants.ID)))
            .thenThrow(NumberFormatException.class);

        assertThatThrownBy(() -> loanServiceImp.getLoan(TestConstants.ID))
                    .isInstanceOf(BadRequestException.class);

        verify(loanReposritory, times(1)).getReferenceById(any());


    }

    @Test
    void testGetLoanNotFound() throws BadRequestException, NotFoundException {

        PaymentPlan paymentPlan = new PaymentPlan();
        Installment installment = new Installment();
        List<Installment> installments = new ArrayList<>();
        installments.add(installment);
        paymentPlan.setInstallments(installments);
        Loan loan = new Loan();
        loan.setPaymentPlan(paymentPlan);

        when(loanReposritory.getReferenceById(UUID.fromString(TestConstants.ID)))
            .thenThrow(EntityNotFoundException.class);

        assertThatThrownBy(() -> loanServiceImp.getLoan(TestConstants.ID))
                    .isInstanceOf(NotFoundException.class);

        verify(loanReposritory, times(1)).getReferenceById(any());


    }

}
