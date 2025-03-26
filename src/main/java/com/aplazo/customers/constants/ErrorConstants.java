package com.aplazo.customers.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access= AccessLevel.PRIVATE)
public class ErrorConstants {

    public static final String INVALID_CUSTOMER_REQUEST = "INVALID_CUSTOMER_REQUEST";

    public static final String CUSTOMER_NOT_FOUND = "CUSTOMER_NOT_FOUND";

    public static final String INVALID_LOAN_REQUEST = "INVALID_LOAN_REQUEST";

    public static final String LOAN_NOT_FOUND = "LOAN_NOT_FOUND";

    public static final String ERROR_DETAIL = "Error detail";
}
