package com.example.miaosha.serialize;

import com.example.miaosha.entity.Stock;
import org.apache.kafka.common.serialization.Deserializer;
import org.nustaq.serialization.FSTConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class KafkaDeserializer implements Deserializer<Stock> {

    private static FSTConfiguration configuration;

    static {
        configuration = FSTConfiguration.createDefaultConfiguration();
        configuration.registerClass(Stock.class);
    }

    @Override
    public Stock deserialize(String s, byte[] bytes) {
        return (Stock) configuration.asObject(bytes);
    }
}
