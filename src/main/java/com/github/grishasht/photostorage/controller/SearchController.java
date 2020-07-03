package com.github.grishasht.photostorage.controller;

import com.github.grishasht.photostorage.model.Image;
import com.github.grishasht.photostorage.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
public class SearchController {

    private final ImageService imageService;

    @Autowired
    public SearchController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping("/search/query={searchTerm}")
    public List<Image> searchByQuery(@PathVariable("searchTerm") String searchTerm) {

        final Map<String, String> searchParameters = imageService.parseTerm(searchTerm);

        return imageService.getImagesByParameters(searchParameters);
    }

}
