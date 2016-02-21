package com.jeeb.grocerymanager.activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;

import com.jeeb.grocerymanager.R;
import com.jeeb.grocerymanager.fragments.LambFragment;

public class SecondActivity extends FragmentActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        FragmentManager fm = getSupportFragmentManager();
        Fragment ff = new LambFragment();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.container,ff);
        ft.commit();
//        TextView txtSecondActivity = (TextView) findViewById(R.id.txt_second_activity);
//        EditText editNewDataTxt = (EditText)findViewById(R.id.ed_tat_new_data_enter);
//        Button btnSubmit = (Button)findViewById(R.id.btn_submit);
//        Intent intent = getIntent();
//       final String intenData = intent.getExtras().getString("Name");
//        txtSecondActivity.setText(intenData);
//        btnSubmit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(getApplicationContext(), MainActivity.class);
//                i.putExtra("Name",intenData);
//                startActivity(i);
//            }
//        });
    }
}
