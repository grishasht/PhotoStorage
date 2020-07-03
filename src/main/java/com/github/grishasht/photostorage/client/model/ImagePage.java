package com.github.grishasht.photostorage.client.model;

import com.github.grishasht.photostorage.model.impl.BasicImage;

import java.util.List;

public class ImagePage {
    private List<Image> pictures;
    private Integer page;
    private Integer pageCount;
    private Boolean hasMore;

    public List<Image> getPictures() {
        return pictures;
    }

    public Integer getPage() {
        return page;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public Boolean getHasMore() {
        return hasMore;
    }
}
