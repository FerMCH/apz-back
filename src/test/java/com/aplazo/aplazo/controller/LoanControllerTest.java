package com.aplazo.aplazo.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.aplazo.aplazo.TestConstants;
import com.aplazo.customers.controller.LoanController;
import com.aplazo.customers.exception.error.BadRequestException;
import com.aplazo.customers.exception.error.InternalErrorException;
import com.aplazo.customers.exception.error.NotFoundException;
import com.aplazo.customers.model.entity.Installment;
import com.aplazo.customers.model.entity.Loan;
import com.aplazo.customers.model.entity.PaymentPlan;
import com.aplazo.customers.model.request.LoanRequest;
import com.aplazo.customers.model.response.LoanResponse;
import com.aplazo.customers.service.LoanService;

@ExtendWith(MockitoExtension.class)
class LoanControllerTest {

    @InjectMocks
    private LoanController loanController;

    @Mock
    private LoanService loanService;

    @Test
    void testGetLoan() throws BadRequestException, NotFoundException {

        PaymentPlan paymentPlan = new PaymentPlan();
        Installment installment = new Installment();
        List<Installment> installments = new ArrayList<>();
        installments.add(installment);
        paymentPlan.setInstallments(installments);
        Loan loan = new Loan();
        loan.setPaymentPlan(paymentPlan);
        LoanResponse mockResponse = new LoanResponse(loan);

        when(loanService.getLoan(TestConstants.ID)).thenReturn(mockResponse);
        ResponseEntity<Object> response = loanController.getLoan(TestConstants.ID);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(loanService, times(1)).getLoan(TestConstants.ID);
    }

    @Test
    void testGetLoanNotFound() throws BadRequestException, NotFoundException {
        when(loanService.getLoan(TestConstants.ID))
            .thenThrow(NotFoundException.class);

        assertThatThrownBy(() -> loanController.getLoan(TestConstants.ID))
            .isInstanceOf(NotFoundException.class);

        verify(loanService, times(1)).getLoan(TestConstants.ID);
    }

    @Test
    void testGetLoanBadRequest() throws BadRequestException, NotFoundException {
        when(loanService.getLoan(TestConstants.ID)).thenThrow(BadRequestException.class);

        assertThatThrownBy(() -> loanController.getLoan(TestConstants.ID))
            .isInstanceOf(BadRequestException.class);
        verify(loanService, times(1)).getLoan(TestConstants.ID);
    }

    @Test
    void testCreateLoan() throws BadRequestException, NotFoundException, InternalErrorException {

        LoanRequest request = new LoanRequest();
        request.setAmount(TestConstants.AMOUNT);
        request.setCustomerId(TestConstants.ID);

        PaymentPlan paymentPlan = new PaymentPlan();
        Installment installment = new Installment();
        List<Installment> installments = new ArrayList<>();
        installments.add(installment);
        paymentPlan.setInstallments(installments);
        Loan loan = new Loan();
        loan.setPaymentPlan(paymentPlan);
        LoanResponse mockResponse = new LoanResponse(loan);

        when(loanService.saveLoan(request)).thenReturn(mockResponse);
        ResponseEntity<Object> response = loanController.createLoan(request);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        verify(loanService, times(1)).saveLoan(request);
    }

    @Test
    void testCreateLoanBadRequest() throws BadRequestException, NotFoundException, InternalErrorException {
        LoanRequest request = new LoanRequest();
        request.setCustomerId(UUID.randomUUID().toString());
        when(loanService.saveLoan(request)).thenThrow(BadRequestException.class);

        assertThatThrownBy(() -> loanController.createLoan(request))
            .isInstanceOf(BadRequestException.class);
        verify(loanService, times(1)).saveLoan(request);
    }

}
