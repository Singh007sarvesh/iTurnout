package com.example.sarvesh.i_turnout;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION=1;
    private static final String CREATE_TABLE="create table "+DbContract.TABLE_NAME+"("+DbContract.studentId+" text,"
            +DbContract.courseId+" text,"+DbContract.date+" text,"+DbContract.presence+" text,"+ DbContract.SYNC_STATUS+" integer);";
    private static final String DROP_TABLE="drop table if exists "+ DbContract.TABLE_NAME;
    public DbHelper(Context context)
    {
        super(context, DbContract.DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }

    public void saveToLocalDatabase(String studentId,String courseId,String date,String presence,int sync_status,SQLiteDatabase database)
    {
      //  int length=Integer.parseInt(size);
      //  int length1=Integer.parseInt(stuAbsent);

      //  for(int i=0;i<length;i++) {
        //    for (int j=0;j<length1;j++) {
                ContentValues contentValues = new ContentValues();
                contentValues.put(DbContract.studentId, studentId);
                contentValues.put(DbContract.courseId, courseId);
                contentValues.put(DbContract.date,date);
                contentValues.put(DbContract.presence,presence);
                contentValues.put(DbContract.SYNC_STATUS, sync_status);
                database.insert(DbContract.TABLE_NAME, null, contentValues);
          //  }
       // }
    }
    public Cursor readFromLocalDatabase(SQLiteDatabase database)
    {
        String[] projection={DbContract.studentId,DbContract.courseId,DbContract.date,DbContract.presence,DbContract.SYNC_STATUS};
        return (database.query(DbContract.TABLE_NAME,projection,null,null,null,null,null));
    }

    public void updateLocalDatabase(String studentId,String courseId,String data,String presence,int sync_status,SQLiteDatabase database)
    {
        ContentValues contentValues=new ContentValues();
        contentValues.put(DbContract.SYNC_STATUS,sync_status);
        String selection=DbContract.TABLE_NAME+"LIKE ?";
        String[] selection_args={studentId,courseId,data,presence};
        database.update(DbContract.TABLE_NAME,contentValues,selection,selection_args);
    }
}
