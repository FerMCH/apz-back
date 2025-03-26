package com.aplazo.customers.service.imp;

import com.aplazo.customers.constants.Constants;
import com.aplazo.customers.constants.ErrorConstants;
import com.aplazo.customers.constants.LogConstants;
import com.aplazo.customers.constants.Properties;
import com.aplazo.customers.constants.StandarConstants;
import com.aplazo.customers.exception.error.BadRequestException;
import com.aplazo.customers.exception.error.InternalErrorException;
import com.aplazo.customers.exception.error.NotFoundException;
import com.aplazo.customers.model.entity.Loan;
import com.aplazo.customers.model.entity.PaymentPlan;
import com.aplazo.customers.model.request.LoanRequest;
import com.aplazo.customers.model.response.LoanResponse;
import com.aplazo.customers.repository.LoanReposritory;
import com.aplazo.customers.service.CustomerService;
import com.aplazo.customers.service.LoanService;
import com.aplazo.customers.service.PaymentPlanService;
import com.aplazo.customers.utils.Utils;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class LoanServiceImp implements LoanService{

    private LoanReposritory loanReposritory;
    private PaymentPlanService paymentPlanService;
    private CustomerService customerService;
    private Properties properties;

    @Override
    public LoanResponse saveLoan(LoanRequest loan) throws BadRequestException, NotFoundException, InternalErrorException {

        log.info(LogConstants.NEW_LOAN);

        if (Objects.isNull(this.customerService.getCustomer(loan.getCustomerId()))) {
            throw new NotFoundException(ErrorConstants.CUSTOMER_NOT_FOUND, ErrorConstants.ERROR_DETAIL);
        }

        Loan newLoan = new Loan();
        Instant now = Instant.now();
        PaymentPlan savedPaymentPlan = this.paymentPlanService.savPaymentPlan(now, Constants.CREDIT_LINE_AMOUNT);
        
        newLoan.setCustomerId(UUID.fromString(loan.getCustomerId()));
        newLoan.setStatus(Constants.STATUS_ACTIVE);
        String date = Utils.actualDate(now, this.properties.getZone(), StandarConstants.FULL_DATE);
        newLoan.setCreatedAt(date);
        newLoan.setPaymentPlan(savedPaymentPlan);

        Loan savedLoan = this.loanReposritory.save(newLoan);

        log.info(LogConstants.SAVE_LOAN, date);
        return new LoanResponse(savedLoan);
    }

    @Override
    public LoanResponse getLoan(String id) throws BadRequestException, NotFoundException {
        log.info(LogConstants.GET_CUSTOMER_ID, id);
        LoanResponse response;

        try {
            Loan loan = this.loanReposritory.getReferenceById(UUID.fromString(id));
            response = new LoanResponse(loan);

        } catch (NumberFormatException e) {
            throw new BadRequestException(ErrorConstants.INVALID_LOAN_REQUEST, ErrorConstants.ERROR_DETAIL);
        } catch (EntityNotFoundException e) {
            throw new NotFoundException(ErrorConstants.LOAN_NOT_FOUND, ErrorConstants.ERROR_DETAIL);
        }

        return response;
    }

}
