package com.aplazo.customers.model.response;

import com.aplazo.customers.model.entity.Installment;
import lombok.Data;

@Data
public class InstallmentResponse {

    private float amount;

    private String scheduledPaymentDate;

    private String status;

    public InstallmentResponse(Installment installmentResponse) {
        this.amount = installmentResponse.getAmount();
        this.scheduledPaymentDate = installmentResponse.getScheduledPaymentDate();
        this.status = installmentResponse.getStatus();
    }
}
