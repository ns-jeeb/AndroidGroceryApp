package com.jeeb.grocerymanager.adapters;

import android.annotation.SuppressLint;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import com.jeeb.grocerymanager.R;
import com.jeeb.grocerymanager.fragments.PackageView;
import com.jeeb.grocerymanager.model.GroceryItem;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by Jeeb on 11/30/2015.
 */
public class MeatPartAdapter extends ArrayAdapter<String> {
    ArrayList<String>unCleanedItems;
    public SparseBooleanArray mCheckStates;
    int imageRes = 0;
    public MeatPartAdapter(LayoutInflater inflater, List<String> cleanedGroItemLists, ArrayList<String>unMangedItems,int res){
        super(inflater.getContext(), R.layout.items, cleanedGroItemLists);
        unCleanedItems = unMangedItems;
        imageRes = res;
        mCheckStates = new SparseBooleanArray(unMangedItems.size());
    }


    @SuppressLint("Assert")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rootView = convertView;
        String item;
        if (rootView == null){
            rootView = new PackageView(getContext());
            item = getItem(position);
            PackageView packageView = (PackageView)rootView;
            packageView.setPackage(item);

        }

        return rootView;
    }

}
