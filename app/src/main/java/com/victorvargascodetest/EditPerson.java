package com.victorvargascodetest;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

public class EditPerson extends AppCompatActivity {

    //EDIT TEXT DECLARATION
    EditText first_name;
    EditText last_name;
    EditText phone_number;
    EditText date_of_birth;
    EditText zipcode;

    Button button_date_of_birth;

    DatePickerDialog picker;

    Activity activity;

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

        activity = this;

        //EDIT TEXT INITIALIZATION
        first_name = (EditText) findViewById(R.id.first_name);
        last_name = (EditText) findViewById(R.id.last_name);
        phone_number = (EditText) findViewById(R.id.phone_number);
        date_of_birth = (EditText) findViewById(R.id.date_of_birth);
        zipcode = (EditText) findViewById(R.id.zipcode);

        button_date_of_birth = (Button) findViewById(R.id.button_date_of_birth);
        button_date_of_birth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);
                picker = new DatePickerDialog(activity, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        date_of_birth.setText(String.format("%02d",year)+"-"+String.format("%02d",month+1)+"-"+String.format("%02d",day));
                    }
                }, year, month, day);
                picker.show();
            }
        });


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

        Button save = (Button) findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean add = true;
                if(first_name.length() == 0){
                    first_name.setError(getResources().getString(R.string.required));
                    add = false;
                }
                if(last_name.length() == 0){
                    last_name.setError(getResources().getString(R.string.required));
                    add = false;
                }
                if(phone_number.length() == 0){
                    phone_number.setError(getResources().getString(R.string.required));
                    add = false;
                }
                if(date_of_birth.length() == 0){
                    date_of_birth.setError(getResources().getString(R.string.required));
                    add = false;
                }
                if(zipcode.length() == 0){
                    zipcode.setError(getResources().getString(R.string.required));
                    add = false;
                }
                if(add){
                    //TODO: Agregar a la base de datos y regresar y refrescar
                }
            }
        });

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
