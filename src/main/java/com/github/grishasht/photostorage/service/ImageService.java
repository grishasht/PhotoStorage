package com.github.grishasht.photostorage.service;

import com.github.grishasht.photostorage.model.Image;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface ImageService {

    Map<String, String> parseTerm(String searchTerm);

    List<Image> getImagesByParameters(Map<String, String> searchParameters);

    List<Image> getAllFromDB();
}
