package com.jeeb.grocerymanager.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.jeeb.grocerymanager.R;
import com.jeeb.grocerymanager.adapters.MeatPartAdapter;
import com.jeeb.grocerymanager.data.DataParser;

import java.util.ArrayList;

import static com.jeeb.grocerymanager.utils.AppUtils.*;

public class BakedFragment extends Fragment {
    private ArrayList<String> mItems;
    private View rootView;
    private DataParser mDataParser = new DataParser();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_my_view_pager, container, false);
        Bundle bundle = getArguments();
        mItems = bundle.getStringArrayList(KEY_BAKED);
        ArrayList<String>cleanBaked = mDataParser.manganedBackedItems( mItems,KEY_BREAD+" ");
        MeatPartAdapter adapter = new MeatPartAdapter(getActivity().getLayoutInflater(), cleanBaked,mItems,R.mipmap.baked_goods);
        ListView lv = (ListView) rootView.findViewById(R.id.grocery_items);
        lv.setAdapter(adapter);
        return rootView;

    }



}
