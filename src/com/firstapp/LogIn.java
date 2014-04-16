package com.firstapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.facebook.*;
import com.facebook.model.*;

public class LogIn extends ActionBarActivity  {

	private TextView selectedLabels;
	private ListView LabelListView;  
	
	private String[] labelItems = new String[] {"label1","label2", "label3", "label4", "label5", "label6", "label7" }; 
	private boolean[] labelStates = new boolean[] {true, false, false, false, false, false,false };
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		selectedLabels = (TextView)findViewById(R.id.selected_labels);
		
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
		
		final BootstrapButton show_listview_button = (BootstrapButton) findViewById(R.id.show_listview_button);
		show_listview_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Intent intentEventListActivity = new Intent(LogIn.this, EventListActivity.class);
        		startActivity(intentEventListActivity);
            }
        });
		
		/** start Facebook Login  **/
		Session.openActiveSession(this, true, new Session.StatusCallback() {
		    @SuppressWarnings("deprecation")
			@Override
		    public void call(Session session, SessionState state, Exception exception) {
		    	if (session.isOpened()) {
		    		// make request to the /me API
		    		Request.executeMeRequestAsync(session, new Request.GraphUserCallback() {

		    		  // callback after Graph API response with user object
		    		  @Override
		    		  public void onCompleted(GraphUser user, Response response) {
		    			  if (user != null) {
		    				  TextView welcome = (TextView) findViewById(R.id.welcome);
		    				  welcome.setText("Hello " + user.getName() + "!");
		    			  }
		    		  }
		    		});
		    	}
		    }
		});
	}
	
	/** for FaceBook authentication **/
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
	  super.onActivityResult(requestCode, resultCode, data);
	  Session.getActiveSession().onActivityResult(this, requestCode, resultCode, data);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.login_activity_actions, menu);
	    return super.onCreateOptionsMenu(menu);
	}
	
	/** Action bar functions **/
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	    
        	case R.id.action_labels:
        		AlertDialog ad = new AlertDialog.Builder(LogIn.this)
					.setTitle("select labels")
					.setMultiChoiceItems(labelItems, labelStates, new DialogInterface.OnMultiChoiceClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which, boolean isChecked) {
						}})
					.setPositiveButton("OK", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,int whichButton){  
							for (int i = 0; i < labelItems.length; i++){ 
								labelStates[i] = LabelListView.getCheckedItemPositions().get(i);
							}
							showLabelContent();
							dialog.dismiss();
						}
					})  
					.setNegativeButton("Cancel", null)
					.show();  
	        		
	        	LabelListView = ad.getListView(); 
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
	
	private void showLabelContent() {
		String content = "";
		for (int i = 0; i < labelItems.length; i++){ 
			if (labelStates[i]) {
				content += labelItems[i] + " ";
			}
		}
		selectedLabels.setText(content);
	}

}