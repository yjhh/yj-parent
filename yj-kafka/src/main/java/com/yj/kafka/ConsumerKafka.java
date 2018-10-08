package com.yj.kafka;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ConsumerKafka {

    public static void main(String ... args){
        Map<String,Object> consumerConfig = new HashMap<>();
        consumerConfig.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class);
        consumerConfig.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class);
        consumerConfig.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"127.0.0.1:9092");
        consumerConfig.put(ConsumerConfig.CLIENT_ID_CONFIG,"consumer");
        consumerConfig.put(ConsumerConfig.GROUP_ID_CONFIG,"consumer");
        consumerConfig.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,"false");
        consumerConfig.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");

        Consumer<String,String> consumer = new KafkaConsumer<String, String>(consumerConfig);

//        consumer.seekToBeginning(Arrays.asList(new TopicPartition(ProducerKafka.topic1,0),new TopicPartition(ProducerKafka.topic2,0)));

        consumer.subscribe(Arrays.asList(ProducerKafka.topic1,ProducerKafka.topic2));

        while(true){
            ConsumerRecords<String,String> recodes =  consumer.poll(100);
            for(ConsumerRecord<String,String> recod:recodes){
                System.out.printf("offset=%s,key=%s,value=%s\r\n",recod.offset(),recod.key(),recod.value());
            }

        }
    }

}
