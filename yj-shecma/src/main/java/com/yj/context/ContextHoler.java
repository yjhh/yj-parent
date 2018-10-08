package com.yj.context;

import com.yj.config.ClientService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class ContextHoler implements ApplicationContextAware {

  AbstractApplicationContext context;

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    this.context = (AbstractApplicationContext)applicationContext;
    BeanDefinitionBuilder build = BeanDefinitionBuilder.genericBeanDefinition(ClientService.class);
    build.setLazyInit(false);
    build.setScope(ConfigurableBeanFactory.SCOPE_SINGLETON);
    BeanDefinition beanDefinition = build.getBeanDefinition();
    ConfigurableListableBeanFactory factory = context.getBeanFactory();
    factory.initializeBean(beanDefinition.getSource(),ClientService.class.getName().toLowerCase());

  }
}
