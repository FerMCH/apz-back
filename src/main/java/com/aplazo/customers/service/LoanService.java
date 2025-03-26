package com.aplazo.customers.service;

import com.aplazo.customers.exception.error.BadRequestException;
import com.aplazo.customers.exception.error.InternalErrorException;
import com.aplazo.customers.exception.error.NotFoundException;
import com.aplazo.customers.model.request.LoanRequest;
import com.aplazo.customers.model.response.LoanResponse;

public interface LoanService {

    /**
     * Save a new Loan.
     *
     * @param loan new loan.
     * @return a new loan created.
     * @throws BadRequestException 
     * @throws NameNotFoundException 
     * @throws NotFoundException 
     * @throws InternalErrorException 
     */
    LoanResponse saveLoan(LoanRequest loan) throws BadRequestException, NotFoundException, InternalErrorException;

    /**
     * Get a loan by id.
     *
     * @param id loan id.
     * @return saved loan.
     * @throws BadRequestException 
     * @throws NotFoundException 
     */
    LoanResponse getLoan(String id) throws BadRequestException, NotFoundException;
}
