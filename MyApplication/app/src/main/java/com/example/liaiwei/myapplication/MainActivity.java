package com.example.liaiwei.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText editName;
    EditText editAddress;
    EditText editNumber;
    Button btnAddData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DatabaseHelper(this);

        editName = (EditText) findViewById( R.id.editText_name);
        editAddress= (EditText) findViewById( R.id.editText2);
        editNumber = (EditText) findViewById(R.id.editText3);


    }


    public void addData(View v) {

        boolean isInserted = myDb.insertData(editName.getText().toString());
        if(isInserted) {
            Log.d("MyContact", "Data insertion successful");
            //create toast message to user indicating data inserted correctly
        }
        else {
            Log.d("MyContact", "Data insertion not successful");
            //create toast message to user indicating data inserted incorrectly
        }
    }
}
