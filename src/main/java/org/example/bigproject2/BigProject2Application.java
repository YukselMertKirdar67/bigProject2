package org.example.bigproject2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class BigProject2Application {

    public static void main(String[] args) {
        SpringApplication.run(BigProject2Application.class, args);
    }

}
