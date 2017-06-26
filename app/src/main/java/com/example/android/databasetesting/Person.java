package com.example.android.databasetesting;

import android.graphics.Bitmap;

/**
 * Created by Android on 6/26/2017.
 */

public class Person{
    String Name;
    String Address;
    int Phone;
    Bitmap picture;
    int ID;

    public Person(String name, String address, int phone, Bitmap picture, int ID) {
        Name = name;
        Address = address;
        Phone = phone;
        this.picture = picture;
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public String getAddress() {
        return Address;
    }

    public int getPhone() {
        return Phone;
    }

    public Bitmap getPicture() {
        return picture;
    }

    public int getID() {
        return ID;
    }
}
