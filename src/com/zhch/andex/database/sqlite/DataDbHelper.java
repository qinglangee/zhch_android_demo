package com.zhch.andex.database.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.zhch.andex.app.MyApp;
import com.zhch.andex.util.LL;
import com.zhch.andex.util.ToastUtil;

/**
 * Created by zhch on 15-7-16.
 */
public class DataDbHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "SqliteDemo.db";


    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + DataSchema.User.TABLE_NAME + " (" +
                    DataSchema.User._ID + " INTEGER PRIMARY KEY," +
                    DataSchema.User.NAME + " TEXT," +
                    DataSchema.User.AGE + " TEXT" +
                    " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + DataSchema.User.TABLE_NAME;

    public DataDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DataDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
        LL.d("DataDbHelper onCreate.");
        ToastUtil.show(MyApp.getInstance(), "dbHelper onCreate");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}
