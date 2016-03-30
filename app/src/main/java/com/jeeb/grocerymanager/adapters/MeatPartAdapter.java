package com.jeeb.grocerymanager.adapters;

import android.annotation.SuppressLint;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jeeb.grocerymanager.R;
import com.jeeb.grocerymanager.activities.AppSingleton;
import com.jeeb.grocerymanager.fragments.PackageView;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by Jeeb on 11/30/2015.
 */
public class MeatPartAdapter extends ArrayAdapter<String> {
    public SparseBooleanArray mCheckStates;
    int imageRes = 0;
    private ArrayList<String>unCleanItems;
    public MeatPartAdapter(LayoutInflater inflater, List<String> cleanedGroItemLists, ArrayList<String>unMangedItems,int res){
        super(inflater.getContext(), R.layout.items, cleanedGroItemLists);
        unCleanItems = unMangedItems;
        imageRes = res;
        mCheckStates = new SparseBooleanArray(unMangedItems.size());
    }
    public MeatPartAdapter(LayoutInflater inflater, ArrayList<String>unMangedItems,int res){
        super(inflater.getContext(), R.layout.items);
        imageRes = res;
        mCheckStates = new SparseBooleanArray(unMangedItems.size());
    }


    @SuppressLint("Assert")
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View rootView = convertView;
        String item;
        if (rootView == null){
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            rootView = inflater.inflate(R.layout.items, parent, false);
        }
        item = getItem(position);
        final PackageView packageView = (PackageView)rootView;
        packageView.setTag(item);
        TextView mTxtGrocItem = (TextView)packageView.findViewById(R.id.txt_groc_item) ;
        ImageView imageView = (ImageView) packageView.findViewById(R.id.img_gro) ;
        imageView.setImageResource(R.drawable.beeb_short_ribs);
        mTxtGrocItem.setText(item);
        packageView.setCheckedChangedListener(new PackageView.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(PackageView buttonView, boolean isChecked) {
                if (isChecked) {
                    AppSingleton.getInstance().addItemToGroceryList(unCleanItems.get(position));
                } else {
                    AppSingleton.getInstance().removeItemFromGroceryList(unCleanItems.get(position));
                }
            }
        });
        return rootView;
    }

}
