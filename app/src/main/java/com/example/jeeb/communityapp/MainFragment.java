package com.example.jeeb.communityapp;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.jeeb.communityapp.models.UserModel;
import com.example.jeeb.communityapp.services.CustomJSONArraytRequest;
import com.example.jeeb.communityapp.services.CustomVolleyRequestQueue;
import com.example.jeeb.communityapp.services.UserJsonParcer;
import com.jeeb.grocerymanager.R;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment implements Response.Listener, Response.ErrorListener {

    public static final String REQUEST_TAG = "MainVolleyActivity";

    private RequestQueue mQueue;
    MainActivity.UsersViewHolder usersViewHolder;
    MainActivity mainActivity;
    View view;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mainActivity = new MainActivity();
        usersViewHolder = mainActivity.new UsersViewHolder();
        view = inflater.inflate(R.layout.list_view, container, false);

//        usersViewHolder.mFirstNameView = (TextView) view.findViewById(R.id.display_first_name);
//        usersViewHolder.mLastNameView = (TextView) view.findViewById(R.id.display_last_name);
//        usersViewHolder.mAddressView = (TextView) view.findViewById(R.id.display_address);
//        usersViewHolder.mCityView = (TextView) view.findViewById(R.id.display_city);
//        usersViewHolder.mProvinceView = (TextView) view.findViewById(R.id.display_province);
//        usersViewHolder.mPhoneView = (TextView) view.findViewById(R.id.display_phone);
//        usersViewHolder.mEmail = (TextView) view.findViewById(R.id.display_email);
        usersViewHolder.mGetUserBtn = (Button) view.findViewById(R.id.btn_get_user);
        usersViewHolder.listView = (ListView)view.findViewById(R.id.items);
//        usersViewHolder.mGetUserBtn.setOnClickListener();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        mQueue = CustomVolleyRequestQueue.getmInstance(getActivity()).getRequestQueue(getActivity());
        String url = "https://api.mongolab.com/api/1/databases/code101/collections/docs101/?apiKey=LcybTMW4H3ULBfJsl-ai7lrc1TwOR0U7";
        final CustomJSONArraytRequest jsonObjectRequest = new CustomJSONArraytRequest(Request.Method.GET, url, new JSONArray(),this, this);
        jsonObjectRequest.setTag(REQUEST_TAG);
        mQueue.add(jsonObjectRequest);

    }

    @Override
    public void onErrorResponse(VolleyError error) {
        usersViewHolder.mFirstNameView.setText(error.getMessage());
    }

    @Override
    public void onResponse(Object response) {
        UserJsonParcer parcer = new UserJsonParcer();
        try {
            List<UserModel> users = parcer.parsingJson(response.toString());
            final UserAdapter userAdapter = new UserAdapter(users,getActivity());
            usersViewHolder.mGetUserBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    usersViewHolder.listView.setAdapter(userAdapter);
                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public class UserAdapter extends BaseAdapter{
        List<UserModel> userModels;
        LayoutInflater layoutInflater = null;
        public UserAdapter(List<UserModel>users, Activity activity){
            userModels = users;
            layoutInflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return userModels.size();
        }

        @Override
        public Object getItem(int position) {
            return userModels.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
//           view = convertView;
//            if (view  == null){
                view = layoutInflater.inflate(R.layout.items, parent,false);
//                usersViewHolder.mFirstNameView = (TextView) view.findViewById(R.id.display_first_name);
//                usersViewHolder.mLastNameView = (TextView) view.findViewById(R.id.display_last_name);
//                usersViewHolder.mAddressView = (TextView) view.findViewById(R.id.display_address);
//                usersViewHolder.mCityView = (TextView) view.findViewById(R.id.display_city);
//                usersViewHolder.mProvinceView = (TextView) view.findViewById(R.id.display_province);
//                usersViewHolder.mPhoneView = (TextView) view.findViewById(R.id.display_phone);
//                usersViewHolder.mEmail = (TextView) view.findViewById(R.id.display_email);

                usersViewHolder.mFirstNameView.setText("First Name: "+ userModels.get(position).getmFirstName());
                usersViewHolder.mLastNameView.setText("Last Name: "+ userModels.get(position).getmLastName());
                usersViewHolder.mAddressView.setText("Address: "+ userModels.get(position).getmAddress());
                usersViewHolder.mCityView.setText("City: "+ userModels.get(position).getmCity());
                usersViewHolder.mProvinceView.setText("Province: "+ userModels.get(position).getmProvince());
                usersViewHolder.mPhoneView.setText("Phone: "+ userModels.get(position).getmPhone());
                usersViewHolder.mEmail.setText("Email: "+ userModels.get(position).getmEmail());
//           }

            return view;
        }
    }


}
