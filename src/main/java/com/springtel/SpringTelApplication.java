package com.springtel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class SpringTelApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringTelApplication.class, args);
    }

}
