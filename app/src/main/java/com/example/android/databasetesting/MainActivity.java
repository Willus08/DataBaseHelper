package com.example.android.databasetesting;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "DataBase";
    private static final int PHOTO_NUMBER = 1;
    EditText name;
    EditText phone;
    EditText address;
    EditText ID;
    ImageView picture;
    Bitmap image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = (EditText) findViewById(R.id.etName);
        phone =(EditText) findViewById(R.id.etNumber);
        address = (EditText) findViewById(R.id.etAddress);
        ID = (EditText) findViewById(R.id.etID);
        picture = (ImageView) findViewById(R.id.ivPicture);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
         super.onActivityResult(requestCode, resultCode, data);
         Bundle extras = data.getExtras();
         image = (Bitmap) extras.get("data");

        picture.setImageBitmap(image);
    }



    public void take(View view) {
        Intent getPictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(getPictureIntent, PHOTO_NUMBER);
    }


    public void save(View view) {
        DataBaseHelper dataBaseHelper = new DataBaseHelper(this);
        Person person = new Person(name.getText().toString(),address.getText().toString(),Integer.parseInt(phone.getText().toString()),image, Integer.parseInt(ID.getText().toString()));
        dataBaseHelper.saveNewPerson(person);
        Log.d(TAG, "save: Person Saved to Database");
    }

    public void load(View view) {
        DataBaseHelper data = new DataBaseHelper(this);
        data.getPeople();
    }

    public void updated(View view) {
        DataBaseHelper data = new DataBaseHelper(this);
        Person person = new Person(name.getText().toString(),address.getText().toString(),Integer.parseInt(phone.getText().toString()),image, Integer.parseInt(ID.getText().toString()));
        data.UpdateByID(person.getID(),person);
    }

    public void delete(View view) {
        DataBaseHelper data = new DataBaseHelper(this);
        data.DeleteByID(Integer.parseInt(ID.getText().toString()));

    }
}
