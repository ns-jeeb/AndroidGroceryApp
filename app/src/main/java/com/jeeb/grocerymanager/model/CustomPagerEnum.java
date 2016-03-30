package com.jeeb.grocerymanager.model;

import com.jeeb.grocerymanager.R;

import java.util.ArrayList;

/**
 * Created by Jeeb on 2/11/2016.
 */
public enum  CustomPagerEnum {
    RED(R.string.red),
    BLUE(R.string.blue),
    GREEN(R.string.blue),
    YELLOW(R.string.blue),
    ORANGE(R.string.orange);

    private int mTitleResId;
    private int mLayoutResId;

    CustomPagerEnum(int titleResId) {
        mTitleResId = titleResId;
    }

    public int getTitleResId() {
        return mTitleResId;
    }

    public int getLayoutResId() {
        return mLayoutResId;
    }
}
