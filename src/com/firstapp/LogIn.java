package com.firstapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import com.beardedhen.androidbootstrap.BootstrapButton;

public class LogIn extends ActionBarActivity  {
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		
		final BootstrapButton login_button = (BootstrapButton) findViewById(R.id.login_button);
		login_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Intent intentEventDescription = new Intent(LogIn.this, EventDescription.class);
        		startActivity(intentEventDescription);
            }
        });

		final BootstrapButton show_dialogs_button = (BootstrapButton) findViewById(R.id.show_dialogs_button);
		show_dialogs_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Intent intentShowDialogsActivity = new Intent(LogIn.this, ShowDialogsActivity.class);
        		startActivity(intentShowDialogsActivity);
            }
        });
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.login_activity_actions, menu);
	    return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    switch (item.getItemId()) {
        	case R.id.action_labels:
	            //openSettings();
	            return true;
	        case R.id.action_search:
	            //openSearch();
	            return true;
	        case R.id.action_settings:
	            //openSettings();
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}

}