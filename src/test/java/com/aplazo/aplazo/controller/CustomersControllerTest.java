package com.aplazo.aplazo.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.aplazo.aplazo.TestConstants;
import com.aplazo.customers.controller.CustomersController;
import com.aplazo.customers.exception.error.BadRequestException;
import com.aplazo.customers.exception.error.InternalErrorException;
import com.aplazo.customers.exception.error.NotFoundException;
import com.aplazo.customers.model.request.CustomerRequest;
import com.aplazo.customers.model.response.CustomerResponse;
import com.aplazo.customers.service.CustomerService;

@ExtendWith(MockitoExtension.class)
class CustomersControllerTest {

    @InjectMocks
    private CustomersController customersController;

    @Mock
    private CustomerService customerService;

    @Test
    void testGetCustomerSuccess() throws BadRequestException, NotFoundException {
        
        String customerId = UUID.randomUUID().toString();
        CustomerResponse mockResponse = new CustomerResponse();
        mockResponse.setId(UUID.fromString(customerId));
        mockResponse.setCreditLineAmount(TestConstants.AMOUNT);
        mockResponse.setAvailableCreditLineAmount(TestConstants.CREDIT_LINE_AMOUNT);

        when(customerService.getCustomer(customerId)).thenReturn(mockResponse);

        ResponseEntity<Object> response = customersController.getCustomer(customerId);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        verify(customerService, times(1)).getCustomer(customerId);
    }

    @Test
    void testGetCustomerNotFound() throws BadRequestException, NotFoundException {
        String customerId = UUID.randomUUID().toString();
        when(customerService.getCustomer(customerId)).thenThrow(NotFoundException.class);

        assertThatThrownBy(() -> customersController.getCustomer(customerId))
            .isInstanceOf(NotFoundException.class);

        verify(customerService, times(1)).getCustomer(customerId);
    }

    @Test
    void testGetCustomer_BadRequest() throws BadRequestException, NotFoundException {
        
        when(customerService.getCustomer(TestConstants.ID)).thenThrow(BadRequestException.class);

        assertThatThrownBy(() -> customersController.getCustomer(TestConstants.ID))
            .isInstanceOf(BadRequestException.class);

        verify(customerService, times(1)).getCustomer(TestConstants.ID);
    }

    @Test
    void testCreateCustomerSuccess() throws InternalErrorException {
        // Arrange
        CustomerRequest request = new CustomerRequest();
        request.setFirstName(TestConstants.NAME);
        request.setLastName(TestConstants.LAST_NAME);
        request.setSecondLastNme(TestConstants.SECOND_LAST_NAME);
        request.setDateOfBirth(TestConstants.DATE);

        CustomerResponse mockResponse = new CustomerResponse();
        mockResponse.setId(UUID.randomUUID());
        mockResponse.setCreditLineAmount(TestConstants.AMOUNT);
        mockResponse.setAvailableCreditLineAmount(TestConstants.CREDIT_LINE_AMOUNT);
        mockResponse.setCreatedAt(TestConstants.FULL_DATE);

        when(customerService.saveCustomer(request)).thenReturn(mockResponse);

        ResponseEntity<Object> response = customersController.createCustomer(request);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        verify(customerService, times(1)).saveCustomer(request);
    }

}
