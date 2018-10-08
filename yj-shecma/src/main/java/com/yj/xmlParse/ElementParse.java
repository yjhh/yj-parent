package com.yj.xmlParse;

import com.yj.ConfigBeanDefinitionParser;
import com.yj.config.Application;
import com.yj.config.ClientConfig;
import com.yj.config.Registry;
import com.yj.config.ServiceConfig;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;


public class ElementParse extends NamespaceHandlerSupport {

  @Override
  public void init() {
    registerBeanDefinitionParser("service",new ConfigBeanDefinitionParser(ServiceConfig.class));
    registerBeanDefinitionParser("application",new ConfigBeanDefinitionParser(Application.class));
    registerBeanDefinitionParser("service",new ConfigBeanDefinitionParser(Registry.class));
    registerBeanDefinitionParser("client",new ConfigBeanDefinitionParser(ClientConfig.class));
  }
}
