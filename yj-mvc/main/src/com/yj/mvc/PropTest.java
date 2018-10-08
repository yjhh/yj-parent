package com.yj.mvc;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("propTest")
public class PropTest implements InitializingBean {
    @Value("${test}")
    private String test;

    @ResponseBody
    @GetMapping("test")
    public String get(){
        return test;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("init");
    }
}
