package com.github.grishasht.photostorage.client;

import com.github.grishasht.photostorage.client.model.AccessToken;
import com.github.grishasht.photostorage.model.Image;

import java.util.List;

public interface ImageClient {

    AccessToken authorize();

    Image getImage(String id);

    List<Image> getImageList(Integer startPage, Integer endPage);

    List<Image> getAllImageList();
}
