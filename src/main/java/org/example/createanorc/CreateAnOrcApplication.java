package org.example.createanorc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class CreateAnOrcApplication {

    public static void main(String[] args) {
        SpringApplication.run(CreateAnOrcApplication.class, args);
    }

}
