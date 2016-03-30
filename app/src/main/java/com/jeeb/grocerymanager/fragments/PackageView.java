package com.jeeb.grocerymanager.fragments;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.Checkable;
import android.widget.LinearLayout;

import com.jeeb.grocerymanager.R;

/**
 * Created by Jeeb on 2/11/2016.
 */
public class PackageView extends LinearLayout implements Checkable {
    public static interface OnCheckedChangeListener {
        /**
         * Called when the checked state of a compound button has changed.
         *
         * @param buttonView The compound button view whose state has changed.
         * @param isChecked  The new checked state of buttonView.
         */
        void onCheckedChanged(PackageView buttonView, boolean isChecked);
    }
    private Boolean mChecked = false;
    public OnCheckedChangeListener getCheckedChangedListener() {
        return checkedChangedListener;
    }

    public void setCheckedChangedListener(OnCheckedChangeListener checkedChangedListener) {
        this.checkedChangedListener = checkedChangedListener;
    }

    OnCheckedChangeListener checkedChangedListener;

    public PackageView(Context context) {
        super(context);
    }

    public PackageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PackageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public PackageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean isChecked() {
         return mChecked;
    }

    @Override
    public void setChecked(boolean b) {
        mChecked = b;
        for (int i = 0; i < getChildCount(); i++) {
            if (getChildAt(i) instanceof Checkable){
                Checkable child = (Checkable) getChildAt(i);
                child.setChecked(b);
            }
        }

        if (getCheckedChangedListener()!= null){
            getCheckedChangedListener().onCheckedChanged(this,mChecked);
        }
    }


    @Override
    public void toggle() {
        mChecked = !mChecked;
    }

}
