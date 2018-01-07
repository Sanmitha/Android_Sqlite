package com.example.hp.sqliteexample;

import android.widget.EditText;

/**
 * Created by hp on 19-12-2017.
 */

public class ModuleClass {


    public int id;
    public String name;
    public String phnumber;

   public ModuleClass()
   {

   }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhnumber() {
        return phnumber;
    }

    public void setPhnumber(String phnumber) {
        this.phnumber = phnumber;
    }
}
