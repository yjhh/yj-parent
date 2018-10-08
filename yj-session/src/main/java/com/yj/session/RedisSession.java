package com.yj.session;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Proxy;

@RestController
public class RedisSession {

    @GetMapping("/session")
    public String getSession(HttpServletRequest request){

        Test proxy = (Test)Proxy.newProxyInstance(MyInvocationHandler.class.getClassLoader(),new Class[]{Test.class},new MyInvocationHandler());
        System.out.println(proxy.my());
        HttpSession session = request.getSession(true);
        return session.getId();
    }

}
