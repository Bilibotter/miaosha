package com.example.miaosha.serialize;

import com.example.miaosha.entity.Stock;
import org.nustaq.serialization.FSTConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SerializeConfiguration {
    @Bean
    public FSTConfiguration fstConfiguration() {
        FSTConfiguration configuration = FSTConfiguration.createDefaultConfiguration();
        configuration.registerClass(Stock.class);
        return configuration;
    }
}
