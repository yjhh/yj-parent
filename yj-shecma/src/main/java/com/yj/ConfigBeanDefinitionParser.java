package com.yj;

import com.yj.annotion.ElementValue;
import com.yj.xmlParse.ParseFunc;
import java.lang.reflect.Field;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

public class ConfigBeanDefinitionParser implements BeanDefinitionParser {

  private Class beanClass;

  public ConfigBeanDefinitionParser(Class beanClass){
    this.beanClass = beanClass;
  }

  @Override
  public BeanDefinition parse(Element element, ParserContext parserContext) {
    GenericBeanDefinition definition = new GenericBeanDefinition();
    definition.setBeanClass(beanClass);
    definition.setAbstract(false);
    definition.setLazyInit(false);
    definition.setScope(ConfigurableBeanFactory.SCOPE_SINGLETON);
    Field[] fields = beanClass.getDeclaredFields();
    for (Field field:fields){
      ElementValue value = field.getAnnotation(ElementValue.class);
      if (value == null){

        String elementAttribute = element.getAttribute(field.getName());
        if (elementAttribute == null || "".equals(elementAttribute)){
          throw new RuntimeException(beanClass.getName() + field.getName() + "can not be null");
        }
        definition.setAttribute(field.getName(),element.getAttribute(field.getName()));
      }else {
        String name = value.name();
        if (StringUtils.isEmpty(name)){
          name = field.getName();
        }
        String elemValue = element.getAttribute(name);
        if (!value.nullable() && elemValue == null){
          throw new RuntimeException(name + "can not be null");
        }

        try {
          ParseFunc fun = value.tranform().newInstance();
          definition.setAttribute(field.getName(),fun.parse(elemValue,field.getDeclaringClass()));
        } catch (InstantiationException e) {
          e.printStackTrace();
        } catch (IllegalAccessException e) {
          e.printStackTrace();
        }

      }
    }

    BeanDefinitionReaderUtils.registerBeanDefinition(new BeanDefinitionHolder(definition,parserContext.getReaderContext().generateBeanName(definition)),parserContext.getRegistry());
    return definition;
  }
}
