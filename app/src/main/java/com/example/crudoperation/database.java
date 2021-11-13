package com.example.crudoperation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class database extends SQLiteOpenHelper {
    private static final String dbname="signup";
    public database(@Nullable Context context) {
        super(context, dbname, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String q="create table users(id integer primary key autoincrement, name text, username text, password text)";
        db.execSQL(q);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists users");
        onCreate(db);
    }
    public boolean insert_data(String name, String username, String password)
        {
            SQLiteDatabase db=this.getWritableDatabase();  //for insert data in db
            ContentValues c=new ContentValues();   //class for write content in db
            c.put("name", name);
            c.put("username", username);
            c.put("password", password);
            long r=db.insert("users", null,c);
            if (r==-1) return false;
            else
                return true;
        }
    public boolean update_data(String name, String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();  //for insert data in db
        ContentValues c = new ContentValues();   //class for write content in db
        c.put("name", name);
        c.put("username", username);
        c.put("password", password);
        Cursor cursor = db.rawQuery("select * from users where username=?", new String[]{username});
        if (cursor.getCount() > 0) {
            long r = db.update("users", c, "username=?", new String[]{username});
            if (r == -1) return false;
            else
                return true;
        } else
            return false;
    }
    public boolean delete_data(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where username=?", new String[]{username});
        if (cursor.getCount() > 0) {
            long r = db.delete("users", "username=?", new String[]{username});
            if (r == -1) return false;
            else
                return true;
        } else
            return false;
    }

       public Cursor getinfo(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from users", null);
        return cursor;
       }
}
