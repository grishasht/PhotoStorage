package com.github.grishasht.photostorage.model.impl;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    private Integer pid;
    @JsonProperty("id")
    private String name;
    private String author;
    private String camera;
    private String tags;
    @JsonProperty("cropped_picture")
    private String croppedPicture;
    @JsonProperty("full_picture")
    private String fullPicture;

    public BasicImage() {
    }

    @Override
    public Integer getPid() {
        return pid;
    }

    @Override
    public String getName() {
        return name;
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
        return croppedPicture;
    }

    @Override
    public String getFullPicture() {
        return fullPicture;
    }

    @Override
    public String toString() {
        return "BasicImage{" +
                "id=" + pid +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", camera='" + camera + '\'' +
                ", tags='" + tags + '\'' +
                ", croppedPicture='" + croppedPicture + '\'' +
                ", fullPicture='" + fullPicture + '\'' +
                '}';
    }
}
