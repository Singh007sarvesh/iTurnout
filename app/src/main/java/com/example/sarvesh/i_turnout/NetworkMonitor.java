package com.example.sarvesh.i_turnout;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkMonitor extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        if(checkNetworkConnection(context))
        {
            DbHelper dbHelper=new DbHelper(context);
            SQLiteDatabase database= dbHelper.getWritableDatabase();
            Cursor cursor= dbHelper.readFromLocalDatabase(database);
            while (cursor.moveToNext())
            {
                int sync_status=cursor.getInt(cursor.getColumnIndex(DbContract.SYNC_STATUS));
                if(sync_status==DbContract.SYNC_STATUS_FAILED)
                {
                    String studentId=cursor.getString(cursor.getColumnIndex(DbContract.studentId));
                    String courseId=cursor.getString(cursor.getColumnIndex(DbContract.studentId));
                    String date=cursor.getString(cursor.getColumnIndex(DbContract.studentId));
                    String presence=cursor.getString(cursor.getColumnIndex(DbContract.studentId));

                }
            }
        }

    }

    public boolean checkNetworkConnection(Context context)
    {
        ConnectivityManager connectivityManager=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
        return (networkInfo!=null && networkInfo.isConnected());
    }
}
