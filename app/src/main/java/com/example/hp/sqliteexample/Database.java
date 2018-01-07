package com.example.hp.sqliteexample;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import java.util.ArrayList;

import static android.provider.Settings.NameValueTable.NAME;

public class Database extends AppCompatActivity {
    EditText name1,phnum1;
    Button submit,retrive,delete;
    TextureView insname1,insnum1;

    DbHelper dbhelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

name1=(EditText)findViewById(R.id.name);
        phnum1= (EditText) findViewById(R.id.phnum);
        submit=(Button)findViewById(R.id.submit);
        retrive=(Button)findViewById(R.id.retrieve);
        //insnam1e=(TextureView)findViewById(R.id.insname);

        dbhelper=new DbHelper(Database.this);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                    String Name = name1.getText().toString();
                    String phnum = phnum1.getText().toString();

                if (Name.equals("") || phnum.equals("")) {
                    Toast.makeText(Database.this, "Add Student details", Toast.LENGTH_SHORT).show();
                }
                else {
                    dbhelper.addDetails(Name,phnum);
                    Toast.makeText(Database.this, "Data Added Successfully", Toast.LENGTH_SHORT).show();

                }

            }
        });


                retrive.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                Intent intent = new Intent(Database.this, listview.class);
                startActivity(intent);




            }
        });

        }
    }


