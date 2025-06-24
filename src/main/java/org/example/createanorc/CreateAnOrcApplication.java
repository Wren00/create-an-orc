package org.example.createanorc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class CreateAnOrcApplication {

    public static void main(String[] args) {
        SpringApplication.run(CreateAnOrcApplication.class, args);
    }

}
