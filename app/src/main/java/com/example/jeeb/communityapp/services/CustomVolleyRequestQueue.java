package com.example.jeeb.communityapp.services;

import android.app.Activity;
import android.content.Context;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;

/**
 * Created by Jeeb on 6/18/2015.
 */
public class CustomVolleyRequestQueue {

    private static CustomVolleyRequestQueue mInstance;
//    private static Context mCtx;
    private RequestQueue mRequestQueue;

    private CustomVolleyRequestQueue(Activity activity){

        mRequestQueue = getRequestQueue(activity);
    }

    public  static synchronized CustomVolleyRequestQueue getmInstance(Activity activity){
        if (mInstance == null){
            mInstance = new CustomVolleyRequestQueue(activity);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue(Activity activity) {
        if (mRequestQueue == null){
            Cache cache = new DiskBasedCache(activity.getApplication().getCacheDir(), 10 * 1024*1024);
            Network network = new BasicNetwork(new HurlStack());
            mRequestQueue = new RequestQueue(cache, network);
            mRequestQueue.start();
        }
        return mRequestQueue;
    }
}
