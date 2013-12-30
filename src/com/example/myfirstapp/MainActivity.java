package com.example.myfirstapp;

import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.widget.ListView;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView; 
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myfirstapp.database.Record;
import com.example.myfirstapp.services.RecordService;

public class MainActivity extends ListActivity
{
    private RecordService datasource;
    private static final String TAG = "DisplayRecord";

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        ListView listView1 = (ListView) findViewById(android.R.id.list);
        datasource = new RecordService(this);
        datasource.open();
        
        List<Record> records = datasource.getRecords();
        
        ArrayAdapter<Record> adapter = new ArrayAdapter<Record>(
        		this, android.R.layout.simple_list_item_activated_1, records);
        setListAdapter(adapter);


        listView1.setOnItemClickListener(new OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
        		long id) {
        	// Try to find another way to get the record instance
        	Record record = datasource.getRecords().get(position);
        	Intent intent = new Intent(MainActivity.this, ShowRecordActivity.class);
        	intent.putExtra("RecordId", String.valueOf(record.getId()));
        	startActivity(intent);
        }});
    }

    public void createRecord(View view){
        EditText artistName = (EditText) findViewById(R.id.artist_name);
    	EditText songName = (EditText) findViewById(R.id.song_name);
    	String artist = artistName.getText().toString();
    	String song = songName.getText().toString(); 
        Record record = new Record(artist, song);
        datasource.storeRecord(record);
        
        updateRecordAdapter(record);
        clearFields(artistName, songName);
        Toast.makeText(getBaseContext(), 
				"Created record with artist: " + artist + " and song title: " + song + ".", Toast.LENGTH_LONG).show();
    }
    
    private void updateRecordAdapter(Record record){
    	@SuppressWarnings("unchecked")
		ArrayAdapter<Record> adapter = (ArrayAdapter<Record>) getListAdapter();
    	adapter.add(record);
    	adapter.notifyDataSetChanged();
    }
    
    private void clearFields(EditText... dictText) {
    	for(EditText field: dictText){
    		field.setText("");
    	}
    }
}
