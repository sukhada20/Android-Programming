package com.example.practical5;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import java.util.ArrayList;

public class MyProvider extends ContentProvider {

    ArrayList<String> list = new ArrayList<>();

    @Override
    public boolean onCreate() {
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {

        MatrixCursor cursor = new MatrixCursor(new String[]{"name"});

        for(String item : list){
            cursor.addRow(new Object[]{item});
        }
        return cursor;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {

        String name = values.getAsString("name");
        list.add(name);
        return uri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        list.clear();
        return 1;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        return 0;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }
}
