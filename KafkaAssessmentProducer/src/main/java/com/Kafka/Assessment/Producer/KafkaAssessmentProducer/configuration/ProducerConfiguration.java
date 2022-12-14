package com.Kafka.Assessment.Producer.KafkaAssessmentProducer.configuration;



import com.Kafka.Assessment.Producer.KafkaAssessmentProducer.constant.KafkaConstant;
import com.Kafka.Assessment.Producer.KafkaAssessmentProducer.model.Movie;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class ProducerConfiguration {


    @Bean // Configuration for Producer
    public Map<String, Object> producerConfig( ){
        Map<String,Object> config = new HashMap<>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConstant.BROKER); // Kafka Server URL
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class); // Data encoding for key
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);// Data encoding for value
        System.out.println(config);
        return config;
    }

    @Bean
    public ProducerFactory<String, Movie> producerFactory(){
        return new DefaultKafkaProducerFactory<>(
                producerConfig() // method for creating "Producer Instances"  on Kafka Broker
        );
    }

    @Bean
    public KafkaTemplate<String,Movie> kafkaTemplate(){
        return new KafkaTemplate<>(producerFactory());
    }

}
