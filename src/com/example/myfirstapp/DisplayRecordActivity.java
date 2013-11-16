package com.example.myfirstapp;

import com.example.myfirstapp.database.Record;
import com.example.myfirstapp.services.RecordService;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class DisplayRecordActivity extends Activity {
	
	private RecordService datasource;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    
	    datasource = new RecordService(this);
        datasource.open();

	    // Get the message from the intent
	    Intent intent = getIntent();
	    String record_id = intent.getStringExtra("RecordId");
//	    Record record = datasource.getRecord(record_id);
	    // Set the text view as the activity layout
	    setContentView(R.layout.record_list_item);
	}
}
