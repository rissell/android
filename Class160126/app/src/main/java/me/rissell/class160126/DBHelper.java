package me.rissell.class160126;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

/**
 * Created by Raptor on 26/01/16.
 */
public class DBHelper extends SQLiteOpenHelper{

    private static final String DATABASE = "class.db";
    private static final String TABLE = "students";
    private static final int VERSION = 1;

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";


    public DBHelper(Context context){
        super(context, DATABASE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String creation = "CREATE TABLE " + TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY, " + COLUMN_NAME + " TEXT)";
        db.execSQL(creation);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String[] name = {TABLE};
        db.execSQL("DROP TABLE IF EXIST ? ", name);
        onCreate(db);

    }

    public void saveRecord(String data){

        ContentValues cv = new ContentValues();
        cv.put(TABLE, data);
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE, null, cv);

    }

    public int findRecord(String name){

        String clause = COLUMN_NAME + "  = ?";
        String[] args = {name};
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.query(TABLE, null, clause, args, null, null, null);
        int result = -1;
        if(c.moveToFirst()){
            result = c.getInt(0);
        }
        return result;


    }


}
