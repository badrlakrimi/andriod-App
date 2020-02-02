package com.example.myapplication.models;

public class exercices_desc {

    private String series;
    private String textDesc;
    private String video;

    public exercices_desc(String series, String video,String textDesc) {
        this.series = series;
        this.textDesc = textDesc;
        this.video = video;
    }

    public exercices_desc() {
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String gettextDesc() {
        return textDesc;
    }

    public void settextDesc(String textDesc) {
        this.textDesc = textDesc;
    }

    public String getvideo() {
        return video;
    }

    public void setvideo(String video) {
        this.video = video;
    }

    @Override
    public String toString() {
        return "exercices_desc{" +
                "series='" + series + '\'' +
                ", textDesc='" + textDesc + '\'' +
                ", video='" + video + '\'' +
                '}';
    }
}
