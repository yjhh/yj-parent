package com.yj.config;

import com.yj.proxy.ServiceInvoke;
import com.yj.service.ConfigParseService;
import java.lang.reflect.Proxy;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

public class ClientService implements FactoryBean<ConfigParseService>, InitializingBean {

  @Autowired
  private ClientConfig clientConfig;

  @Override
  public ConfigParseService getObject() throws Exception {
    return (ConfigParseService)Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),new Class[]{ConfigParseService.class},new ServiceInvoke());
  }

  @Override
  public Class<?> getObjectType() {
    return ConfigParseService.class;
  }

  @Override
  public boolean isSingleton() {
    return true;
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    System.out.println("11111");
  }
}
