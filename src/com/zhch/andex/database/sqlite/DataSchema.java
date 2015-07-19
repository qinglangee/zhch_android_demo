package com.zhch.andex.database.sqlite;

import android.provider.BaseColumns;

/**
 * Created by zhch on 15-7-16.
 */
public class DataSchema {

    public static abstract class User implements BaseColumns {
        public static final String TABLE_NAME = "user";
        public static final String NAME = "NAME";
        public static final String AGE = "AGE";
    }
}
