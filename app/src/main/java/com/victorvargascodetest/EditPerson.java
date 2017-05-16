package com.victorvargascodetest;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
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

    String FIRST_NAME;
    String LAST_NAME;
    String PHONE_NUMBER;
    String DATE_OF_BIRTH;
    String ZIPCODE;

    Button button_date_of_birth;

    DatePickerDialog picker;

    Activity activity;

    DbPersonsHelper database;

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
        getSupportActionBar().setSubtitle(getResources().getString(R.string.edit_person_text));

        activity = this;


        //VALUES FROM INTENT
        FIRST_NAME = getIntent().getStringExtra("first_name");
        LAST_NAME = getIntent().getStringExtra("last_name");
        PHONE_NUMBER = getIntent().getStringExtra("phone_number");
        DATE_OF_BIRTH = getIntent().getStringExtra("date_of_birth");
        ZIPCODE = getIntent().getStringExtra("zipcode");


        //EDIT TEXT INITIALIZATION
        first_name = (EditText) findViewById(R.id.first_name);
        last_name = (EditText) findViewById(R.id.last_name);
        phone_number = (EditText) findViewById(R.id.phone_number);
        date_of_birth = (EditText) findViewById(R.id.date_of_birth);
        zipcode = (EditText) findViewById(R.id.zipcode);

        //BUTTON TO SELECT CALENDAR FOR DATE OF BIRTH
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


        //COMPLETE FIELDS IF DATA EXIST
        if(FIRST_NAME != null && LAST_NAME != null){
            if(!FIRST_NAME.isEmpty() && !LAST_NAME.isEmpty()) {
                first_name.setText(FIRST_NAME);
                last_name.setText(LAST_NAME);
                if (PHONE_NUMBER != null) {
                    if (!PHONE_NUMBER.isEmpty()) {
                        phone_number.setText(PHONE_NUMBER);
                    }
                }
                if (DATE_OF_BIRTH != null) {
                    if (!DATE_OF_BIRTH.isEmpty()) {
                        date_of_birth.setText(DATE_OF_BIRTH);
                    }
                }
                if (ZIPCODE != null) {
                    if (!ZIPCODE.isEmpty()) {
                        zipcode.setText(ZIPCODE);
                    }
                }
            }
        } else {
            this.setTitle(getResources().getString(R.string.new_person_title));
            getSupportActionBar().setSubtitle(getResources().getString(R.string.new_person_text));
        }

        //SQLITE DATABASE INITIALIZATION
        database = new DbPersonsHelper(this);


        //SAVE BUTTON FOR NEW OR UPDATE
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
                if(add){
                    //SEARCH FOR FIRST NAME AND LAST NAME, IF EXIST UPDATE ELSE CREATE NEW
                    if(FIRST_NAME != null && LAST_NAME != null){
                        if(!FIRST_NAME.isEmpty() && !LAST_NAME.isEmpty()) {
                            Cursor cursor = database.getAll();
                            if (cursor.getCount() > 0) {
                                while (cursor.moveToNext()) {

                                }
                                insertToDatabase();
                            } else {
                                insertToDatabase();
                            }
                            cursor.close();
                        } else {
                            insertToDatabase();
                        }
                    } else {
                        insertToDatabase();
                    }
                }
            }
        });

    }

    private void updateToDatabase(){
        ContentValues values = new ContentValues();
        values.put(DbPersonsDefinition.Entry.FIRST_NAME, first_name.getText().toString());
        values.put(DbPersonsDefinition.Entry.LAST_NAME, last_name.getText().toString());
        values.put(DbPersonsDefinition.Entry.PHONE_NUMBER, phone_number.getText().toString());
        values.put(DbPersonsDefinition.Entry.DATE_OF_BIRTH, date_of_birth.getText().toString());
        values.put(DbPersonsDefinition.Entry.ZIPCODE, zipcode.getText().toString());
        database.update(FIRST_NAME, LAST_NAME, values);

        Intent intent = new Intent();
        setResult(1, intent);
        finish();
    }

    /**
     * INSERT NEW PERSON TO DATABASE
     */
    private void insertToDatabase(){
        ContentValues values = new ContentValues();
        values.put(DbPersonsDefinition.Entry.FIRST_NAME, first_name.getText().toString());
        values.put(DbPersonsDefinition.Entry.LAST_NAME, last_name.getText().toString());
        values.put(DbPersonsDefinition.Entry.PHONE_NUMBER, phone_number.getText().toString());
        values.put(DbPersonsDefinition.Entry.DATE_OF_BIRTH, date_of_birth.getText().toString());
        values.put(DbPersonsDefinition.Entry.ZIPCODE, zipcode.getText().toString());
        database.insert(values);

        Intent intent = new Intent();
        setResult(1, intent);
        finish();
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
