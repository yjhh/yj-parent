package com.yj.schemas;

import com.yj.config.ClientService;
import com.yj.service.ConfigParseService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

  public static void main(String[] args) {
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
    ClientService clientService = context.getBean(ClientService.class);
    ConfigParseService service = context.getBean(ConfigParseService.class);
    System.out.println(service.getConfig());
  }
}
