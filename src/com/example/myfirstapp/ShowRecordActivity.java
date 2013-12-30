package com.example.myfirstapp;

import com.example.myfirstapp.database.Record;
import com.example.myfirstapp.services.RecordService;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ShowRecordActivity extends Activity {
	
	private RecordService datasource;
	private Record record;
	private static final String TAG = "DisplayRecord";
	private EditText artistName;
	private EditText songName;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    
	    datasource = new RecordService(this);
        datasource.open();

	    // Get the message from the intent
	    Intent intent = getIntent();
	    String record_id = intent.getStringExtra("RecordId");
	    record = datasource.getRecord(record_id);
	    // Set the text view as the activity layout
	    setContentView(R.layout.record_list_item);
	    
	    artistName = (EditText) findViewById(R.id.edit_artist_name);
    	songName = (EditText) findViewById(R.id.edit_song_name);
   	
    	artistName.setText(record.getArtist());
    	songName.setText(record.getSong());
	}
	
	public void update(View view) {
		Log.i(TAG, "Updating record");
		String artist = artistName.getText().toString();
		String song = songName.getText().toString();
    	record.setArtist(artist);
    	record.setSong(song);
    	datasource.updateRecord(record);
		Intent intent = new Intent(ShowRecordActivity.this, MainActivity.class);
		startActivity(intent);
		Toast.makeText(getBaseContext(), 
				"Updated record to artist: " + artist + " and song title: " + song + ".", Toast.LENGTH_LONG).show();
	}
	
	public void delete(View view) {
		Log.i(TAG, "Deleting record");
		datasource.deleteRecord(record);
		Intent intent = new Intent(ShowRecordActivity.this, MainActivity.class);
		startActivity(intent);
		Toast.makeText(getBaseContext(), 
				"Deleted record with id " + record.getId() + ".", Toast.LENGTH_LONG).show();
	}
	
	
}
