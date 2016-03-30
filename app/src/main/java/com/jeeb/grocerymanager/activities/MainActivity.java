package com.jeeb.grocerymanager.activities;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.jeeb.grocerymanager.utils.AppUtils.*;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.jeeb.grocerymanager.fragments.BakedFragment;
import com.jeeb.grocerymanager.fragments.BeefFragment;
import com.jeeb.grocerymanager.fragments.ChickenFragment;
import com.jeeb.grocerymanager.fragments.DairyFragment;
import com.jeeb.grocerymanager.fragments.FruitFragment;
import com.jeeb.grocerymanager.fragments.GoatFragment;
import com.jeeb.grocerymanager.fragments.GrainFragment;
import com.jeeb.grocerymanager.fragments.PorkFragment;
import com.jeeb.grocerymanager.fragments.SeafoodFragment;
import com.jeeb.grocerymanager.fragments.VegetableFragment;
import com.jeeb.grocerymanager.data.DataParser;
import com.jeeb.grocerymanager.R;
import com.jeeb.grocerymanager.fragments.LambFragment;
import com.jeeb.grocerymanager.model.Beef;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static Bundle mArgement;
    private PagerAdapter mMeatPagerAdapter, mVegeFruitAdapter;
    private ViewPager mMeatPager;
    private ViewPager mVegeFruitPager;
    private List<String>beefItems;
    private List<String>chicItems;
    private List<String>lambItems;
    private List<String>goatItems;
    private List<String>porkItems;
    private List<String>grainItems;
    private List<String>seafoodItems;
    private List<String> dairyItems;
    private List<String>vegeItems;
    private List<String>bakedItems;
    private List<String>fruitItems;
    private DataParser mDataParser;
    private ArrayList<Fragment>mFragments = new ArrayList<>();
    private Button mBtn;

    public static Fragment saveData(Fragment fragment, String keyArg, ArrayList<String> items){
        mArgement = new Bundle();
        mArgement.putStringArrayList(keyArg, items);
        fragment.setArguments(mArgement);
        return fragment;
    }
    TextView testView;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
        String url = "https://api.mongolab.com/api/1/databases/grocery/collections/food/?apiKey=LcybTMW4H3ULBfJsl-ai7lrc1TwOR0U7";
        mBtn = (Button)findViewById(R.id.btn_create_list) ;
        testView =(TextView)findViewById(R.id.tsTxt);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                mDataParser = new DataParser();
                List<String> mItems = mDataParser.items(response);

                beefItems = new ArrayList<>();
                chicItems = new ArrayList<>();
                lambItems = new ArrayList<>();
                goatItems = new ArrayList<>();
                porkItems = new ArrayList<>();
                vegeItems = new ArrayList<>();
                fruitItems = new ArrayList<>();
                grainItems = new ArrayList<>();
                bakedItems = new ArrayList<>();
                seafoodItems = new ArrayList<>();
                dairyItems = new ArrayList<>();
                if (!mItems.isEmpty() && mItems.size() != -1){
                    for (String item: mItems){
                        if (item.contains(KEY_BEEF)){
                            beefItems.add(item);

                        }else if (item.contains(KEY_LAMB)){
                            lambItems.add(item);

                        }else if (item.contains(KEY_GOA)){
                            goatItems.add(item);

                        }else if (item.contains(KEY_CHICKEN)){
                            chicItems.add(item);

                        }else if (item.contains(KEY_PORK)){
                            porkItems.add(item);
                        }else if (item.contains(KEY_FRUIT)){
                            fruitItems.add(item);
                        }else if (item.contains(KEY_VEGETABLE)){
                            vegeItems.add(item);
                        }
                        else if (item.contains(KEY_SEAFOOD)){
                            seafoodItems.add(item);
                        }
                         else if (item.contains(KEY_GRAIN)){
                            grainItems.add(item);
                        }
                         else if (item.contains(KEY_DAIRY)){
                            dairyItems.add(item);
                        }
                         else if (item.contains(KEY_BREAD)){
                            bakedItems.add(item);
                        }
                        else if(item.contains(KEY_ROLLS)){
                            bakedItems.add(item);
                        }
                        else if(item.contains(KEY_BUN)){
                            bakedItems.add(item);
                        }
                         else if(item.contains(KEY_SNACK)){
                            bakedItems.add(item);
                        }
                        else if(item.contains(KEY_CAKES)){
                            bakedItems.add(item);
                        }
                        else if(item.contains(KEY_PASTRY)){
                            bakedItems.add(item);
                        }

                    }
                }

                createdView();
                mBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ArrayList<String>list = AppSingleton.getInstance().getGroceryList();
                        testView.setText(list.toString());
                        mDataParser.saveData(getApplicationContext(),AppSingleton.getInstance().getGroceryList(),KEY_FILE);
                        Intent intent = new Intent(MainActivity.this, TestActivityForTest.class);
                        intent.putStringArrayListExtra("GroceryList",list);
                        startActivity(intent);
                    }
                });

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        AppSingleton.getInstance().getRequestQueue().add(jsonArrayRequest);
    }
    public void createdView(){
        TabLayout meatTabLayout = (TabLayout) findViewById(R.id.meat_tab_layout);

        meatTabLayout.addTab(meatTabLayout.newTab().setText(KEY_BEEF));
        meatTabLayout.addTab(meatTabLayout.newTab().setText(KEY_LAMB));
        meatTabLayout.addTab(meatTabLayout.newTab().setText(KEY_GOA));
        meatTabLayout.addTab(meatTabLayout.newTab().setText(KEY_CHICKEN));
        meatTabLayout.addTab(meatTabLayout.newTab().setText(KEY_PORK));
        meatTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        mMeatPager = (ViewPager) findViewById(R.id.meat_pager);
        mMeatPagerAdapter = new MeatSlidePagerAdapter(getSupportFragmentManager(),meatTabLayout.getTabCount());
        mMeatPager.setAdapter(mMeatPagerAdapter);
        mMeatPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(meatTabLayout));
        meatTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mMeatPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        TabLayout vegeFruitTabLayout = (TabLayout) findViewById(R.id.vege_fruit_tab_layout);
        vegeFruitTabLayout.addTab(vegeFruitTabLayout.newTab().setText(KEY_FRUIT));
        vegeFruitTabLayout.addTab(vegeFruitTabLayout.newTab().setText(KEY_VEGETABLE));
        vegeFruitTabLayout.addTab(vegeFruitTabLayout.newTab().setText(KEY_GRAIN));
        vegeFruitTabLayout.addTab(vegeFruitTabLayout.newTab().setText(KEY_SEAFOOD));
        vegeFruitTabLayout.addTab(vegeFruitTabLayout.newTab().setText(KEY_BAKED));
        vegeFruitTabLayout.addTab(vegeFruitTabLayout.newTab().setText(KEY_DAIRY));
        vegeFruitTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        mVegeFruitPager = (ViewPager)findViewById(R.id.vege_fruit_pager);
        mVegeFruitAdapter = new VegFruitSlidPagerAdapter(getSupportFragmentManager(),vegeFruitTabLayout.getTabCount());
        mVegeFruitPager.setAdapter(mVegeFruitAdapter);
        mVegeFruitPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(vegeFruitTabLayout));

        vegeFruitTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mVegeFruitPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


    private class MeatSlidePagerAdapter extends FragmentStatePagerAdapter {
        int NUM_PAGES;
        public MeatSlidePagerAdapter(FragmentManager fm, int tabNumber) {
            super(fm);
            NUM_PAGES = tabNumber;
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment;
            switch (position) {

                case 0:
                    fragment = saveData(new BeefFragment(), KEY_BEEF    , (ArrayList<String>) beefItems);
                    mFragments.add(fragment);
                    return fragment;
                case 1:
                    fragment = saveData(new LambFragment(),   KEY_LAMB   , (ArrayList<String>) lambItems);
                    return fragment;
                case 2:
                    fragment = saveData(new GoatFragment(),   KEY_GOA    , (ArrayList<String>) goatItems);
                    return fragment;
                case 3:
                    fragment = saveData(new ChickenFragment(),KEY_CHICKEN , (ArrayList<String>) chicItems);
                    return fragment;
                case 4:
                    fragment = saveData(new PorkFragment(),   KEY_PORK    , (ArrayList<String>) porkItems);
                    return fragment;

                default:
                   return new BeefFragment();
            }
        }
        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }

    public class VegFruitSlidPagerAdapter extends FragmentStatePagerAdapter{
        int NUM_PAGES;
        public VegFruitSlidPagerAdapter(FragmentManager fm, int tabNumber) {
            super(fm);
            NUM_PAGES = tabNumber;
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment;
            switch (position) {

                case 0:
                    fragment = saveData(new FruitFragment(),    KEY_FRUIT     , (ArrayList<String>) fruitItems);
                    return fragment;
                case 1:
                    fragment = saveData(new VegetableFragment(),KEY_VEGETABLE, (ArrayList<String>) vegeItems);
                    return fragment;
                case 2:
                    fragment = saveData(new GrainFragment(),KEY_GRAIN, (ArrayList<String>) grainItems);
                    return fragment;
                case 3:
                    fragment = saveData(new SeafoodFragment(),KEY_SEAFOOD, (ArrayList<String>) seafoodItems);
                    return fragment;
                case 4:
                    fragment = saveData(new BakedFragment(),KEY_BAKED, (ArrayList<String>) bakedItems);
                    return fragment;
                case 5:
                    fragment = saveData(new DairyFragment(),KEY_DAIRY, (ArrayList<String>) dairyItems);
                    return fragment;

                default:
                    return new ChickenFragment();
            }
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }

}
