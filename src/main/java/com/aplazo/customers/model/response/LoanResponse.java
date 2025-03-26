package com.aplazo.customers.model.response;

import com.aplazo.customers.model.entity.Loan;
import java.util.UUID;
import lombok.Data;

@Data
public class LoanResponse {

    private UUID id;

    private UUID customerId;

    private String status;

    private String createdAt;

    private PaymentPlanResponse paymentPlan;

    public LoanResponse(Loan loan) {
        this.id = loan.getId();
        this.customerId = loan.getCustomerId();
        this.status = loan.getStatus();
        this.createdAt = loan.getCreatedAt();
        this.paymentPlan = 
            new PaymentPlanResponse(loan.getPaymentPlan());
    }

}
