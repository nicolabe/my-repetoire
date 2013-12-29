package com.example.myfirstapp.services;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.myfirstapp.database.TestDatabaseActivity;
import com.example.myfirstapp.database.Record;	



public class RecordService {

  // Database fields
  private SQLiteDatabase database;
  private TestDatabaseActivity dbHelper;
  private String[] allColumns = { TestDatabaseActivity.COLUMN_ID,
      TestDatabaseActivity.COLUMN_SONG, TestDatabaseActivity.COLUMN_ARTIST };
  private static final String TAG = "Record";
  
  public RecordService(Context context) {
    dbHelper = new TestDatabaseActivity(context);
  }

  public void open() throws SQLException {
    database = dbHelper.getWritableDatabase();
  }

  public void close() {
    dbHelper.close();
  }

  public void storeRecord(Record record) {
    ContentValues values = new ContentValues();
    values.put(TestDatabaseActivity.COLUMN_ARTIST, record.getArtist());
    values.put(TestDatabaseActivity.COLUMN_SONG, record.getSong());
    long insertId = database.insert(TestDatabaseActivity.TABLE_RECORDS, null,
        values);
    Cursor cursor = database.query(TestDatabaseActivity.TABLE_RECORDS,
        allColumns, TestDatabaseActivity.COLUMN_ID + " = " + insertId, null,
        null, null, null);
    cursor.close();
  }
  
  public List<Record> getRecords(){
	  List<Record> records = new ArrayList<Record>();
	  Cursor cursor = database.query(
			  TestDatabaseActivity.TABLE_RECORDS, allColumns, null, null, null, null, null);
	  
	  cursor.moveToFirst();
	  while(!cursor.isAfterLast()){
		  Record record = cursorToRecord(cursor);
		  records.add(record);
		  cursor.moveToNext();
	  }
	  cursor.close();
	  return records;
  }

  public Record getRecord(String record_id) {
	  String record_id_query = TestDatabaseActivity.COLUMN_ID + " = " + record_id;
	  Cursor cursor = database.query(TestDatabaseActivity.TABLE_RECORDS,
		        allColumns, record_id_query, null,
		        null, null, null);
	  cursor.moveToFirst();
	  Record record = cursorToRecord(cursor);
	  cursor.close();
	  return record;
  }
  
  private Record cursorToRecord(Cursor cursor){
	  int indexOfId = 0;
	  int indexOfArtist = 1;
	  int indexOfSong = 2;
	  long id = cursor.getLong(indexOfId);
	  String artist = cursor.getString(indexOfArtist);
	  String song = cursor.getString(indexOfSong);	  
	  return new Record(id, artist, song);
  }

   public void deleteRecord(Record record) {
     long id = record.getId();
     Log.i(TAG, "Record deleted with id: " + id);
     database.delete(TestDatabaseActivity.TABLE_RECORDS, TestDatabaseActivity.COLUMN_ID
         + " = " + id, null);
   }
} 