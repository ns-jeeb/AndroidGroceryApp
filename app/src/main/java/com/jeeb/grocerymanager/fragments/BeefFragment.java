package com.jeeb.grocerymanager.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Toast;

import static com.jeeb.grocerymanager.utils.AppUtils.*;

import com.jeeb.grocerymanager.R;
import com.jeeb.grocerymanager.adapters.MeatPartAdapter;
import com.jeeb.grocerymanager.data.DataParser;

import java.util.ArrayList;

public class BeefFragment extends Fragment {
    protected View rootView;
    private ArrayList<String> mItems;
    private DataParser mDataParser = new DataParser();
    protected ArrayList<String>cleanedTexts;
    protected ListView mListView;
    protected MeatPartAdapter mAdapter;
    public BeefFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        mItems = getArguments().getStringArrayList(KEY_BEEF);
        rootView = inflater.inflate(R.layout.fragment_my_view_pager, container, false);
        cleanedTexts = mDataParser.manganedBackedItems(mItems,KEY_BEEF);
        mAdapter = new MeatPartAdapter(getActivity().getLayoutInflater(),cleanedTexts, mItems,R.mipmap.beef);
        mListView = (ListView) rootView.findViewById(R.id.grocery_items);
        mListView.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);

        mListView.setAdapter(mAdapter);
        return rootView;
    }
}
