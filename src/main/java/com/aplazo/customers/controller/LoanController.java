package com.aplazo.customers.controller;

import com.aplazo.customers.constants.LogConstants;
import com.aplazo.customers.exception.error.BadRequestException;
import com.aplazo.customers.exception.error.InternalErrorException;
import com.aplazo.customers.exception.error.NotFoundException;
import com.aplazo.customers.model.request.LoanRequest;
import com.aplazo.customers.model.response.LoanResponse;
import com.aplazo.customers.service.LoanService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping("${routes.loans.base}")
@Validated
public class LoanController {

    private LoanService loanService;

    
    @GetMapping("${routes.loans.get}")
    public ResponseEntity<Object> getLoan(@PathVariable String loanId) throws BadRequestException, NotFoundException {
        log.info(LogConstants.CALL_METHOD, LogConstants.GET_CUSTOMER);
        return new ResponseEntity<>(loanService.getLoan(loanId), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Object> createLoan(@Valid @RequestBody LoanRequest loan) throws BadRequestException, NotFoundException, InternalErrorException {
        log.info(LogConstants.CALL_METHOD, LogConstants.CREATE_CUSTOMER);
        LoanResponse response = loanService.saveLoan(loan);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    

}
