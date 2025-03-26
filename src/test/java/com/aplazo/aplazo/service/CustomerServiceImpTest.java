package com.aplazo.aplazo.service;

import com.aplazo.aplazo.TestConstants;
import com.aplazo.customers.constants.Constants;
import com.aplazo.customers.constants.Properties;
import com.aplazo.customers.constants.StandarConstants;
import com.aplazo.customers.exception.error.BadRequestException;
import com.aplazo.customers.exception.error.InternalErrorException;
import com.aplazo.customers.exception.error.NotFoundException;
import com.aplazo.customers.model.entity.Customer;
import com.aplazo.customers.model.request.CustomerRequest;
import com.aplazo.customers.model.response.CustomerResponse;
import com.aplazo.customers.repository.CustomerRepository;
import com.aplazo.customers.service.imp.CustomerServiceImp;
import com.aplazo.customers.utils.Utils;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImpTest {

    @InjectMocks
    private CustomerServiceImp customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private Properties properties;

    @Test
    void testSaveCustomer() throws InternalErrorException {
        CustomerRequest request = new CustomerRequest();
        request.setFirstName(TestConstants.NAME);
        request.setLastName(TestConstants.LAST_NAME);
        request.setSecondLastNme(TestConstants.SECOND_LAST_NAME);
        request.setDateOfBirth(TestConstants.DATE);

        when(properties.getZone()).thenReturn(TestConstants.ZONE);
        when(customerRepository.save(any(Customer.class))).thenAnswer(invocation -> {
            Customer customer = invocation.getArgument(0);
            customer.setId(UUID.randomUUID());
            customer.setCreatedAt(Utils.actualDate(Instant.now(), TestConstants.ZONE, StandarConstants.FULL_DATE));
            return customer;
        });

        CustomerResponse response = customerService.saveCustomer(request);

        assertThat(response).isNotNull();

        verify(customerRepository, times(1)).save(any(Customer.class));
    }

    @Test
    void testGetCustomerSuccess() throws BadRequestException, NotFoundException, InternalErrorException {
        
        UUID uuid = UUID.fromString(TestConstants.ID);

        Customer customer = new Customer();
        customer.setId(uuid);
        customer.setCreatedAt(Utils.actualDate(Instant.now(), TestConstants.ZONE, StandarConstants.FULL_DATE));
        customer.setCreditLineAmount(Constants.CREDIT_LINE_AMOUNT);
        customer.setAvailableCreditLineAmount(Constants.AVAILABLE_CREDIT);

        when(customerRepository.getReferenceById(uuid)).thenReturn(customer);
        CustomerResponse response = customerService.getCustomer(TestConstants.ID);

        assertThat(response).isNotNull();

        verify(customerRepository, times(1)).getReferenceById(uuid);
    }

    @Test
    void testGetCustomerInvalidId() {
        try (MockedStatic<UUID> mockedUUID = mockStatic(UUID.class)) {
            mockedUUID.when(() -> UUID.fromString(TestConstants.STRING_VOID)).thenThrow(NumberFormatException.class);

            assertThatThrownBy(() -> customerService.getCustomer(TestConstants.STRING_VOID))
                    .isInstanceOf(BadRequestException.class);

            verify(customerRepository, never()).getReferenceById(any());
        }
    }

    @Test
    void testGetCustomerNotFound() {

        UUID uuid = UUID.fromString(TestConstants.ID);

        when(customerRepository.getReferenceById(uuid)).thenThrow(new EntityNotFoundException());

        assertThatThrownBy(() -> customerService.getCustomer(TestConstants.ID))
                .isInstanceOf(NotFoundException.class);

        verify(customerRepository, times(1)).getReferenceById(uuid);
    }
}