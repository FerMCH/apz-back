package com.aplazo.customers.constants;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "aplazo") 
@Component
@Getter
@Setter
public class Properties {

    private String zone;
    private String secret;
    private String crossorigin;
    

}
