package com.victorvargascodetest;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

public class EditPerson extends AppCompatActivity {

    //EDIT TEXT DECLARATION
    EditText first_name;
    EditText last_name;
    EditText phone_number;
    EditText date_of_birth;
    EditText zipcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_person);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(getResources().getColor(R.color.colorAccent));
        }

        getSupportActionBar();

        //THIS ENABLE BACK BUTTON
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //ANIMATION BETWEEN ACTIVITIES
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_right);

        this.setTitle(getIntent().getStringExtra("first_name")+" "+getIntent().getStringExtra("last_name"));
        getSupportActionBar().setSubtitle("Edit person");

        //EDIT TEXT INITIALIZATION
        first_name = (EditText) findViewById(R.id.first_name);
        last_name = (EditText) findViewById(R.id.last_name);
        phone_number = (EditText) findViewById(R.id.phone_number);
        date_of_birth = (EditText) findViewById(R.id.date_of_birth);
        zipcode = (EditText) findViewById(R.id.zipcode);


        //COMPLETE FIELDS IF DATA FOR EDIT EXIST
        if(!getIntent().getStringExtra("first_name").isEmpty() && !getIntent().getStringExtra("last_name").isEmpty()){
            first_name.setText(getIntent().getStringExtra("first_name"));
            last_name.setText(getIntent().getStringExtra("last_name"));

            try{
                phone_number.setText(getIntent().getStringExtra("phone_number"));
            } catch(Exception e){
                Log.e("ERROR", "No phone number");
            }

            try{
                date_of_birth.setText(getIntent().getStringExtra("date_of_birth"));
            } catch(Exception e){
                Log.e("ERROR", "No date of birth");
            }

            try{
                zipcode.setText(getIntent().getStringExtra("zipcode"));
            } catch(Exception e){
                Log.e("ERROR", "No zipcode");
            }
        }

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.animation_enter, R.anim.animation_leave);
    }

}
