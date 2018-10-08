package com.yj.config;

import com.yj.annotion.ElementValue;
import com.yj.rule.Rule;
import com.yj.xmlParse.ParseLoadBalance;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.AbstractApplicationContext;

public class ClientConfig implements ApplicationContextAware {

  private String retry;

  @ElementValue(name = "loadBalance",tranform = ParseLoadBalance.class)
  private Rule loadbalance;

  public String getRetry() {
    return retry;
  }

  public void setRetry(String retry) {
    this.retry = retry;
  }

  public Rule getLoadbalance() {
    return loadbalance;
  }

  public void setLoadbalance(Rule loadbalance) {
    this.loadbalance = loadbalance;
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    BeanDefinitionBuilder build = BeanDefinitionBuilder.genericBeanDefinition(ClientService.class);
    build.setLazyInit(true);
    build.setScope(ConfigurableBeanFactory.SCOPE_SINGLETON);
    BeanDefinition beanDefinition = build.getBeanDefinition();
    ConfigurableListableBeanFactory factory = ((AbstractApplicationContext)applicationContext).getBeanFactory();
//    factory.initializeBean(beanDefinition,ClientService.class.getName().toLowerCase());
    factory.registerSingleton(ClientService.class.getName().toLowerCase(),beanDefinition);
  }
}
