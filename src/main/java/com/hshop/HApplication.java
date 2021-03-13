package com.hshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@ComponentScan(basePackages = {"spring.library.common","com.hshop"})
@PropertySource(value = {"classpath:application.properties"})
@EnableScheduling
public class HApplication {

  public static void main(String[] args) {
    SpringApplication.run(HApplication.class, args);
  }

}
