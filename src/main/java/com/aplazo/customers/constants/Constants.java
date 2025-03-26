package com.aplazo.customers.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access= AccessLevel.PRIVATE)
public class Constants {

    public static final long CREDIT_LINE_AMOUNT = 1L;

    public static final long AVAILABLE_CREDIT = 0L;

    public static final String STATUS_ACTIVE = "ACTIVE";

    public static final String STATUS_NEXT = "NEXT";

    public static final long EXPIRATIO_TIME = 86400000;

}
