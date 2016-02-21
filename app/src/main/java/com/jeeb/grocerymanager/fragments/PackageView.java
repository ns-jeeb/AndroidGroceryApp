package com.jeeb.grocerymanager.fragments;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.jeeb.grocerymanager.R;

import java.util.ArrayList;

/**
 * Created by Jeeb on 2/11/2016.
 */
public class PackageView extends LinearLayout implements Checkable {
    private Holder mHolder;
    private Boolean mChecked = false;
    private static ArrayList<String> mSelectedItems;

    public PackageView(Context context) {
        super(context);
        mHolder = new Holder();
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.items, this, true);
        mHolder.img = (ImageView) v.findViewById(R.id.img_gro);
        mHolder.chItem = (CheckBox) v.findViewById(R.id.chb_grocery);
        mSelectedItems = new ArrayList<>();
    }

    public void setPackage(String item) {
        // my custom method where I set package id, date, and time
        mHolder.chItem.setText(item);
//        mHolder.chItem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                Log.d("onCheckedchanged"," called first");
//                if (isChecked){
//                    Toast.makeText(getContext(),"is checked",Toast.LENGTH_LONG).show();
//                    mSelectedItems.add(mHolder.chItem.getText().toString());
//
//                }else {
//                    Toast.makeText(getContext(),"is Un checked",Toast.LENGTH_LONG).show();
//                    mSelectedItems.remove(mHolder.chItem.getText().toString());
//                }
//            }
//        });
    }

    @Override
    public boolean isChecked() {
         return mChecked;
    }

    @Override
    public void setChecked(boolean b) {
        if (b != mChecked) {
            mChecked = b;
            refreshDrawableState();
        }

        for (int i = 0; i < getChildCount(); i++) {

            mHolder.chItem = (CheckBox) getChildAt(i);
            if (mHolder.chItem != null) {
                mHolder.chItem.setChecked(b);
            }
            Log.d("inside loop","is clicked");
        }
    }

    @Override
    public void toggle() {
        mChecked = !mChecked;
         mHolder.chItem.toggle();
    }


    public void createSelectedItem(){
        if (mSelectedItems != null){
            for (String s: mSelectedItems){
                Log.d("List comes ",s);
            }
            Log.d("List size "," "+mSelectedItems.size());
        }
    }

    public static class Holder {
        ImageView img;
        CheckBox chItem;
    }
}
