package com.example.myfirstapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class TestDatabaseActivity extends SQLiteOpenHelper {
	  public static final String TABLE_RECORDS = "records";
	  public static final String COLUMN_ID = "_id";
	  public static final String COLUMN_SONG = "song";
	  public static final String COLUMN_ARTIST = "artist";

	  private static final String DATABASE_NAME = "records.db";
	  private static final int DATABASE_VERSION = 1;

	  // Database creation sql statement
	  private static final String DATABASE_CREATE = "create table " 
	  	   + TABLE_RECORDS 
	  	   + "(" + COLUMN_ID
	       + " integer primary key autoincrement, "
	       + COLUMN_ARTIST + " text not null, " 
	       + COLUMN_SONG + " text not null);";

	  public TestDatabaseActivity(Context context) {
	    super(context, DATABASE_NAME, null, DATABASE_VERSION);
	  }

	  @Override
	  public void onCreate(SQLiteDatabase database) {
	    database.execSQL(DATABASE_CREATE);
	  }

	  @Override
	  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	    Log.w(TestDatabaseActivity.class.getName(),
	        "Upgrading database from version " + oldVersion + " to "
	            + newVersion + ", which will destroy all old data");
	    db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECORDS);
	    onCreate(db);
	  }

}