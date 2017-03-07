package com.example.vineetjain.googlesignin.Syllabus;

/**
 * Created by Vineet Jain on 27-02-2017.
 */

public class SyllabusFirebaseModel  {
    String images,titles;

    public SyllabusFirebaseModel(){

    }

    public SyllabusFirebaseModel(String images, String titles) {
        this.images = images;
        this.titles = titles;
    }

    public String getImages() {

        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getTitles() {
        return titles;
    }

    public void setTitles(String titles) {
        this.titles = titles;
    }
}
