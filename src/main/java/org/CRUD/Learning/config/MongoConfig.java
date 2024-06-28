package org.CRUD.Learning.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class MongoConfig {

    @Bean
    public MongoCustomConversions customConversions() {
        List<Object> converters = new ArrayList<>();
        // Add any other custom converters here
        return new MongoCustomConversions(converters);
    }
}
