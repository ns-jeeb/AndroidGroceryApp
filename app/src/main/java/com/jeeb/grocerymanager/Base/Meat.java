package com.jeeb.grocerymanager.Base;

/**
 * Created by Jeeb on 3/24/2016.
 */
public class Meat {
    private String mName;
    private long mImageId;
    private String mType;

    public long getImageId() {
        return mImageId;
    }

    public void setImageId(long imageId) {
        mImageId = imageId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

    @Override
    public String toString() {
        return mName+mType;
    }
}
