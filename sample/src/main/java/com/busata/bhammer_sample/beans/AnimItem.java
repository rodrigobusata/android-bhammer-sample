package com.busata.bhammer_sample.beans;


public class AnimItem {

    private String mTitle;
    private String mText;
    private int mImage;

    public AnimItem(String title, String text, int image) {
        this.mTitle = title;
        this.mText = text;
        this.mImage = image;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        this.mText = text;
    }

    public int getImage() {
        return mImage;
    }

    public void setImage(int image) {
        this.mImage = image;
    }
}
