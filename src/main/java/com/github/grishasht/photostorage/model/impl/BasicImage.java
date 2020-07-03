package com.github.grishasht.photostorage.model.impl;

import com.github.grishasht.photostorage.model.Image;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "images")
public class BasicImage implements Image {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;

    public BasicImage() {
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }


}
