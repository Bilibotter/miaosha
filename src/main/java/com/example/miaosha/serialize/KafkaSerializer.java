package com.example.miaosha.serialize;

import com.example.miaosha.entity.Stock;
import org.apache.kafka.common.serialization.Serializer;
import org.nustaq.serialization.FSTConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

public class KafkaSerializer implements Serializer<Stock> {
    private static FSTConfiguration configuration;

    static {
        configuration = FSTConfiguration.createDefaultConfiguration();
        configuration.registerClass(Stock.class);
    }

    @Override
    public byte[] serialize(String s, Stock stock) {
        return configuration.asByteArray(stock);
    }
}
