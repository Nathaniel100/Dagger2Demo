package com.wufan.dagger2demo.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by wufan on 16/3/15.
 */
public class ITunesResult implements Serializable{

    @SerializedName("collectionName")
    private String collectionName;

    private String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    @Override
    public String toString() {
        return collectionName;
    }
}
