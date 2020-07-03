package com.github.grishasht.photostorage.client;

import com.github.grishasht.photostorage.client.model.AccessToken;
import com.github.grishasht.photostorage.model.Image;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ImageClient {

    AccessToken authorize();

    Image getImage(String id);

    List<Image> getImageList(Integer startPage, Integer endPage);

    List<Image> getAllImageList();
}
