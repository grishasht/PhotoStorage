package com.github.grishasht.photostorage.model;

import java.util.List;

public interface Image {

    Integer getPId();

    String getId();

    String getAuthor();

    String getCamera();

    List<String> getTags();

    String getCroppedPicture();

    String getFullPicture();

}
