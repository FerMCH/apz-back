package com.aplazo.customers.service;

import java.time.Instant;

import com.aplazo.customers.exception.error.InternalErrorException;
import com.aplazo.customers.model.entity.PaymentPlan;

public interface PaymentPlanService {

    PaymentPlan savPaymentPlan(Instant now, float creditLineAmount) throws InternalErrorException;

}
