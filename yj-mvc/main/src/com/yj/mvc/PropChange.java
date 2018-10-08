package com.yj.mvc;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.AbstractXmlApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ConfigurableWebApplicationContext;
import org.springframework.web.context.support.AbstractRefreshableWebApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;

import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;

@Component("propInit")
//@org.springframework.context.annotation.PropertySource(value = "defined.properties",ignoreResourceNotFound=true)
public class PropChange extends PropertyPlaceholderConfigurer implements ApplicationContextAware {

    public static ConfigurableWebApplicationContext context;
    public static Properties properties;

    public PropChange(){
        this.trimValues=true;
    }

    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props) throws BeansException {
        super.processProperties(beanFactoryToProcess, props);
        properties = props;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (applicationContext instanceof ConfigurableWebApplicationContext){
            context = (ConfigurableWebApplicationContext)applicationContext;
        }
    }

    @Override
    protected void loadProperties(Properties props) throws IOException {
        super.loadProperties(props);
        Random random = new Random();
        props.setProperty("test","test"+random.nextInt(100));
    }

    public static void refresh(){
        ClassPathXmlApplicationContext c = new ClassPathXmlApplicationContext(context.getConfigLocations());
        MutablePropertySources propertySources = c.getEnvironment().getPropertySources();
        MutablePropertySources sources = context.getEnvironment().getPropertySources();
        for(PropertySource source:propertySources){
            if(sources.contains(source.getName())){
                sources.replace(source.getName(),source);
            }else{
                sources.addLast(source);
            }
        }
        c.stop();
        AutowireCapableBeanFactory factory = context.getAutowireCapableBeanFactory();
        PropTest test = factory.getBean("propTest",PropTest.class);
        factory.destroyBean(test);
        factory.initializeBean(test,"propTest");
    }
}
