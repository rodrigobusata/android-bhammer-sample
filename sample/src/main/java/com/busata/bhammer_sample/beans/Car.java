package com.busata.bhammer_sample.beans;

import com.busata.bhammer.beans.BObject;


public class Car extends BObject {
    private String mModel;
    private String mBrand;
    private int mPhoto;


    public Car() {
    }

    public Car(String model, String brand, int photo) {
        mModel = model;
        mBrand = brand;
        mPhoto = photo;
    }


    public String getModel() {
        return mModel;
    }

    public void setModel(String model) {
        this.mModel = model;
    }

    public String getBrand() {
        return mBrand;
    }

    public void setBrand(String brand) {
        this.mBrand = brand;
    }

    public int getPhoto() {
        return mPhoto;
    }

    public void setPhoto(int photo) {
        this.mPhoto = photo;
    }
}
