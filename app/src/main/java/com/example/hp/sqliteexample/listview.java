package com.example.hp.sqliteexample;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class listview extends AppCompatActivity {
    ArrayList<ModuleClass>moduleclass;
    ListView clist;
    TextView name1,number1;
    DbHelper dbhelper;
    List delete;
    AlertDialog alert11;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_listview);
        clist = (ListView) findViewById(R.id.list);
        dbhelper = new DbHelper(listview.this);
        moduleclass = dbhelper.retriveData();
        final Custadapter adapter = new Custadapter(listview.this, 0);
        clist.setAdapter(adapter);

        clist.setLongClickable(true);

        clist.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            AlertDialog.Builder builder;

            @Override

            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int i, long l) {
                //Log.v("Long Clicked","pos: "+i);

                builder = new AlertDialog.Builder(listview.this);
                View view1 = LayoutInflater.from(listview.this).inflate(R.layout.update_layout, null);
                builder.setView(view1);
                final EditText upname = (EditText) view1.findViewById(R.id.upname);

                final EditText upnumber = (EditText) view1.findViewById(R.id.uppnum);
                Button del = (Button) view1.findViewById(R.id.delete);
                Button up = (Button) view1.findViewById(R.id.update);
                upname.setText(moduleclass.get(i).getName());
                upnumber.setText(moduleclass.get(i).getPhnumber());


                up.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int id=moduleclass.get(i).getId();
                        String name=upname.getText().toString();
                        String phnumber=upnumber.getText().toString();

                        dbhelper.update(name,phnumber,id);
                        moduleclass=dbhelper.retriveData();
                        adapter.notifyDataSetChanged();
alert11.cancel();
                    }
                });

                del.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                       // builder.setTitle("Delete entry")
                                //.setMessage("Are you sure you want to delete this entry?");
                        dbhelper.delete(moduleclass.get(i).getId());
                        moduleclass = dbhelper.retriveData();
                        adapter.notifyDataSetChanged();
                        alert11.cancel();
                        /*.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // continue with delete
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .show();*/

                      /*  builder.setPositiveButton(
                                "Yes",
                                new DialogInterface.OnClickListener() {

                                    public void onClick(DialogInterface dialog, int id) {

                                        dialog.cancel();
                                        dbhelper.delete(moduleclass.get(i).getId());
                                        moduleclass = dbhelper.retriveData();
                                        adapter.notifyDataSetChanged();
                                        //clist.setAdapter(adapter);

                                    }
                                });

                        builder.setNegativeButton(
                                "No",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });*/

                       }
                });
                alert11 = builder.create();
                alert11.show();

                return true;


                //delete=(ListView)findViewById(R.id.delete) ;


            }

        });
    }
    class Custadapter extends ArrayAdapter {

        public Custadapter(Context context, int resource) {
            super(context, resource);
        }

        @Override
        public int getCount() {
            return moduleclass.size();
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //  return super.getView(position, convertView, parent);

            if (convertView == null) {

                LayoutInflater inflater = LayoutInflater.from(listview.this);//layout is set to the custom layout
                convertView = inflater.inflate(R.layout.custom, null);// convertview is used to view the list
                name1 = (TextView) convertView.findViewById(R.id.name);
                number1 = (TextView) convertView.findViewById(R.id.number);
            }
            name1.setText(moduleclass.get(position).getName());
            number1.setText(moduleclass.get(position).getPhnumber());

            return convertView;

        }}}
