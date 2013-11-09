package com.example.myfirstapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuInflater;
import android.content.Intent;
import android.widget.EditText;

public class MainActivity extends Activity
{
	public final static String EXTRA_MESSAGE = "com.example.myfirstapp.EXAMPLE";

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

    }

    public void sendMessage(View view){
    	Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText artistName = (EditText) findViewById(R.id.artist_name);
    	EditText songName = (EditText) findViewById(R.id.song_name);
    	String message = String.format("%s - %s", 
                                       artistName.getText().toString(), 
                                       songName.getText().toString());
    	intent.putExtra(EXTRA_MESSAGE, message);
    	startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.layout.main_activity_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_search:
                openSearch();
                return true;
            case R.id.action_settings:
                openSettings();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void openSearch(){
        // do something
    }

    public void openSettings(){
        // do something else
    }
}
