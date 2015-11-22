package com.qAndroid.ipcup1;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageButton;


public class UserActivity extends Activity 
{
  
   
  /** Called when the activity is first created. */
  @Override 
  public void onCreate(Bundle savedInstanceState) 
  { 
    super.onCreate(savedInstanceState); 
    this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	super.onCreate(savedInstanceState);
    setContentView(R.layout.user); 
    
    ImageButton imagebutton = (ImageButton) findViewById(R.id.back_btn);
	imagebutton.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View v) {
			finish();	//返回
		}
	});
	
	
    
  } 
   
  
}