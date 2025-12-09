package com.gzy.rpsm.property;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.gzy.rpsm.property.mapper")

public class PropertyApplication {
    public static void main(String[] args) {
        SpringApplication.run(PropertyApplication.class, args);
    }

}
