package com.victorvargascodetest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import static android.support.v4.content.ContextCompat.startActivity;

public class AdapterPersons extends BaseAdapter {

    private Context context;
    private ArrayList<ModelPersons> Persons;

    // CONSTRUCTOR FUNCTION
    public AdapterPersons(Context context, ArrayList<ModelPersons> Persons){
        this.context = context;
        this.Persons = Persons;
    }

    @Override
    public int getCount() {
        return Persons.size();
    }

    @Override
    public Object getItem(int position) {
        return Persons.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        // INFLATE ITEM FROM LAYOUT
        View ViewInflater;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            ViewInflater = inflater.inflate(R.layout.person_list_item, null);
        } else {
            ViewInflater = convertView;
        }

        // FIELDS INITIALIZATION FROM LAYOUT
        final TextView first_name = (TextView) ViewInflater.findViewById(R.id.first_name);
        final TextView last_name = (TextView) ViewInflater.findViewById(R.id.last_name);
        final TextView phone_number = (TextView) ViewInflater.findViewById(R.id.phone_number);
        final TextView date_of_birth = (TextView) ViewInflater.findViewById(R.id.date_of_birth);
        final TextView zipcode = (TextView) ViewInflater.findViewById(R.id.zipcode);

        // BUTTON INITIALIZATION FROM LAYOUT
        Button edit = (Button) ViewInflater.findViewById(R.id.edit);
        Button delete = (Button) ViewInflater.findViewById(R.id.delete);

        // SET VALUES FOR EACH FIELD TO SHOW IN LISTVIEW
        first_name.setText(Persons.get(position).getFirst_name());
        last_name.setText(Persons.get(position).getLast_name());
        phone_number.setText(Persons.get(position).getPhone_number());
        date_of_birth.setText(Persons.get(position).getDate_of_birth());
        zipcode.setText(Persons.get(position).getZipcode());

        // BUTTON CLICK LISTENERS
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EditPerson.class);
                intent.putExtra("first_name", first_name.getText().toString());
                intent.putExtra("last_name", last_name.getText().toString());
                intent.putExtra("phone_number", phone_number.getText().toString());
                intent.putExtra("date_of_birth", date_of_birth.getText().toString());
                intent.putExtra("zipcode", zipcode.getText().toString());
                ((Activity) context).startActivityForResult(intent, 1);
            }
        });

        return ViewInflater;
    }
}
