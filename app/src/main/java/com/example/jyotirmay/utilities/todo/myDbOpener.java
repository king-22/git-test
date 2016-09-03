package com.example.jyotirmay.utilities.todo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.jyotirmay.utilities.todo.db.TaskTable;

/**
 * Created by jyotirmay on 25-08-2016.
 */
public class myDbOpener extends SQLiteOpenHelper {

    public static final String DB_NAME ="mydatabase";
    public static final int  DB_VER =2;

    private static com.example.jyotirmay.utilities.todo.myDbOpener myDbOpener = null;

    public static SQLiteDatabase openReadableDatabase(Context c){
        if(myDbOpener == null){
            myDbOpener = new myDbOpener(c);
        }
        return myDbOpener.getReadableDatabase();
    }

    public static SQLiteDatabase openWritableDatabase(Context c){
        if(myDbOpener == null){
            myDbOpener = new myDbOpener(c);
        }
        return myDbOpener.getWritableDatabase();
    }
    public myDbOpener(Context context) {
        super(context, DB_NAME,null, DB_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TaskTable.TABLE_CREATE_CMD);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

