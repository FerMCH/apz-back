package com.aplazo.customers.service.imp;

import com.aplazo.customers.constants.Constants;
import com.aplazo.customers.constants.Properties;
import com.aplazo.customers.constants.StandarConstants;
import com.aplazo.customers.exception.error.InternalErrorException;
import com.aplazo.customers.model.entity.Installment;
import com.aplazo.customers.model.entity.PaymentPlan;
import com.aplazo.customers.repository.PaymentPlanRepository;
import com.aplazo.customers.service.InstallmentService;
import com.aplazo.customers.service.PaymentPlanService;
import com.aplazo.customers.utils.Utils;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class PaymentPlanServiceImp implements PaymentPlanService {

    private PaymentPlanRepository paymentPlanRepository;
    
    private InstallmentService installmentService;

    private Properties properties;

    @Override
    public PaymentPlan savPaymentPlan(Instant now, float creditLineAmount) throws InternalErrorException {
        List<Installment> installments = new ArrayList<>();
        
        Installment installment = new Installment();
        installment.setAmount(Constants.CREDIT_LINE_AMOUNT);
        installment.setScheduledPaymentDate(Utils.actualDate(now, this.properties.getZone(), StandarConstants.SHORT_DATE));
        installment.setStatus(Constants.STATUS_NEXT);
        Installment savedInstallment = this.installmentService.saveInstallment(installment);

        
        installments.add(savedInstallment);
        PaymentPlan paymentPlan = new PaymentPlan();
        paymentPlan.setCommissionAmount(creditLineAmount);
        paymentPlan.setInstallments(installments);

        return this.paymentPlanRepository.save(paymentPlan);
    }

}
