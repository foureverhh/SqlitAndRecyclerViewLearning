package com.nackademin.foureverhh.sqlitandrecyclerviewlearning;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.nackademin.foureverhh.sqlitandrecyclerviewlearning.GroceryContract.*;

public class GroceryDBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "grocerylist.db";
    public static final int DATABASE_VERSION = 1;


    public GroceryDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_GROCERYLIST_TABLE = "CREATE TABLE " +
            GroceryEntry.TABLE_NAME + " (" +
            GroceryEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            GroceryEntry.COLUMN_NAME + " TEXT NOT NULL, " +
            GroceryEntry.COLUMN_AMOUNT + " INTEGER NOT NULL, " +
            GroceryEntry.COLUMN_TIMESTAMP + " TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP" +
                " );";
        sqLiteDatabase.execSQL(SQL_CREATE_GROCERYLIST_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + GroceryEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
