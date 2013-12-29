package com.example.myfirstapp;

import com.example.myfirstapp.database.Record;
import com.example.myfirstapp.services.RecordService;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class DisplayRecordActivity extends Activity {
	
	private RecordService datasource;
	private Record record;
	private static final String TAG = "DisplayRecord";
	
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
	    
	    EditText artistName = (EditText) findViewById(R.id.edit_artist_name);
    	EditText songName = (EditText) findViewById(R.id.edit_song_name);
   	
    	artistName.setText(record.getArtist());
    	songName.setText(record.getSong());    	
	}
	
	public void update(View view) {
//		datasource.storeRecord(record);
		Intent intent = new Intent(DisplayRecordActivity.this, MainActivity.class);
		startActivity(intent);
	}
	
	public void delete(View view) {
		datasource.deleteRecord(record);
		Intent intent = new Intent(DisplayRecordActivity.this, MainActivity.class);
		startActivity(intent);
	}
}
