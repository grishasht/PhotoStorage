package com.github.grishasht.photostorage.client.impl;

import com.github.grishasht.photostorage.client.ImageClient;
import com.github.grishasht.photostorage.client.exception.ImageClientException;
import com.github.grishasht.photostorage.client.factory.ImageClientFactory;
import com.github.grishasht.photostorage.client.model.AccessToken;
import com.github.grishasht.photostorage.client.model.ApiKey;
import com.github.grishasht.photostorage.client.model.ImagePage;
import com.github.grishasht.photostorage.model.Image;
import com.github.grishasht.photostorage.model.impl.BasicImage;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class ImageClientImpl implements ImageClient {

    private final static String AUTH_URI = "http://interview.agileengine.com/auth";
    private final static String IMAGES_URI = "http://interview.agileengine.com/images";


    private static final String HEADER_MEDIA_TYPE = MediaType.APPLICATION_JSON;
    private static final String HEADER_AUTH = "Authorization";
    public static final String QUERY_PARAM_PAGE = "page";

    private final String apiKey;
    private final Client restClient = ClientBuilder.newClient();

    private AccessToken accessToken;

    public ImageClientImpl(String apiKey) {
        this.apiKey = apiKey;
        this.accessToken = authorize();
    }

    @Override
    public AccessToken authorize() {

        return restClient
                .target(AUTH_URI)
                .request(HEADER_MEDIA_TYPE)
                .post(Entity.entity(new ApiKey(apiKey), HEADER_MEDIA_TYPE))
                .readEntity(AccessToken.class);
    }

    @Override
    public Image getImage(String id) {

        final Response response = getImageResponse(id);

        final int responseStatus = response.getStatus();

        if (isSuccessful(responseStatus)) {

            return response.readEntity(BasicImage.class);

        } else{

            throw new ImageClientException(responseStatus);
        }
    }

    private Response getImageResponse(String id) {
        return restClient
                .target(IMAGES_URI)
                .path(id)
                .request(HEADER_MEDIA_TYPE)
                .header(HEADER_AUTH, "Bearer " + accessToken.getToken())
                .get();
    }

    @Override
    public List<Image> getImageList(Integer startPage, Integer endPage) {

        final List<Image> resultList = new LinkedList<>();

        int currentPage = startPage;

        while(currentPage <= endPage) {

            final Response response = getImagesResponse(currentPage);

            final int responseStatus = response.getStatus();

            if (isSuccessful(responseStatus)) {

                final ImagePage imagePage = response.readEntity(ImagePage.class);

                final List<Image> resultImages = getImagesFromPage(imagePage);

                resultList.addAll(resultImages);

                if (currentPage == imagePage.getPageCount()){

                    return resultList;
                }

                currentPage++;
            } else {

                throw new ImageClientException(responseStatus);
            }
        }

        return resultList;
    }

    private List<Image> getImagesFromPage(ImagePage imagePage) {
        final List<com.github.grishasht.photostorage.client.model.Image> imageList = imagePage.getPictures();

        return imageList.stream()
                .map(com.github.grishasht.photostorage.client.model.Image::getId)
                .map(this::getImage)
                .collect(Collectors.toList());
    }

    private Response getImagesResponse(int currentPage) {
        return restClient
                .target(IMAGES_URI)
                .queryParam(QUERY_PARAM_PAGE, currentPage)
                .request(HEADER_MEDIA_TYPE)
                .header(HEADER_AUTH, "Bearer " + accessToken.getToken())
                .get();
    }

    @Override
    public List<Image> getAllImageList() {

        final List<Image> resultList = new LinkedList<>();

        int currentPage = 0;

        final Response firstResponse = getImagesResponse(currentPage);
        final ImagePage firstPage = firstResponse.readEntity(ImagePage.class);
        final Integer lastPageNumber = firstPage.getPageCount();

        if (lastPageNumber > 0 ) {
            resultList.addAll(getImagesFromPage(firstPage));
        }

        while(currentPage <= lastPageNumber) {

            final Response response = getImagesResponse(currentPage);

            final int responseStatus = response.getStatus();

            if (isSuccessful(responseStatus)) {

                final ImagePage imagePage = response.readEntity(ImagePage.class);

                final List<Image> resultImages = getImagesFromPage(imagePage);

                resultList.addAll(resultImages);

                currentPage++;
            } else {

                throw new ImageClientException(responseStatus);
            }
        }

        return resultList;
    }

    private boolean isSuccessful(int responseStatus) {
        return responseStatus / 100 == 2;
    }

    public static void main(String[] args) {
        final ImageClientFactory factory = new ImageClientFactory();
        final ImageClient client = factory.createDefaultInstance();

        final List<Image> allImageList = client.getAllImageList();

        allImageList.forEach(System.out::println);
    }
}
