package com.tyroo.tyroovastdemoapp;

/**
 * Created by sukhpalsingh on 21/02/18.
 */

public class ImageDto {
    private int imageId;
    private String description;

    public ImageDto(int imageId, String description) {
        this.imageId = imageId;
        this.description = description;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
