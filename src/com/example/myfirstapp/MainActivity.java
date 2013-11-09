package com.example.myfirstapp;

import java.util.List;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import com.example.myfirstapp.database.Record;
import com.example.myfirstapp.services.RecordService;

public class MainActivity extends ListActivity
{
    private RecordService datasource;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        datasource = new RecordService(this);
        datasource.open();
        
        List<Record> records = datasource.getRecords();
        
        ArrayAdapter<Record> adapter = new ArrayAdapter<Record>(
        		this, android.R.layout.simple_list_item_1, records);
        setListAdapter(adapter);
    }

    public void sendMessage(View view){
        EditText artistName = (EditText) findViewById(R.id.artist_name);
    	EditText songName = (EditText) findViewById(R.id.song_name);
        Record record = new Record(artistName.getText().toString(), 
                                   songName.getText().toString());
        datasource.storeRecord(record);
        
        updateRecordAdapter(record);
    }
    
    private void updateRecordAdapter(Record record){
    	@SuppressWarnings("unchecked")
		ArrayAdapter<Record> adapter = (ArrayAdapter<Record>) getListAdapter();
    	adapter.add(record);
    	adapter.notifyDataSetChanged();
    }

    // @Override
    // public boolean onCreateOptionsMenu(Menu menu) {
    //     // Inflate the menu items for use in the action bar
    //     MenuInflater inflater = getMenuInflater();
    //     inflater.inflate(R.layout.main_activity_actions, menu);
    //     return super.onCreateOptionsMenu(menu);
    // }

    // @Override
    // public boolean onOptionsItemSelected(MenuItem item) {
    //     // Handle presses on the action bar items
    //     switch (item.getItemId()) {
    //         case R.id.action_search:
    //             openSearch();
    //             return true;
    //         case R.id.action_settings:
    //             openSettings();
    //             return true;
    //         default:
    //             return super.onOptionsItemSelected(item);
    //     }
    // }

    // public void openSearch(){
    //     // do something
    // }

    // public void openSettings(){
    //     // do something else
    // }
}
