package com.github.grishasht.photostorage.model.impl;

import com.github.grishasht.photostorage.model.Image;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "images")
public class BasicImage implements Image {
    @Id
    @GeneratedValue
    private Integer pId;
    private String id;
    private String author;
    private String camera;
    private String tags;
    private String cropped_picture;
    private String full_picture;

    public BasicImage() {
    }

    public Integer getPId() {
        return pId;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getAuthor() {
        return author;
    }

    @Override
    public String getCamera() {
        return camera;
    }

    @Override
    public List<String> getTags() {
        return List.of(tags.split(" "));
    }

    @Override
    public String getCroppedPicture() {
        return cropped_picture;
    }

    @Override
    public String getFullPicture() {
        return full_picture;
    }
}
