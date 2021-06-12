package com.example.miaosha.seralize;

import com.example.miaosha.entity.Stock;
import org.nustaq.serialization.FSTConfiguration;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class SerializeConfig {
    @Bean
    public FSTConfiguration fstConfiguration() {
        FSTConfiguration configuration = FSTConfiguration.createDefaultConfiguration();
        configuration.registerClass(Stock.class);
        return configuration;
    }
}
