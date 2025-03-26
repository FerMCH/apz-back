package com.aplazo.customers.model.request;

import com.aplazo.customers.constants.LogConstants;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoanRequest {

    @NotNull(message = LogConstants.NULL_ATRIBUTE)
    private String customerId;
    
    @NotNull(message = LogConstants.NULL_ATRIBUTE)
    private float amount;
}
