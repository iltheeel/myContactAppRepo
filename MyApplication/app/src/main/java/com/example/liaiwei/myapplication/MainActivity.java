package com.example.liaiwei.myapplication;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

        boolean isInserted = myDb.insertData(editName.getText().toString(), editAddress.getText().toString(), editNumber.getText().toString());
        if(isInserted) {
            Log.d("MyContact", "Data insertion successful");
            Toast.makeText(getApplicationContext(), "DATA INSERTION SUCCESSFUL", Toast.LENGTH_SHORT);
            //create toast message to user indicating data inserted correctly
        }
        else {
            Log.d("MyContact", "Data insertion not successful");
            Toast.makeText(getApplicationContext(), "DATA INSERTION NOT SUCCESSFUL", Toast.LENGTH_SHORT);
            //create toast message to user indicating data inserted incorrectly
        }
    }

    public void viewData(View v) {
        Cursor res = myDb.getAllData();
        if (res.getCount() ==0) {
            showMessage("Error", "No data found in database");
            //put logd and toast here
            return;
        }

        StringBuffer buffer = new StringBuffer();
        // setup loop with moveToNext method
        //append each col to buffer
        while(!res.isLast()){
            res.moveToNext();
            for(int i =0; i<4; i++) {
                Log.d("MyContact", res.getString(i));
                buffer.append(res.getColumnNames());
                buffer.append(" : ");
                buffer.append(res.getString(i));
                buffer.append("\n");
            }
        }
        showMessage("app", buffer.toString());
    }

    private void showMessage(String title, String mssg) {

    }
}
