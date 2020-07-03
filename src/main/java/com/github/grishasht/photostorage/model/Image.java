package com.github.grishasht.photostorage.model;

import java.util.List;

public interface Image {

    Integer getPid();

    String getName();

    String getAuthor();

    String getCamera();

    List<String> getTags();

    String getCroppedPicture();

    String getFullPicture();

}
