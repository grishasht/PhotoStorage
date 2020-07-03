package com.github.grishasht.photostorage.util;

import com.github.grishasht.photostorage.client.ImageClient;
import com.github.grishasht.photostorage.client.factory.ImageClientFactory;
import com.github.grishasht.photostorage.service.ImageService;
import com.github.grishasht.photostorage.service.impl.BasicImageService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Beans {

    @Bean
    public ImageService imageService(){
        return new BasicImageService();
    }

    @Bean
    public ImageClient imageClient(){
        final ImageClientFactory imageClientFactory = new ImageClientFactory();

        return imageClientFactory.createDefaultInstance();
    }

}
