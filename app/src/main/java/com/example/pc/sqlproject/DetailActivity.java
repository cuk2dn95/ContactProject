package com.example.pc.sqlproject;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by PC on 10/13/2017.
 */

public class DetailActivity extends Activity {

    public static final int REQUEST_EDIT_ACTIVITY = 2;
    String id_contact;
    TextView fName,fCity,fGender,fPhone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        fName = (TextView)findViewById(R.id.name_detail);
        fGender = (TextView)findViewById(R.id.gender_detail);
        fCity = (TextView)findViewById(R.id.address_detail);
        fPhone =(TextView)findViewById(R.id.phone_detail);


        Intent intent = getIntent();
        id_contact = intent.getStringExtra("id");
        updateInformation(id_contact);

    }


    void updateInformation(String id)
    {
        ContentResolver resolver =getContentResolver();
        Cursor cursor = resolver.query(Uri.withAppendedPath(MyContentProvider.CONTENT_URI,id),
                null,null,null,null,null);
        if(cursor.moveToFirst())
        {
            String name = cursor.getString(cursor.getColumnIndex(MyContentProvider.NAME_COLUMN));
            String city = cursor.getString(cursor.getColumnIndex(MyContentProvider.CITY_COLUMN));
            String gender = cursor.getString(cursor.getColumnIndex(MyContentProvider.GENDER_COLUMN));
            String phone = cursor.getString(cursor.getColumnIndex(MyContentProvider.PHONE_NUMBER));

            fName.setText(name);
            fGender.setText(gender);
            fCity.setText(city);
            fPhone.setText(phone);



        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = new MenuInflater(this);
        menuInflater.inflate(R.menu.detail_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id)
        {
            case R.id.edit_menu:

                Intent intent = new Intent(this,AddActivity.class);
                intent.putExtra("function","edit");
                intent.putExtra("id",id_contact);
               startActivityForResult(intent,REQUEST_EDIT_ACTIVITY);


                break;
            case R.id.delete_detail_menu:

                getContentResolver().delete(Uri.withAppendedPath(MyContentProvider.CONTENT_URI,id_contact),null,null);
                getLoaderManager().restartLoader(0,null,MainActivity.contactLoader);
                onBackPressed();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_EDIT_ACTIVITY)
        {
            updateInformation(id_contact);
        }

    }

    public  void callContact(View view)
    {
        String phone = fPhone.getText().toString();
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:"+phone));
        if ( ContextCompat.checkSelfPermission( this, android.Manifest.permission.ACCESS_COARSE_LOCATION ) != PackageManager.PERMISSION_GRANTED )
            startActivity(intent);
    }

}
