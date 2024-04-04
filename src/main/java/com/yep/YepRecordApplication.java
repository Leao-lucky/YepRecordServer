package com.yep;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class YepRecordApplication {

   public static void main(String[] args) {
      SpringApplication.run(YepRecordApplication.class, args);
   }
   

}