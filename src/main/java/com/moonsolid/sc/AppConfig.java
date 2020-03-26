package com.moonsolid.sc;

import org.springframework.context.annotation.ComponentScan;

@ComponentScan(value = "com.moonsolid.sc")
public class AppConfig {

  public AppConfig() {
    System.out.println("AppConfig 객체 생성");
  }

}
