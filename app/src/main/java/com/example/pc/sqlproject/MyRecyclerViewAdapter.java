package com.example.pc.sqlproject;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

/**
 * Created by PC on 10/13/2017.
 */

public class MyRecyclerViewAdapter extends SimpleCursorAdapter {

    String[] from;
    int[] to;
    Context context1;
    public MyRecyclerViewAdapter(Context context, int layout, Cursor c, String[] from, int[] to, int flags) {
        super(context, layout, c, from, to, flags);
        this.context1 = context;
        this.from = from;
        this.to = to;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.item,parent,false);
    }

    @Override
    public void bindView(View view,Context context, Cursor cursor) {
        TextView fName = (TextView) view.findViewById(to[0]);
        String name = cursor.getString(cursor.getColumnIndex(from[0]));
        fName.setText(name);
        view.setTag(cursor.getString(cursor.getColumnIndex(MyContentProvider.ID_COLUMN)));
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String id_contact = v.getTag().toString();

                Intent intent = new Intent(context1,DetailActivity.class);

                intent.putExtra("id",id_contact);
                context1.startActivity(intent);



            }
        });
    }
}
