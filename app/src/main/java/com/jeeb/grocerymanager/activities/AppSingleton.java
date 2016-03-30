package com.jeeb.grocerymanager.activities;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jeeb on 11/30/2015.
 */
public class AppSingleton extends Application {
    private static AppSingleton sInstance;
    private RequestQueue mRequestQueue;
    private ArrayList<String> mGroceryList;

    @Override
    public void onCreate() {
        super.onCreate();
        mRequestQueue = Volley.newRequestQueue(this);
        mGroceryList = new ArrayList<>();
        sInstance = this;
    }

    public synchronized static AppSingleton getInstance() {
        return sInstance;
    }

    public RequestQueue getRequestQueue() {
        return mRequestQueue;
    }

    public ArrayList<String> getGroceryList() {
        return mGroceryList;
    }

    public void setGroceryList(ArrayList<String> groceryList) {
        mGroceryList = groceryList;
    }

    public void addItemToGroceryList(String item){
        if (mGroceryList.contains(item)){
            return;
        }
        mGroceryList.add(item);
    }

    public void removeItemFromGroceryList(String item) {
        mGroceryList.remove(item);
    }


}
