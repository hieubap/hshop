package com.hshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan(basePackages = {"spring.library.common","com.hshop"})
@EnableScheduling
public class HApplication {

  public static void main(String[] args) {
    SpringApplication.run(HApplication.class, args);
  }

}
