package com.aplazo.customers.model.request;

import com.aplazo.customers.constants.LogConstants;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CustomerRequest {

    @NotNull(message = LogConstants.NULL_ATRIBUTE)
    private String firstName;

    @NotNull(message = LogConstants.NULL_ATRIBUTE)
    private String lastName;
    
    @NotNull(message = LogConstants.NULL_ATRIBUTE)
    private String secondLastNme;

    @NotNull(message = LogConstants.NULL_ATRIBUTE)
    private String dateOfBirth;

}
