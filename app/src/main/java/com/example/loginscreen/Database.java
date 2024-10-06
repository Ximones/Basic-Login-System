package com.example.loginscreen;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {

    public static final String databaseName = "SignLog.db";

    // Constructor
    public Database(@Nullable Context context) {
        super(context, databaseName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDatabase) {
        // Create users table with username as the primary key
        MyDatabase.execSQL("CREATE TABLE users(username TEXT PRIMARY KEY, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDatabase, int oldVersion, int newVersion) {
        // Drop the old table if it exists and recreate it
        MyDatabase.execSQL("DROP TABLE IF EXISTS users");
    }

    // Insert new user data
    public Boolean insertData(String username, String password) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);  // Correct field for username
        contentValues.put("password", password);  // Correct field for password
        long result = MyDatabase.insert("users", null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    // Check if a username already exists in the database
    public Boolean checkUsername(String username) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("SELECT * FROM users WHERE username = ?", new String[]{username});

        if(cursor.getCount() > 0) {
            return true;
        }else {
            return false;
        }
    }

    // Check if both username and password match
    public Boolean checkPassword(String username, String password) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("SELECT * FROM users WHERE username = ? AND password = ?", new String[]{username, password});

        if (cursor.getCount() > 0) {
            return true;
        }else {
            return false;
        }
    }
}
