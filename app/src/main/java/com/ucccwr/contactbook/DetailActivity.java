package com.ucccwr.contactbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    TextView head , data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent myIntent = getIntent(); // gets the previously created intent
        String firstKeyName = myIntent.getStringExtra("firstKeyName"); // will return "FirstKeyValue"
        String secondKeyName= myIntent.getStringExtra("secondKeyName");

        head = findViewById(R.id.head);
        data = findViewById(R.id.data);

        head.setText(firstKeyName);
        data.setText(secondKeyName);

        Log.d("mykey",firstKeyName+" "+secondKeyName);
    }
}