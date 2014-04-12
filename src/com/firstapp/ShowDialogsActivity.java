package com.firstapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.beardedhen.androidbootstrap.BootstrapButton;

public class ShowDialogsActivity extends Activity {
	
	private BootstrapButton button1;  
	private BootstrapButton button2;  
	private BootstrapButton button3;  
	
	private TextView selectedLabels;
	private ListView areaCheckListView;  
	
	private String[] labelItems = new String[] {"label1","label2", "label3", "label4", "label5", "label6", "label7" }; 
	private boolean[] labelStates = new boolean[] {true, false, false, false, false, false,false };
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_dialogs);
		
		button1 = (BootstrapButton)findViewById(R.id.button1);  
		button2 = (BootstrapButton)findViewById(R.id.button2);  
		button3 = (BootstrapButton)findViewById(R.id.button3);  
		
		button1.setOnClickListener(new ButtonClickListener1());  
		//button2.setOnClickListener(new CheckBoxClickListener());  
		//button3.setOnClickListener(new RadioClickListener());  
		
		selectedLabels = (TextView)findViewById(R.id.selected_labels);
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
	
	class ButtonClickListener1 implements OnClickListener {

		@Override
		public void onClick(View v) {
			AlertDialog ad = new AlertDialog.Builder(ShowDialogsActivity.this)
				.setTitle("select labels")
				.setMultiChoiceItems(labelItems, labelStates, new DialogInterface.OnMultiChoiceClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which, boolean isChecked) {
					}})
					
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int whichButton){  
						for (int i = 0; i < labelItems.length; i++){ 
							labelStates[i] = areaCheckListView.getCheckedItemPositions().get(i);
						}
						showLabelContent();
						dialog.dismiss();
					}
				})  
				
				.setNegativeButton("Cancel", null)
				.show();  
			areaCheckListView = ad.getListView(); 
		}  
	}
}