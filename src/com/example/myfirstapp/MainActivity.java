package com.example.myfirstapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuInflater;
import android.content.Intent;
import android.widget.EditText;
import com.example.myfirstapp.services.RecordService;
import com.example.myfirstapp.database.Record;

public class MainActivity extends Activity
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
    }

    public void sendMessage(View view){
        EditText artistName = (EditText) findViewById(R.id.artist_name);
    	EditText songName = (EditText) findViewById(R.id.song_name);
        Record record = new Record(artistName.toString(), 
                                   songName.toString());
        datasource.storeRecord(record);
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
