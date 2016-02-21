package com.example.jeeb.communityapp.services;

import com.example.jeeb.communityapp.models.UserModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Jeeb on 6/18/2015.
 */
public class UserJsonParcer {
    public UserJsonParcer(){}

    public List<UserModel> parsingJson(String jsonApi) throws JSONException {

        JSONArray jObject = new JSONArray(jsonApi.toString());
        List<UserModel> users = new ArrayList<>();
        for (int j =0; j< jObject.length();j++){
            UserModel user = new UserModel();
            JSONObject jsonObject = (JSONObject) jObject.get(j);
            JSONObject docJson = jsonObject.getJSONObject("document");

            user.setmFirstName(docJson.getString("first_name"));
            user.setmLastName(docJson.getString("last_name"));
            user.setmAddress(docJson.getString("Address"));
            user.setmCity(docJson.getString("city"));
            user.setmProvince(docJson.getString("Province"));
            user.setmEmail(docJson.getString("email"));
            user.setmPhone(docJson.getString("phone"));
            users.add(user);
        }

        return users;
    }


}
