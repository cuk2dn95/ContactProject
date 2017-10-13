package com.example.pc.sqlproject;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.CursorLoader;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends Activity {

    public final static int REQUEST_ADD_ACTIVITY = 1;
    ListView listView;
    static public  ContactLoader contactLoader;
    MyRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.list_view);



        adapter = new MyRecyclerViewAdapter(this,R.layout.item,null,
                        new String[]{MyContentProvider.NAME_COLUMN},new int[]{R.id.contact},0);
        listView.setAdapter(adapter);
        LoaderManager manager = getLoaderManager();
        contactLoader = new ContactLoader(this,adapter);
        manager.initLoader(0,null,contactLoader);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = new MenuInflater(this);
        menuInflater.inflate(R.menu.menu,menu);

        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.add_menu:

                Intent intent = new Intent(this,AddActivity.class);
                intent.putExtra("function","add");
                startActivityForResult(intent,REQUEST_ADD_ACTIVITY);



                break;
            case R.id.delete_menu:

                ContentResolver resolver = getContentResolver();
                resolver.delete(MyContentProvider.CONTENT_URI,null,null);


                LoaderManager manager = getLoaderManager();
                manager.restartLoader(0,null,contactLoader);
                break;



        }

        return super.onOptionsItemSelected(item);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == REQUEST_ADD_ACTIVITY )
        {
            LoaderManager manager = getLoaderManager();
            manager.restartLoader(0,null,contactLoader);
        }


    }
}
