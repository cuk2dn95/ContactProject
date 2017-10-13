package com.example.pc.sqlproject;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.ContactsContract;

public class MyContentProvider extends ContentProvider {
    public MyContentProvider() {
    }


    MyDB myDB;

    public static final String AUTHORIY ="MyDB";
    public static final String TABLE = MyDB.TABLE_NAME;

    public static final  Uri CONTENT_URI = Uri.parse("content://"+AUTHORIY+"/"+TABLE);


    public static final String ID_COLUMN= MyDB.ID_COLUMN;
    public static final String NAME_COLUMN= MyDB.NAME_COLUMN;
    public static final String PHONE_NUMBER= MyDB.PHONE_COLUMN;
    public static final String CITY_COLUMN= MyDB.CITY_COLUMN;
    public static final String GENDER_COLUMN= MyDB.GENDER_COLUMN;
    static UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static  final private int TABLE_CODE = 1;
    static  final private int COLUMN_CODE = 2;
    static {

        uriMatcher.addURI(AUTHORIY,TABLE,TABLE_CODE);
        uriMatcher.addURI(AUTHORIY,TABLE+"/#",COLUMN_CODE);

    }






    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.


        SQLiteDatabase db = myDB.getWritableDatabase();
        int code = uriMatcher.match(uri);
        int result;
        switch (code)
        {
            case TABLE_CODE:
                result = db.delete(TABLE,selection,selectionArgs);
                break;
            case COLUMN_CODE:
                String id = uri.getLastPathSegment();
                result = db.delete(TABLE,ID_COLUMN +"=?",new String[]{id});
                break;
            default:
                throw new UnsupportedOperationException("Not yet implemented");

        }

        return result;
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.


        SQLiteDatabase db = myDB.getWritableDatabase();

        int code = uriMatcher.match(uri);
        Uri uri1 = null;
        switch (code)
        {
            case TABLE_CODE:

               long id = db.insert(TABLE,null,values);
                uri1 = Uri.withAppendedPath(uri,Long.toString(id));
                break;

            default:
                throw new UnsupportedOperationException("Not yet implemented");
        }






        return uri1;
    }

    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.
        myDB = new MyDB(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        // TODO: Implement this to handle query requests from clients.


        SQLiteDatabase db = myDB.getWritableDatabase();
        int code = uriMatcher.match(uri);
        Cursor cursor = null;
        switch (code)
        {
            case TABLE_CODE:

               cursor= db.query(TABLE,projection,selection,selectionArgs,null,null,sortOrder);
                break;

            case COLUMN_CODE:
                String id = uri.getLastPathSegment();
                cursor= db.query(TABLE,projection,ID_COLUMN +"=?",new String[]{id},null,null,sortOrder);


                break;
            default:
                throw new UnsupportedOperationException("Not yet implemented");


        }


        return  cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        SQLiteDatabase db = myDB.getWritableDatabase();
        int code = uriMatcher.match(uri);
        int result = -1;
        switch (code){
            case TABLE_CODE:
                break;
            case COLUMN_CODE:
                String id = uri.getLastPathSegment();
                result = db.update(TABLE,values,ID_COLUMN+"=?",new String[]{id});
                break;
            default:
                throw new UnsupportedOperationException("Not yet implemented");
        }


        return result;

    }
}
