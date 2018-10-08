package com.yj.stream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.messaging.Message;

@SpringBootApplication
//@EnableBinding({Source.class,Sink.class})
public class StreamApplication {

    public static void main(String ... args){
        SpringApplication app = new SpringApplication(StreamApplication.class);
        app.run(args);
    }

//    @InboundChannelAdapter(Source.OUTPUT)
//    public String setMessage(){
//        return "hello";
//    }


}
