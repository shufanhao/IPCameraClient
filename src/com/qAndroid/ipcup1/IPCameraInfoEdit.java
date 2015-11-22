package com.qAndroid.ipcup1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class IPCameraInfoEdit extends Activity {
	private IPCameraInfoAdapter mEditAdapter;
	
	private EditText mip;
	private EditText mport;
	private EditText musername;
	private EditText mpassword;
	private EditText mseries;
	
	//private RadioButton mhip;
	//private RadioButton mfip;
	
	private Button confirmBtn;
	private Button cancleBtn;
	private Button loginBtn;
	private Long mId;
	
	private SharedPreferences sp;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.edit);
		sp = getSharedPreferences("userInfo", Context.MODE_WORLD_READABLE);// 创造实例化对象
		mEditAdapter = new IPCameraInfoAdapter(this);
		mEditAdapter.open();
		
		mip = (EditText) findViewById(R.id.ipVal);
		mport = (EditText) findViewById(R.id.portVal);
		musername = (EditText) findViewById(R.id.usernameVal);
		mpassword = (EditText) findViewById(R.id.passwordVal);
		mseries = (EditText) findViewById(R.id.seriesVal);
		
		confirmBtn = (Button) findViewById(R.id.ok);
		confirmBtn.setOnClickListener(new OnClickListener() {
			//@Override
			public void onClick(View v) {
				String ip = mip.getText().toString();
				String fport = mport.getText().toString();
				int port = Integer.parseInt(fport.trim());
				String username = musername.getText().toString();
				String password = mpassword.getText().toString();
				String series = mseries.getText().toString();
				/*mRadioGroup.setOnCheckedChangeListener(
						new OnCheckedChangeListener(){
					public void onCheckedChanged(
							)
				}*/
				
				if (mId != null) {
					mEditAdapter.updateStuInfo(mId, ip, port ,username , password ,series );					
				} else {
					mEditAdapter.addStuInfo( ip, port , username , password ,series);
				}
				
				Intent i = new Intent();
				setResult(RESULT_OK, i);
				finish();
			}			
		});
		
		cancleBtn = (Button) findViewById(R.id.cancel);
		cancleBtn.setOnClickListener(new OnClickListener() {
			//@Override
			public void onClick(View v) {
				Intent i = new Intent();
				setResult(RESULT_OK, i);
				finish();
			}			
		});
		
		loginBtn = (Button) findViewById(R.id.login);
		loginBtn.setOnClickListener(new OnClickListener() {
			//@Override
			public void onClick(View v) {
				String ip = mip.getText().toString();
				String fport = mport.getText().toString();
				//int port = Integer.parseInt(fport.trim());
				String username = musername.getText().toString();
				String password = mpassword.getText().toString();
				M_arg.ip = ip;
				M_arg.port= fport;
				M_arg.username=username;
				M_arg.password=password;
				if (username.equals("admin") && password.equals("")) {
					
					
						Editor editor = sp.edit();
						editor.putString("ip", ip);
						editor.putString("port", fport);
						editor.putString("USER_NAME", username);
						editor.putString("password", password);
						editor.commit();
						
						
							Toast.makeText(IPCameraInfoEdit.this, "登录成功",
									Toast.LENGTH_SHORT).show();
							Intent intent = new Intent(IPCameraInfoEdit.this,
									indexcontent.class);
							
							IPCameraInfoEdit.this.startActivity(intent);
						} 
					}	
		});
		
		ImageButton imagebutton = (ImageButton) findViewById(R.id.back_btn);
		imagebutton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();	//返回
			}
		});
		
		mId = null;
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			String ip = extras.getString(IPCameraInfoAdapter.KEY_STUDENT_IP);
			String username = extras.getString(IPCameraInfoAdapter.KEY_STUDENT_USERNAME);
			String password = extras.getString(IPCameraInfoAdapter.KEY_STUDENT_PASSWORD);
			String series = extras.getString(IPCameraInfoAdapter.KEY_STUDENT_SERIES);
			int port = extras.getInt(IPCameraInfoAdapter.KEY_STUDENT_PORT);
			mId = extras.getLong(IPCameraInfoAdapter.KEY_ID);
			
			if (ip != null) {
				mip.setText(ip);
			}
			mport.setText(Integer.toString(port));
			if (username != null) {
				musername.setText(username);
			}
			
			
				mpassword.setText(password);
			
				if (series != null) {
					mseries.setText(series);
				}
			
						
		}
	}

}
