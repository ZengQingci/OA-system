package com.object.oasystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(value = "com.object.oasystem.mapper")
@SpringBootApplication
public class OaSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(OaSystemApplication.class, args);
    }

}
