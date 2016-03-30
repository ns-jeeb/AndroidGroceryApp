package com.jeeb.grocerymanager.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.jeeb.grocerymanager.R;
import com.jeeb.grocerymanager.adapters.GroceryAdapter;

import java.util.ArrayList;

/**
 * Created by Jeeb on 3/27/2016.
 */
public class TestActivityForTest extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grocery_layout);
        Intent i = this.getIntent();
        ArrayList<String> mItems = i.getStringArrayListExtra("GroceryList");
        Log.d("ARGUMENT= ::::",""+mItems);
        ViewPager viewPager = (ViewPager)super.findViewById(R.id.view_pager);
        viewPager.setAdapter(new GroceryAdapter(this,mItems));

//        FragmentManager fm = getSupportFragmentManager();
//        Fragment ff = new GroceryFragment();
//        FragmentTransaction ft = fm.beginTransaction();
//        ft.add(R.id.container,ff);
//        ft.commit();
    }
}
