package com.yj.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.HashMap;
import java.util.Map;

public class ProducerKafka {

     final static String topic1 = "topic-1";
     final static String topic2 = "topic-2";

    public static void main(String ... args){
        Map<String,Object> producerConfig = new HashMap<>();
        producerConfig.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,StringSerializer.class);
        producerConfig.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class);
        producerConfig.put(ProducerConfig.ACKS_CONFIG,"all");
        producerConfig.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"127.0.0.1:9092");
        producerConfig.put(ProducerConfig.CLIENT_ID_CONFIG,"127");
        producerConfig.put(ProducerConfig.RETRIES_CONFIG,"5");

        Producer<String,String> producer = new KafkaProducer<String, String>(producerConfig);
        ProducerRecord<String,String > recoder = new ProducerRecord<>(topic1,topic1);
        producer.send(recoder);
        recoder = new ProducerRecord<>(topic2,topic2);
        producer.send(recoder);
    }

}
