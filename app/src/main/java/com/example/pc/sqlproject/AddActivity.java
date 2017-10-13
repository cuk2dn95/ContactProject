package com.example.pc.sqlproject;

import android.app.ActionBar;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

/**
 * Created by PC on 10/13/2017.
 */

public class AddActivity extends Activity {

    EditText fName,fPhone,fCity;
    RadioGroup fGender;
    String func,id_contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        fName = (EditText)findViewById(R.id.name_input);
        fPhone= (EditText)findViewById(R.id.phone_input);
        fCity = (EditText)findViewById(R.id.city_input);
        fGender = (RadioGroup)findViewById(R.id.gender_input);

        Intent intent = getIntent();
         func = intent.getStringExtra("function");

        if(func.equals("edit"))
        {
            id_contact  = intent.getStringExtra("id");
            Cursor cursor = getContentResolver().query(Uri.withAppendedPath(MyContentProvider.CONTENT_URI,id_contact),null,null,null,null);
            if(cursor.moveToFirst())
            {
                fName.setText(cursor.getString(cursor.getColumnIndex(MyContentProvider.NAME_COLUMN)));
                fPhone.setText(cursor.getString(cursor.getColumnIndex(MyContentProvider.PHONE_NUMBER)));
                fCity.setText(cursor.getString(cursor.getColumnIndex(MyContentProvider.CITY_COLUMN)));
                String gender = cursor.getString(cursor.getColumnIndex(MyContentProvider.GENDER_COLUMN));
                fGender.check(gender.equals("Female")?R.id.female:R.id.male);
            }
        }

    }

    public void cancelContact(View view)
    {
        onBackPressed();
    }

    public void addContact(View view)
    {
        String name,phone,city,gender;
        name = fName.getText().toString();
        phone= fPhone.getText().toString();
        city = fCity.getText().toString();
        int id_btn = fGender.getCheckedRadioButtonId();
        RadioButton radioButton =(RadioButton) findViewById(id_btn);
        gender = radioButton.getText().toString();
        if(TextUtils.isEmpty(name) || TextUtils.isEmpty(phone) || TextUtils.isEmpty(city))
        {
            Toast.makeText(getApplicationContext(),"vui long nhap du ", Toast.LENGTH_SHORT);
            return;
        }


        ContentResolver resolver = getContentResolver();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MyContentProvider.NAME_COLUMN,name);
        contentValues.put(MyContentProvider.PHONE_NUMBER,phone);
        contentValues.put(MyContentProvider.CITY_COLUMN,city);
        contentValues.put(MyContentProvider.GENDER_COLUMN,gender);



        if(func.equals("add"))
        {

            resolver.insert(MyContentProvider.CONTENT_URI,contentValues);
            Toast.makeText(getApplicationContext(),"da them contact ", Toast.LENGTH_SHORT);

            finishActivity(MainActivity.REQUEST_ADD_ACTIVITY);
            onBackPressed();
        }
        else if(func.equals("edit"))
        {






            resolver.update(Uri.withAppendedPath(MyContentProvider.CONTENT_URI,id_contact),contentValues,null,null);
            Toast.makeText(getApplicationContext(),"da cap nhat contact ", Toast.LENGTH_SHORT);

            finishActivity(DetailActivity.REQUEST_EDIT_ACTIVITY);
            onBackPressed();
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}


