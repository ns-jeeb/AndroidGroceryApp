package com.jeeb.grocerymanager.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;

import com.jeeb.grocerymanager.R;
import com.jeeb.grocerymanager.adapters.MeatPartAdapter;
import com.jeeb.grocerymanager.data.DataParser;

import java.util.ArrayList;

import static com.jeeb.grocerymanager.utils.AppUtils.*;

/**
 * A simple {@link Fragment} subclass.
 */
public class LambFragment extends Fragment {
    private View rootView;
    private ArrayList<String> mItems;
    private DataParser mDataParser;

    public LambFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        mItems = getArguments().getStringArrayList(KEY_LAMB);
        rootView = inflater.inflate(R.layout.fragment_my_view_pager, container, false);
        mDataParser = new DataParser();
        ArrayList<String> cleanBaked = mDataParser.manganedBackedItems(mItems,KEY_LAMB);
        MeatPartAdapter adapter = new MeatPartAdapter(getActivity().getLayoutInflater(),cleanBaked,mItems,R.mipmap.lamb_cuts_diargam);
        ListView lv = (ListView) rootView.findViewById(R.id.grocery_items);
        lv.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);
        lv.setAdapter(adapter);
        return rootView;
    }
}
