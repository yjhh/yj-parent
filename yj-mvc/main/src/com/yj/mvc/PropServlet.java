package com.yj.mvc;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.PropertyResourceConfigurer;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.util.PropertyPlaceholderHelper;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class PropServlet  {

    @Value("${test}")
    private String test;

    @Resource
    private ConfigurableEnvironment environment;
    @Resource
    private MyProperSource myProperSource;
    @PostConstruct
    public void init(){
        System.out.println("post");
    }

    @GetMapping("/prop")
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.getWriter().write(test);
        resp.getWriter().close();
    }

    @GetMapping("/change")
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        PropChange.context.refresh();
//        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory)PropChange.context.getBeanFactory();
//        beanFactory.removeBeanDefinition("propInit");
//
//        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(PropChange.class);
//        beanFactory.registerBeanDefinition("propInit",beanDefinitionBuilder.getBeanDefinition());
//        PropChange propChange = PropChange.context.getBean("propInit",PropChange.class);
//        propChange.loadProperties(PropChange.properties);
//        propChange.processProperties(PropChange.context.getBeanFactory(),PropChange.properties);
//        propChange.postProcessBeanFactory(PropChange.context.getBeanFactory());
//
//        PropTest test = PropChange.context.getBean("propTest",PropTest.class);
//        AutowireCapableBeanFactory factory = PropChange.context.getAutowireCapableBeanFactory();
//        factory.destroyBean(test);
//        factory.initializeBean(test,"propTest");
//        factory.destroyBean(propChange);
//        factory.initializeBean(propChange,"propInit");
        myProperSource.refresh();
        environment.getPropertySources().addLast(myProperSource);

    }

}
