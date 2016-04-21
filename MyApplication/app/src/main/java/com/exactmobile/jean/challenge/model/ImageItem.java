package com.exactmobile.jean.challenge.model;

/**
 * Created by jmvnkuru on 20/04/16.
 */
public class ImageItem {
    private String imageUrl;
    private String imageDescription;
    private String title;


    public ImageItem(String imageUrl, String imageDescription, String title) {
        this.imageUrl = imageUrl;
        this.imageDescription = imageDescription;
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageDescription() {
        return imageDescription;
    }

    public void setImageDescription(String imageDescription) {
        this.imageDescription = imageDescription;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
