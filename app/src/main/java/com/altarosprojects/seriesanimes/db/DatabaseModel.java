package com.altarosprojects.seriesanimes.db;

import android.provider.BaseColumns;

public class DatabaseModel {

    private DatabaseModel(){}

    //Inner class that defines the users table
    public static class UsersTable implements BaseColumns{
        public static final String TABLE_NAME = "Users";
        public static final String COLUMN_1 = "username";
        public static final String COLUMN_2 = "email";
        public static final String COLUMN_3 = "password";
    }

    //method for maintaining the Users Table
    public static final String SQL_CREATE_USER =
            "CREATE TABLE " + UsersTable.TABLE_NAME + " (" +
                UsersTable._ID + " INTEGER PRIMARY KEY," +
                UsersTable.COLUMN_1 + " TEXT," +
                UsersTable.COLUMN_2 + " TEXT," +
                UsersTable.COLUMN_3 + " TEXT)";
    public static final String SQL_DELETE_USER =
            "DROP TABLE IF EXISTS " + UsersTable.TABLE_NAME;


}
