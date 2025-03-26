package com.aplazo.customers.model.response;

import com.aplazo.customers.model.entity.PaymentPlan;
import java.util.List;
import lombok.Data;

@Data
public class PaymentPlanResponse {

    private float commissionAmount;

    private List<InstallmentResponse> installments;

    public PaymentPlanResponse(PaymentPlan paymentPlan) {
        this.commissionAmount = paymentPlan.getCommissionAmount();
        this.installments = paymentPlan.getInstallments()
            .stream().map(InstallmentResponse::new).toList();
    }

}
