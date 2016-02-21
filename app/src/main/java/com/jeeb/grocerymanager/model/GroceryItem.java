package com.jeeb.grocerymanager.model;

/**
 * Created by Jeeb on 2/11/2016.
 */
public class GroceryItem {
    private boolean mIsSelected;
    private String mName;

    public void setName(String name) {
        mName = name;
    }

    private int mImageSrc;

    public GroceryItem(String name) {
        mName = name;
    }

    public boolean isSelected() {
        return mIsSelected;
    }

    public void setSelected(boolean selected) {
        mIsSelected = selected;
    }

    public String getName() {
        return mName;
    }

    public int getImageSrc() {
        return mImageSrc;
    }

    public void setImageSrc(int imageSrc) {
        mImageSrc = imageSrc;
    }
}
