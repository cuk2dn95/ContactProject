package com.example.pc.sqlproject;

import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.SimpleCursorAdapter;

/**
 * Created by PC on 10/13/2017.
 */

public class ContactLoader implements LoaderManager.LoaderCallbacks<Cursor> {

    SimpleCursorAdapter adapter;
    Context context;

    public ContactLoader(Context context,SimpleCursorAdapter adapter) {
        this.adapter = adapter;
        this.context  = context;
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
    adapter.swapCursor(null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

        adapter.swapCursor(data);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        return new CursorLoader(context,MyContentProvider.CONTENT_URI,null,null,null,null);
    }
}
