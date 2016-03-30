package com.jeeb.grocerymanager.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jeeb.grocerymanager.R;
import com.jeeb.grocerymanager.model.Grocery;
import java.util.ArrayList;
import static com.jeeb.grocerymanager.utils.AppUtils.*;

/**
 * Created by Jeeb on 3/27/2016.
 */

public class GroceryAdapter extends PagerAdapter {
    private ArrayList<String>mGroceryItems;
    private ArrayList<ViewGroup> mViews;
    private LayoutInflater mInflater;

    public GroceryAdapter(Context context, ArrayList<String>groceryItems) {
        mInflater = LayoutInflater.from(context);
        mGroceryItems = groceryItems;
        mViews = new ArrayList<>(mGroceryItems.size());
    }
    @Override
    public Object instantiateItem(final ViewGroup collection, int position) {
        View viewGroup = mInflater.inflate(R.layout.grocery_items,collection,false);
        TextView txtDescView = (TextView)viewGroup.findViewById(R.id.txt_dec_grocery);
        TextView txtGroceryName = (TextView)viewGroup.findViewById(R.id.txt_name_grocery);
        TextView txtTitleView = (TextView)viewGroup.findViewById(R.id.txt_title_grocery);
        ImageView imageView = (ImageView)viewGroup.findViewById(R.id.image_grocery);
        final CheckBox ch = (CheckBox)viewGroup.findViewById(R.id.checked_is_done);
        Grocery groceItems = managingDataToView(position, collection);
        ch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (ch.isChecked()) {
                    Toast.makeText(collection.getContext(),"Grocery is done",Toast.LENGTH_SHORT).show();
                }
            }
        });
        if (!mGroceryItems.isEmpty()){
            imageView.setImageResource (groceItems.getImageSrc()) ;
            txtGroceryName.setText(groceItems.getNames());
            txtTitleView.setText(groceItems.getTitle());
            txtDescView.setText(groceItems.getDesc());
            txtGroceryName.setTextColor(R.color.orange);
            collection.addView(viewGroup,0);
        }

        return viewGroup;
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }

    @Override
    public int getCount() {
        return mGroceryItems.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    public Grocery managingDataToView(int position, ViewGroup collection){
        Grocery grocery = new Grocery();
        String words[];
        String title;
        if (!mGroceryItems.isEmpty()){
            words = mGroceryItems.get(position).split(" ", 2);
            if (words[0].equalsIgnoreCase(KEY_BEEF) || words[0].equalsIgnoreCase(KEY_GOA) || words[0].equalsIgnoreCase(KEY_LAMB) ||
                    words[0].equalsIgnoreCase(KEY_PORK)) {
                title = words[0].replace(words[0], "Meat");
                grocery.setNames(words[1]);
                grocery.setTitle(title);
                grocery.setDesc(collection.getContext().getString(R.string.beef_beef_rump_roast));
                grocery.setImageSrc(R.mipmap.fresh_meat_png);

            } else if (words[0].equalsIgnoreCase(KEY_BREAD)) {
                grocery.setImageSrc(R.mipmap.baked_goods);
                grocery.setTitle(words[0]);
                grocery.setDesc(collection.getContext().getString(R.string.beef_bone_roast));
                grocery.setNames(words[1]);

            } else if (words[0].equalsIgnoreCase(KEY_CHICKEN)) {
                grocery.setImageSrc(R.mipmap.chicken);
                grocery.setTitle(words[0]);
                grocery.setDesc(collection.getContext().getString(R.string.beef_cheeks));
                grocery.setNames(words[1]);

            } else if (words[0].equalsIgnoreCase(KEY_GRAIN)) {
                grocery.setImageSrc(R.mipmap.whole_grains);
                grocery.setTitle(words[0]);
                grocery.setDesc(collection.getContext().getString(R.string.beef_ground));
                grocery.setNames(words[1]);

            } else if (words[0].equalsIgnoreCase(KEY_FRUIT)) {
                grocery.setImageSrc(R.mipmap.fruits);
                grocery.setTitle(words[0]);
                grocery.setDesc(collection.getContext().getString(R.string.beef_ground));
                grocery.setNames(words[1]);

            } else if (words[0].equalsIgnoreCase(KEY_VEGETABLE)) {
                grocery.setImageSrc(R.mipmap.vegetable);
                grocery.setTitle(words[0]);
                grocery.setDesc(collection.getContext().getString(R.string.beef_ground));
                grocery.setNames(words[1]);

            } else if (words[0].equalsIgnoreCase(KEY_DAIRY)) {
                grocery.setImageSrc(R.mipmap.variety_of_dairy);
                grocery.setTitle(words[0]);
                grocery.setDesc(collection.getContext().getString(R.string.beef_ground));
                grocery.setNames(words[1]);

            } else {
                grocery.setImageSrc(R.mipmap.lamb_cuts_diargam);
                grocery.setTitle(words[0]);
                grocery.setDesc(collection.getContext().getString(R.string.beef_ground));
                grocery.setNames(words[1]);
            }
        }
        return grocery;
    }


}
