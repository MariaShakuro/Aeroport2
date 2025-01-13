package com.aviation.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.aviation.core"})
public class FlightServiceApplication {
    public static void main(String[] args) {

        SpringApplication.run(FlightServiceApplication.class, args);

    }

}
