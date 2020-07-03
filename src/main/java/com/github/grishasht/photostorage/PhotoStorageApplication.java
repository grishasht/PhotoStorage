package com.github.grishasht.photostorage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class PhotoStorageApplication {

    public static void main(String[] args) {
        SpringApplication.run(PhotoStorageApplication.class, args);
    }

}
