package com.hshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"spring.library.common","com.hshop"})
public class HApplication {

  public static void main(String[] args) {
    SpringApplication.run(HApplication.class, args);
  }

}
