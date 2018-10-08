package com.yj.stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.messaging.Message;

@SpringBootApplication
//@EnableKafkaStreams
@EnableBinding(Sink.class)
public class StreamInput {

    public static void main(String ... args){
        SpringApplication.run(StreamInput.class,args);
    }

//    @Autowired
//    Sink sink;

    @StreamListener(Sink.INPUT)
    public void getMessage(Message message){
        System.out.println(message.getPayload().toString());
    }

}
