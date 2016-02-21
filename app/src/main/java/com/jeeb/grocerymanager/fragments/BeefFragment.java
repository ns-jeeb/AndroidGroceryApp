package com.jeeb.grocerymanager.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;

import static com.jeeb.grocerymanager.utils.AppUtils.*;
import com.jeeb.grocerymanager.R;
import com.jeeb.grocerymanager.adapters.MeatPartAdapter;
import com.jeeb.grocerymanager.data.DataParser;
import com.jeeb.grocerymanager.model.GroceryItem;

import java.util.ArrayList;

public class BeefFragment extends Fragment {
    private View rootView;
    private GroceryItem mGroceryItem;
    private ArrayList<String> mItems;
    private String eachItem = null;
    private DataParser mDataParser = new DataParser();
    private ArrayList<String>selectedItems;
    private ArrayList<String>cleanedTexts;
    private ListView mListView;
    private MeatPartAdapter mAdapter;

    public BeefFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        final String fileName = "myBlog.txt";
        mItems = this.getArguments().getStringArrayList(KEY_BEEF);
        rootView = inflater.inflate(R.layout.fragment_my_view_pager, container, false);
        cleanedTexts = mDataParser.manganedBackedItems(mItems,KEY_BEEF);
//        mDataParser.saveData(getContext(),"{name:Aziz, last Name: Sakhizada}",fileName);
        mAdapter = new MeatPartAdapter(getActivity().getLayoutInflater(),cleanedTexts, mItems,R.mipmap.beef);
        mListView = (ListView) rootView.findViewById(R.id.grocery_items);
        mListView.setAdapter(mAdapter);
        selectedItems = new ArrayList<>();
//        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                CheckBox checkBox = (CheckBox)view.findViewById(R.id.chb_grocery);
//                if (mGroceryItem.isSelected()){
//                    selectedItems.add(cleanedTexts.get(position));
//                }else {
//                    checkBox.setChecked(checkBox.isChecked());
//                }

//                if unchecked remove the items from the list
//                if (!checkBox.isChecked()){
//                    selectedItems.remove(position);
//                    mListView.clearChoices();
//                    Log.d("position****","position = ==="+selectedItems.get(position)+ " is removed");
//                }else {
//                    Log.d("position****","position = ==="+selectedItems.get(position));
//                }

//            }
//        });
        return rootView;
    }

}
