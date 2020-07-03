package com.github.grishasht.photostorage.service.impl;

import com.github.grishasht.photostorage.client.ImageClient;
import com.github.grishasht.photostorage.model.Image;
import com.github.grishasht.photostorage.model.impl.BasicImage;
import com.github.grishasht.photostorage.repository.BasicImageRepository;
import com.github.grishasht.photostorage.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Primary
public class BasicImageService implements ImageService {

    @Autowired
    private ImageClient imageClient;
    @Autowired
    private BasicImageRepository imageRepository;

    @Override
    public Map<String, String> parseTerm(String searchTerm) {

        final Map<String, String> resultMap = new HashMap<>();

        final String[] parameters = searchTerm.split("&");

        for (String parameter : parameters) {
            final String[] keyAndValue = parameter.split("=");

            resultMap.put(keyAndValue[0], keyAndValue[1]);
        }

        return resultMap;
    }

    /**
     * This method should check if images with such parameters exist in the
     * database first and if it doesn't than this method downloads images from api,
     * saves them in the database and returns lists of needed entities if such exists.
     *
     * @param searchParameters map with parameters for searching.
     * @return list of wanted entities.
     */
    @Override
    public List<Image> getImagesByParameters(Map<String, String> searchParameters) {

        // Here we need to add logic which checks if database is updated and contains
        // last entities.

        final List<Image> allDBImageList = getAllFromDB();

        for (Image image : allDBImageList) {

            for (Map.Entry<String, String> entry : searchParameters.entrySet()) {

                final String key = entry.getKey();
                final String value = entry.getValue();

                // Here we need to find by key and value all needed entities;

            }

        }

        // This must be if database doesn't contain needed entities.
        final List<Image> allImageList = imageClient.getAllImageList();

        allImageList.stream()
                .map(BasicImage.class::cast)
                .forEach(imageRepository::save);



        return null;
    }

    @Override
    public List<Image> getAllFromDB() {
        final Iterable<BasicImage> imageIterable = imageRepository.findAll();

        return StreamSupport.stream(imageIterable.spliterator(), false)
                .collect(Collectors.toList());
    }


}
