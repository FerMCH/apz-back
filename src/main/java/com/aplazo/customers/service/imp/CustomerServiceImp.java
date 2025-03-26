package com.aplazo.customers.service.imp;

import com.aplazo.customers.constants.Constants;
import com.aplazo.customers.constants.ErrorConstants;
import com.aplazo.customers.constants.LogConstants;
import com.aplazo.customers.constants.Properties;
import com.aplazo.customers.constants.StandarConstants;
import com.aplazo.customers.exception.error.BadRequestException;
import com.aplazo.customers.exception.error.InternalErrorException;
import com.aplazo.customers.exception.error.NotFoundException;
import com.aplazo.customers.model.entity.Customer;
import com.aplazo.customers.model.request.CustomerRequest;
import com.aplazo.customers.model.response.CustomerResponse;
import com.aplazo.customers.repository.CustomerRepository;
import com.aplazo.customers.service.CustomerService;
import com.aplazo.customers.utils.Utils;
import jakarta.persistence.EntityNotFoundException;

import java.time.Instant;
import java.util.UUID;
import javax.naming.NameNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class CustomerServiceImp implements CustomerService {

    private CustomerRepository customerRepository;

    private Properties properties;

    /**
     * {@inheritDoc}
     * @throws InternalErrorException 
     */
    @Override
    public CustomerResponse saveCustomer(CustomerRequest customer) throws InternalErrorException {

        log.info(LogConstants.NEW_CUSTOMER);

        Customer newCustomer = new Customer();
        newCustomer.setFirstName(customer.getFirstName());
        newCustomer.setLastName(customer.getLastName());
        newCustomer.setSecondLastNme(customer.getSecondLastNme());
        newCustomer.setDateOfBirth(customer.getDateOfBirth());
        newCustomer.setCreditLineAmount(Constants.CREDIT_LINE_AMOUNT);
        newCustomer.setAvailableCreditLineAmount(Constants.AVAILABLE_CREDIT);
        String date = Utils.actualDate(Instant.now(), this.properties.getZone(), StandarConstants.FULL_DATE);
        newCustomer.setCreatedAt(date);

        Customer saveCustomer = this.customerRepository.save(newCustomer);

        

        CustomerResponse response = new CustomerResponse();

        response.setAvailableCreditLineAmount(saveCustomer.getAvailableCreditLineAmount());
        response.setCreatedAt(saveCustomer.getCreatedAt());
        response.setCreditLineAmount(saveCustomer.getCreditLineAmount());
        response.setId(saveCustomer.getId());

        log.info(LogConstants.SAVE_CUSTOMER, date);

        return response;
    }

    /**
     * {@inheritDoc}
    * @throws BadRequestException 
    * @throws NameNotFoundException 
    */
    @Override
    public CustomerResponse getCustomer(String id) throws BadRequestException, NotFoundException {

        Customer customer;
        
        CustomerResponse response = new CustomerResponse();
        log.info(LogConstants.GET_CUSTOMER_ID, id);
        try {
            customer = this.customerRepository.getReferenceById(UUID.fromString(id));
            response.setAvailableCreditLineAmount(customer.getAvailableCreditLineAmount());
            response.setCreatedAt(customer.getCreatedAt());
            response.setCreditLineAmount(customer.getCreditLineAmount());
            response.setId(customer.getId());
        } catch (NumberFormatException e) {
            throw new BadRequestException(ErrorConstants.INVALID_CUSTOMER_REQUEST, ErrorConstants.ERROR_DETAIL);
        } catch (EntityNotFoundException e) {
            throw new NotFoundException(ErrorConstants.CUSTOMER_NOT_FOUND, ErrorConstants.ERROR_DETAIL);
        }
        
        return response;
    }

}
