package com.victorvargascodetest;

import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(getResources().getColor(R.color.colorAccent));
        }

        ArrayList<ModelPersons> Persons = new ArrayList<ModelPersons>();

        TextView no_persons_text = (TextView) findViewById(R.id.no_persons_text);

        //ADD PERSONS TO LIST IF EXIST ON DATABASE
        DbPersonsHelper PersonsHelper = new DbPersonsHelper(this);
        Cursor cursor = PersonsHelper.getAll();
        if(cursor.getCount() > 0){
            no_persons_text.setVisibility(View.GONE);
            while(cursor.moveToNext()){
                Persons.add(new ModelPersons(
                        cursor.getString(cursor.getColumnIndexOrThrow(DbPersonsDefinition.Entry.FIRST_NAME)),
                        cursor.getString(cursor.getColumnIndexOrThrow(DbPersonsDefinition.Entry.LAST_NAME)),
                        cursor.getString(cursor.getColumnIndexOrThrow(DbPersonsDefinition.Entry.PHONE_NUMBER)),
                        cursor.getString(cursor.getColumnIndexOrThrow(DbPersonsDefinition.Entry.DATE_OF_BIRTH)),
                        cursor.getString(cursor.getColumnIndexOrThrow(DbPersonsDefinition.Entry.ZIPCODE))
                ));
            }
        } else {
            no_persons_text.setVisibility(View.VISIBLE);
        }
        cursor.close();


        //LIST VIEW AND ADAPTER TO SHOW DATA
        ListView List = (ListView) findViewById(R.id.ListView);
        AdapterPersons Adapter = new AdapterPersons(this, Persons);
        List.setAdapter(Adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //MENU FOR PERSON ADD BUTTON ON CORNER TOP RIGHT
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addButton:
                Intent intent = new Intent(this, EditPerson.class);
                startActivityForResult(intent, 1);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 1) {
            Intent intent = new Intent(this, MainActivity.class);
            finish();
            startActivity(intent);
        }
    }
}
