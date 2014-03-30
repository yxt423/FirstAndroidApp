package com.firstapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LogIn extends Activity {
	
	Intent intentEventDescription;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		intentEventDescription = new Intent(this, EventDescription.class);
		
		final Button login_button = (Button) findViewById(R.id.login_button);
		login_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
        		startActivity(intentEventDescription);
            }
        });
		
	}

}