package com.example.myapplication.models;

public class exercises_images_class {
    private String id_photos;
    private String images;


    public exercises_images_class(String id_photos, String images) {
        this.id_photos = id_photos;
        this.images = images;
    }

    public exercises_images_class() {
    }

    public String getId_photos() {
        return id_photos;
    }

    public void setId_photos(String id_photos) {
        this.id_photos = id_photos;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return "exercises_images_class{" +
                "id_photos='" + id_photos + '\'' +
                ", images=" + images +
                '}';
    }
}
