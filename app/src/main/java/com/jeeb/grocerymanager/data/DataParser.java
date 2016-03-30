package com.jeeb.grocerymanager.data;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jeeb.grocerymanager.model.Beef;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import static com.jeeb.grocerymanager.utils.AppUtils.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jeeb on 11/29/2015.
 */
public class DataParser {
    public List<String> items(JSONArray jsonArray){
        List<String>groceryItems = new ArrayList<>();
        List<JSONArray>listOfGrocery = new ArrayList<>();
        if (jsonArray != null){
            for (int j =0; j<jsonArray.length(); j++){
                try {
                    JSONObject jsArray = jsonArray.getJSONObject(j);
                    JSONObject jsMeats = jsArray.optJSONObject("Grocery");
                    JSONObject jsBeef = jsMeats.optJSONObject(KEY_BEEF);
                    JSONObject jsLamb = jsMeats.optJSONObject(KEY_LAMB);
                    JSONObject jsGoat = jsMeats.optJSONObject(KEY_GOA);
                    JSONObject jsChic = jsMeats.optJSONObject(KEY_CHICKEN);
                    JSONObject jsPork = jsMeats.optJSONObject(KEY_PORK);
                    JSONObject jsVege = jsMeats.optJSONObject(KEY_VEGETABLE);
                    JSONObject jsFruit = jsMeats.optJSONObject(KEY_FRUIT);
                    JSONObject jsSeaFoot = jsMeats.optJSONObject(KEY_SEAFOOD);
                    JSONObject jsGrain = jsMeats.optJSONObject(KEY_GRAIN);
                    JSONObject jsDairy = jsMeats.optJSONObject(KEY_DAIRY);
                    JSONObject jsBaked = jsMeats.optJSONObject(KEY_BAKED);

                    listOfGrocery.add(jsBeef.getJSONArray   (KEY_PARTS));
                    listOfGrocery.add(jsLamb.getJSONArray   (KEY_PARTS));
                    listOfGrocery.add(jsGoat.getJSONArray   (KEY_PARTS));
                    listOfGrocery.add(jsChic.getJSONArray   (KEY_PARTS));
                    listOfGrocery.add(jsPork.getJSONArray   (KEY_PARTS));
                    listOfGrocery.add(jsFruit.getJSONArray  (KEY_PARTS));
                    listOfGrocery.add(jsVege.getJSONArray   (KEY_PARTS));
                    listOfGrocery.add(jsGrain.getJSONArray  (KEY_PARTS));
                    listOfGrocery.add(jsSeaFoot.getJSONArray(KEY_PARTS));
                    listOfGrocery.add(jsDairy.getJSONArray  (KEY_PARTS));
                    listOfGrocery.add(jsBaked.getJSONArray  (KEY_PARTS));
                    groceryItems = manuplateJsArray(listOfGrocery);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        return groceryItems;

    }
    public List<String> manuplateJsArray(List<JSONArray>jsonArrays){
        List<String>lists = new ArrayList<>();

        for (JSONArray jsA : jsonArrays){
            JSONArray jsonArray = jsA;
            for (int i=0; i<jsonArray.length();i++){
                lists.add(jsonArray.optString(i));
            }
        }
        return lists;
    }
    public ArrayList<String> manganedBackedItems(ArrayList<String>items,String removedText){
        ArrayList<String> cleanedItems = new ArrayList<>();

        if (items.isEmpty()){
            return null;

        }else {
            for (String item : items){
                if (item.contains(removedText)) {
                    cleanedItems.add(item.replace(removedText, ""));
                }
                else if (item.contains(KEY_ROLLS+" ")) {
                    cleanedItems.add(item.replace(KEY_ROLLS+" ", ""));
                } else if (item.contains(KEY_BUN+" ")) {
                    cleanedItems.add(item.replace(KEY_BUN+" ", ""));
                } else if (item.contains(KEY_SNACK+" ")) {
                    cleanedItems.add(item.replace(KEY_SNACK+" ", ""));
                } else if (item.contains(KEY_CAKES+" ")) {
                    cleanedItems.add(item.replace(KEY_CAKES+" ", ""));
                } else if (item.contains(KEY_PASTRY+" ")) {
                    cleanedItems.add(item.replace(KEY_PASTRY+" ", ""));
                }
//                this is uncommented if use object
//                else if (item.getName().contains(KEY_ROLLS+" ")) {
//                    temS = item.getName().replace(KEY_ROLLS+" ", "");
//                    item.setName(temS);
//                    cleanedItems.add(item);
//                } else if (item.getName().contains(KEY_BUN+" ")) {
//                    temS = item.getName().replace(KEY_BUN+" ", "");
//                    item.setName(temS);
//                    cleanedItems.add(item);
//                } else if (item.getName().contains(KEY_SNACK+" ")) {
//                    temS = item.getName().replace(KEY_SNACK+" ", "");
//                    item.setName(temS);
//                    cleanedItems.add(item);
//                } else if (item.getName().contains(KEY_CAKES+" ")) {
//                    temS = item.getName().replace(KEY_CAKES+" ", "");
//                    item.setName(temS);
//                    cleanedItems.add(item);
//                } else if (item.getName().contains(KEY_PASTRY+" ")) {
//                    temS = item.getName().replace(KEY_PASTRY+" ", "");
//                    item.setName(temS);
//                    cleanedItems.add(item);
//                }
            }
            return cleanedItems;
        }
    }

//    static String fileName = "myBlog.json";

    public void saveData(Context context, List<String> items, String fileName) {
        try {
            FileWriter file = new FileWriter(context.getFilesDir().getPath() + "/" + fileName);
            Gson gson = new GsonBuilder().create();
            String st = gson.toJson(items);
            file.write(st);
            Log.e("TAG", "JSON: " + st);
            file.flush();
            file.close();
        } catch (IOException e) {
            Log.e("TAG", "Error in Writing: " + e.getLocalizedMessage());
        }
    }

    public String getData(Context context,String fileName) {
        try {
            File f = new File(context.getFilesDir().getPath() + "/" + fileName);

            FileInputStream is = new FileInputStream(f);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            return new String(buffer);
        } catch (IOException e) {
            Log.e("TAG", "Error in Reading: " + e.getLocalizedMessage());
            return null;
        }
    }

}
