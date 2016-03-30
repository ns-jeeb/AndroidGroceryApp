package com.jeeb.grocerymanager.model;

import java.util.ArrayList;

/**
 * Created by Jeeb on 2/11/2016.
 */
public class Grocery {
    private boolean mIsSelected;
    private String mNames;
    private String mTypes;
    private String mTitle;
    private String mDesc;
    private int mImageSrc;

    public String getDesc() {
        return mDesc;
    }

    public void setDesc(String desc) {
        mDesc = desc;
    }

    public boolean isSelected() {
        return mIsSelected;
    }

    public void setSelected(boolean selected) {
        mIsSelected = selected;
    }

    public String getNames() {
        return mNames;
    }

    public void setNames(String names) {
        mNames = names;
    }

    public String getTypes() {
        return mTypes;
    }

    public void setTypes(String types) {
        mTypes = types;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public int getImageSrc() {
        return mImageSrc;
    }

    public void setImageSrc(int imageSrc) {
        mImageSrc = imageSrc;
    }

    public Grocery() {

    }


}
