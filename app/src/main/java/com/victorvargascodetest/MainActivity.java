package com.victorvargascodetest;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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

        //TWO PERSONS ADDED AT FIRST FOR DEMO
        Persons.add(new ModelPersons("Victor", "Vargas", "+56 9 7501 1700", "1982-10-15", "9876543"));
        Persons.add(new ModelPersons("Frank", "Bermann", "+56 9 7685 9364", "1976-05-01", "9854320"));

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
