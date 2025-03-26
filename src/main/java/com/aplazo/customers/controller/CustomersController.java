package com.aplazo.customers.controller;

import com.aplazo.customers.constants.LogConstants;
import com.aplazo.customers.exception.error.BadRequestException;
import com.aplazo.customers.exception.error.InternalErrorException;
import com.aplazo.customers.exception.error.NotFoundException;
import com.aplazo.customers.model.request.CustomerRequest;
import com.aplazo.customers.model.response.CustomerResponse;
import com.aplazo.customers.service.CustomerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@AllArgsConstructor
@Slf4j
@RestController()
@RequestMapping("${routes.customers.base}")
@Validated
public class CustomersController {

    private CustomerService customerService;

    /**
     * Get customer by id.
     *
     * @param id String id.
     * @return a customer.
     * @throws BadRequestException 
     * @throws NotFoundException 
     */
    
    @GetMapping("${routes.customers.get}")
    public ResponseEntity<Object> getCustomer(@PathVariable String customerId) throws BadRequestException, NotFoundException {
        log.info(LogConstants.CALL_METHOD, LogConstants.GET_CUSTOMER);
        return new ResponseEntity<>(customerService.getCustomer(customerId), HttpStatus.OK);
    }

    /**
     * save a new customer.
     *
     * @param CustomerRequest new customer.
     * @return a new save Customer.
     * @throws InternalErrorException 
     */
    @PostMapping()
    public ResponseEntity<Object> createCustomer(@Valid @RequestBody CustomerRequest customer) throws InternalErrorException {
        log.info(LogConstants.CALL_METHOD, LogConstants.CREATE_CUSTOMER);
        CustomerResponse response = customerService.saveCustomer(customer);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
