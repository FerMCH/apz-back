package com.aplazo.customers.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access= AccessLevel.PRIVATE)
public class LogConstants {

    public static final String NULL_ATRIBUTE = "El campo no puede ser nulo";

    public static final String CALL_METHOD = "CALL METHOD {}";

    public static final String GET_CUSTOMER = "GET CUSTOMER";

    public static final String GET_LOAN = "GET LOAN";
    
    public static final String CREATE_CUSTOMER = "CREATE CUSTOMER";

    public static final String CREATE_LOAN = "CREATE LOAN";

    public static final String ERROR_LOG = "ERROR: {} -CALL {}";

    public static final String NEW_CUSTOMER = "CREATE NEW CUSTOMER";

    public static final String SAVE_CUSTOMER = "SAVE NEW CUSTOMER AT {}";

    public static final String GET_CUSTOMER_ID = "GET CUSTOMER BY ID:{}";

    public static final String NEW_LOAN = "CREATE NEW LOAN";

    public static final String SAVE_LOAN = "SAVE NEW LOAN AT {}";

    public static final String GET_LOAN_ID = "GET LOAN BY ID:{}";

}
