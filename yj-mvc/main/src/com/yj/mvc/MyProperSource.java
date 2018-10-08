package com.yj.mvc;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MyProperSource extends MapPropertySource {

//    public MyProperSource(Map<String, Object> source){
//        super("my",source);
//    }
    public MyProperSource(){
        super("mySource",new HashMap<>());
    }

    public MyProperSource(String name, Map<String, Object> source) {
        super(name, source);
    }

    @Override
    public Object getProperty(String name) {
        return this.source.get(name);
    }

    public void refresh(){
        this.source.put("test","test"+Math.random());
    }

}
