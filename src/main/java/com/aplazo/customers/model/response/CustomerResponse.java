package com.aplazo.customers.model.response;

import java.util.UUID;
import lombok.Data;

@Data
public class CustomerResponse {

    private UUID id;
    private String createdAt;
    private float creditLineAmount;
    private float availableCreditLineAmount;

}
