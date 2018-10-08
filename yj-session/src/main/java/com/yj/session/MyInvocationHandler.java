package com.yj.session;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyInvocationHandler implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(proxy.getClass());
//        System.out.println(proxy);
        proxy = TestImpl.class.newInstance();
        Object value = method.invoke(proxy,args);
        return value.toString() + "success";
    }

    public static void main(String[] args) throws InterruptedException {
//        Test proxy = (Test)Proxy.newProxyInstance(MyInvocationHandler.class.getClassLoader(),new Class[]{Test.class},new MyInvocationHandler());
//        proxy.my();
        String s = "msgo.minshengec.com ".trim();
        byte[] bytes = s.getBytes();
        char[] chars = s.toCharArray();
    }

}
