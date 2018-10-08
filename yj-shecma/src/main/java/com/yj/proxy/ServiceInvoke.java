package com.yj.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ServiceInvoke implements InvocationHandler {

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    Map<String,String> map = new HashMap<>();
    map.put("key","value");
    return map;
  }
}
