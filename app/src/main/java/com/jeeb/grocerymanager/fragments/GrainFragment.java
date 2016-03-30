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

public class GrainFragment extends Fragment {
        private ArrayList<String> mItems;
        private View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_my_view_pager, container, false);
        Bundle bundle = getArguments();
        mItems = bundle.getStringArrayList(KEY_GRAIN);
        DataParser mDataParser = new DataParser();
        ArrayList<String>cleanedTexts = mDataParser.manganedBackedItems(mItems,KEY_GRAIN);
        MeatPartAdapter adapter = new MeatPartAdapter(getActivity().getLayoutInflater(),cleanedTexts, mItems,R.mipmap.whole_grains);
        ListView lv = (ListView) rootView.findViewById(R.id.grocery_items);
        lv.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);
        lv.setAdapter(adapter);
        return rootView;

    }

}
