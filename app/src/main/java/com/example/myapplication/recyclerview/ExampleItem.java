package com.example.myapplication.recyclerview;

public class ExampleItem {

    private int imgDelete;
    private String txt_time;
    private int pos;

    public ExampleItem(int imageResource, String text1,int posi) {
        imgDelete = imageResource;
        txt_time = text1;
        pos = posi;
    }

    public int getImgDelete() {
        return imgDelete;
    }

    public String getTxt_time() {
        return txt_time;
    }

    public int getPos() {
        return pos;
    }
}
