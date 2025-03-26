package com.aplazo.customers.service;

import com.aplazo.customers.exception.error.BadRequestException;
import com.aplazo.customers.exception.error.InternalErrorException;
import com.aplazo.customers.exception.error.NotFoundException;
import com.aplazo.customers.model.request.CustomerRequest;
import com.aplazo.customers.model.response.CustomerResponse;

public interface CustomerService {

    /**
     * save a new customer.
     *
     * @param customer new customer.
     * @return a new save Customer.
     * @throws InternalErrorException 
     */
    CustomerResponse saveCustomer(CustomerRequest customer) throws InternalErrorException;

    /**
     * Get customer by id.
     *
     * @param id String id.
     * @return a customer.
     * @throws BadRequestException 
     * @throws NameNotFoundException 
     * @throws NotFoundException 
     */
    CustomerResponse getCustomer(String id) throws BadRequestException, NotFoundException;

}