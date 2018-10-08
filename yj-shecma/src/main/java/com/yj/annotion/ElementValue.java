package com.yj.annotion;

import com.yj.xmlParse.ParseFunc;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ElementValue {

   String name() default "";

   boolean nullable() default false;

   Class<? extends ParseFunc> tranform() default Default.class;

   class Default implements ParseFunc<String>{

      @Override
      public String parse(String value, Class<String> aClass) {
         return (String)value;
      }
   }
}
