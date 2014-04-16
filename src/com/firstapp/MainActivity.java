package com.firstapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		showAppCover();
	}
	
	private void showAppCover() {
        new Thread() {
            public void run() {
                    try {
                            Thread.sleep(10);
                    } catch (InterruptedException e) {
                            e.printStackTrace();
                    }
                    Intent intentLogIn = new Intent(MainActivity.this, LogIn.class);
            		startActivity(intentLogIn);
                    finish();
            }
        }.start();
	}
}
