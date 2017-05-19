package com.example.liaiwei.myapplication;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
    EditText findName;
    Button btnAddData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb= new DatabaseHelper(this);

        editName = (EditText) findViewById( R.id.editText_name);
        editAddress= (EditText) findViewById( R.id.editText2);
        editNumber = (EditText) findViewById(R.id.editText3);
        findName = (EditText) findViewById(R.id.nameSearch);


    }


    public void searchByName(View v) {

        Cursor res = myDb.findData(findName.getText().toString());
        if (res.getCount() ==0) {
            Toast.makeText(getApplicationContext(), "DATA NOT FOUND", Toast.LENGTH_SHORT).show();
            //put logd and toast here3
            return;
        }

        StringBuffer buffer = new StringBuffer();

            res.moveToNext();


            buffer.append(" \n ID : ");
            buffer.append(res.getString(0));
            buffer.append(" \n Name: ");
            buffer.append(res.getString(1));
            buffer.append(" \n Address: ");
            buffer.append(res.getString(2));
            buffer.append(" \n Phone Number: ");
            buffer.append(res.getString(3));

        Log.d("MyContact", buffer.toString());

        showMessage("app", buffer.toString());

    }

    public void addData(View v) {

        boolean isInserted = myDb.insertData(editName.getText().toString(), editAddress.getText().toString(), editNumber.getText().toString());
        if(isInserted) {
            Log.d("MyContact", "Data insertion successful");
            Toast.makeText(getApplicationContext(), "DATA INSERTION SUCCESSFUL", Toast.LENGTH_SHORT).show();
            //create toast message to user indicating data inserted correctly
        }
        else {
            Log.d("MyContact", "Data insertion not successful");
            Toast.makeText(getApplicationContext(), "DATA INSERTION NOT SUCCESSFUL", Toast.LENGTH_SHORT).show();
            //create toast message to user indicating data inserted incorrectly
        }
    }

    public void viewData(View v) {
        Cursor res = myDb.getAllData();
        if (res.getCount() ==0) {
            Toast.makeText(getApplicationContext(), "DATA NOT FOUND", Toast.LENGTH_SHORT).show();
            //put logd and toast here
            return;
        }

        StringBuffer buffer = new StringBuffer();
        // setup loop with moveToNext method
        //append each col to buffer
        while(!res.isLast()){
            res.moveToNext();

           // for(int i =0; i<4; i++) {
            buffer.append(" \n ID : ");
            buffer.append(res.getString(0));
            buffer.append(" \n Name: ");
            buffer.append(res.getString(1));
            buffer.append(" \n Address: ");
            buffer.append(res.getString(2));
            buffer.append(" \n Phone Number: ");
            buffer.append(res.getString(3));

        }
        Log.d("MyContact", buffer.toString());

        showMessage("app", buffer.toString());
    }

    private void showMessage(String title, String mssg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true); //cancel using back button
        builder.setTitle(title);
        builder.setMessage(mssg);
        builder.show();
    }
}