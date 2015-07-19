package com.zhch.andex.database.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.zhch.andex.R;
import com.zhch.andex.common.activity.BaseAct;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.zhch.andex.database.sqlite.DataSchema.User;

/**
 * Created by zhch on 15-7-16.
 */
public class SqliteDemo extends BaseAct {

    @Bind(R.id.name)
    EditText mName;
    @Bind(R.id.age)
    EditText mAge;
    @Bind(R.id.result)
    TextView mResult;

    DataDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.database_sqlite_demo);
        ButterKnife.bind(this);

        mDbHelper = new DataDbHelper(getContext());
    }

    /**
     * 新增一条记录
     * @param v
     */
    public void onSubmitClick(View v) {

        Long id = System.currentTimeMillis();
        String name = mName.getText().toString();
        String age = mAge.getText().toString();

        // Gets the data repository in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(User._ID, id);
        values.put(User.NAME, name);
        values.put(User.AGE, age);

        // Insert the new row, returning the primary key value of the new row
        long newRowId;
        newRowId = db.insert(User.TABLE_NAME, null, values);

        onListClick(null);
    }

    /**
     * 显示表中所有记录
     * @param v
     */
    public void onListClick(View v) {
        onClearClick(null);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                User._ID,
                User.NAME,
                User.AGE
        };

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                User.NAME + " DESC";

        Cursor cursor = db.query(
                User.TABLE_NAME,  // The table to query
                projection,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );

        StringBuilder sb = new StringBuilder();

        while(cursor.moveToNext()){
            long itemId = cursor.getLong( cursor.getColumnIndexOrThrow(User._ID) );
            String name = cursor.getString( cursor.getColumnIndexOrThrow(User.NAME) );
            int age = cursor.getInt(cursor.getColumnIndexOrThrow(User.AGE));
            String content = itemId + " " + name + " " + age;
            sb.append(content).append("\n");
        }

        appendResult(sb.toString());
    }

    public void onClearClick(View v){
        mResult.setText("");
    }

    /**
     * 删除记录
     * @param v
     */
    public void onDeleteClick(View v){
        String name = mName.getText().toString();
        String age = mAge.getText().toString();

        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Define 'where' part of query.
        String selection = User.NAME + " LIKE ?";
        // Specify arguments in placeholder order.
        String[] selectionArgs = { name };
        // Issue SQL statement.
        db.delete(User.TABLE_NAME, selection, selectionArgs);

        onListClick(null);
    }

    /**
     * 更新记录
     * @param v
     */
    public void onUpdateClick(View v){

        String name = mName.getText().toString();
        String age = mAge.getText().toString();

        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // New value for one column
        ContentValues values = new ContentValues();
        values.put(User.NAME, name);

        // Which row to update, based on the ID
        String selection = User.AGE + " LIKE ?";
        String[] selectionArgs = { String.valueOf(age) };

        int count = db.update(
                User.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        onListClick(null);
    }

    private void appendResult(String content) {
        mResult.setText(mResult.getText().toString() + "\n" + content);
    }
}
