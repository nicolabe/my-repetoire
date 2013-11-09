package com.example.myfirstapp.services;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.example.myfirstapp.database.TestDatabaseActivity;
import com.example.myfirstapp.database.Record;	

public class RecordService {

  // Database fields
  private SQLiteDatabase database;
  private TestDatabaseActivity dbHelper;
  private String[] allColumns = { TestDatabaseActivity.COLUMN_ID,
      TestDatabaseActivity.COLUMN_SONG, TestDatabaseActivity.COLUMN_ARTIST };

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

  // public void deleteComment(Comment comment) {
  //   long id = comment.getId();
  //   System.out.println("Comment deleted with id: " + id);
  //   database.delete(MySQLiteHelper.TABLE_COMMENTS, MySQLiteHelper.COLUMN_ID
  //       + " = " + id, null);
  // }

  // public List<Comment> getAllComments() {
  //   List<Comment> comments = new ArrayList<Comment>();

  //   Cursor cursor = database.query(MySQLiteHelper.TABLE_COMMENTS,
  //       allColumns, null, null, null, null, null);

  //   cursor.moveToFirst();
  //   while (!cursor.isAfterLast()) {
  //     Comment comment = cursorToComment(cursor);
  //     comments.add(comment);
  //     cursor.moveToNext();
  //   }
  //   // make sure to close the cursor
  //   cursor.close();
  //   return comments;
  // }

  // private Comment cursorToComment(Cursor cursor) {
  //   Comment comment = new Comment();
  //   comment.setId(cursor.getLong(0));
  //   comment.setComment(cursor.getString(1));
  //   return comment;
  // }
} 