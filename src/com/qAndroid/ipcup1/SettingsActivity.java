package com.qAndroid.ipcup1;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import android.app.Activity;
import android.app.Service;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

public class SettingsActivity extends Activity {
	private static final String TAG = "SharedPreferencesDemo";
	// ������ݵ�xml�ļ���
	private static final String SETING_DATA = "setingData";
	private String seriesflag="F"; 
	private boolean flagalarm = true;
	private boolean flagupdo = true;
	private boolean flagleri = true;
	private LinearLayout linearletori;
	private LinearLayout linearuptodo;
	private LinearLayout linearleri;
	private LinearLayout linearupdo;
	private LinearLayout linearstletori;
	private LinearLayout linearstuptodo;
	private LinearLayout linearalarm;
	private LinearLayout linearreboot;
	private EditText brightness=null ;
	private EditText contrast=null ;
	private double light,lightness,pic,pict;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.seting);
		brightness = (EditText) findViewById(R.id.editText1);// IP地址
		contrast= (EditText) findViewById(R.id.editText2);
		
		final Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
		final Spinner spinner = (Spinner) findViewById(R.id.spinner);

		
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View arg1,
					int pos, long id) {
				//String result = parent.getItemAtPosition(pos).toString(); // ��ȡѡ�����ֵ
				//Log.i("Spinnerʾ示例", result);
              if(pos==0){
					
				}
				 if(pos==1){
					BasicHttpParams httpParams;
					httpParams = new BasicHttpParams();
					HttpConnectionParams.setConnectionTimeout(httpParams, 1000);
					HttpConnectionParams.setSoTimeout(httpParams, 1000);
					DefaultHttpClient httpClient = new DefaultHttpClient();
					String stripport = "http://" + M_arg.ip + ":"
							+ M_arg.port
							+ "/camera_control.cgi?param=3&value=0&user="
							+ M_arg.username + "&pwd="
							+ M_arg.password;
					HttpGet get = new HttpGet(stripport);
					try {
						httpClient.execute(get);
					} catch (ClientProtocolException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
				
				}
				 if(pos==2){
					BasicHttpParams httpParams;
					httpParams = new BasicHttpParams();
					HttpConnectionParams.setConnectionTimeout(httpParams, 1000);
					HttpConnectionParams.setSoTimeout(httpParams, 1000);
					DefaultHttpClient httpClient = new DefaultHttpClient();
					String stripport = "http://" + M_arg.ip + ":"
							+ M_arg.port
							+ "/camera_control.cgi?param=3&value=1&user="
							+ M_arg.username + "&pwd="
							+ M_arg.password;
					
					
					HttpGet get = new HttpGet(stripport);
					try {
						httpClient.execute(get);
					} catch (ClientProtocolException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				
				}
				 if(pos==3){
						BasicHttpParams httpParams;
						httpParams = new BasicHttpParams();
						HttpConnectionParams.setConnectionTimeout(httpParams, 1000);
						HttpConnectionParams.setSoTimeout(httpParams, 1000);
						DefaultHttpClient httpClient = new DefaultHttpClient();
						String stripport = "http://" + M_arg.ip + ":"
								+ M_arg.port
								+ "/camera_control.cgi?param=3&value=2&user="
								+ M_arg.username + "&pwd="
								+ M_arg.password;
						
						
						HttpGet get = new HttpGet(stripport);
						try {
							httpClient.execute(get);
						} catch (ClientProtocolException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					
					}
				 
				
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});

		// Ϊѡ���б�����OnItemSelectedListener�¼�����
		spinner2.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View arg1,
					int pos, long id) {
				//String result = parent.getItemAtPosition(pos).toString(); // ��ȡѡ�����ֵ
				//Log.i("Spinnerʾ示例", result);
              if(pos==0){
					
				}
				 if(pos==1){
					BasicHttpParams httpParams;
					httpParams = new BasicHttpParams();
					HttpConnectionParams.setConnectionTimeout(httpParams, 1000);
					HttpConnectionParams.setSoTimeout(httpParams, 1000);
					DefaultHttpClient httpClient = new DefaultHttpClient();
					String stripport = "http://" + M_arg.ip + ":"
							+ M_arg.port
							+ "/camera_control.cgi?param=0&value=2&user="
							+ M_arg.username + "&pwd="
							+ M_arg.password;
					String stripport2 = "http://" + M_arg.ip + ":"
							+ M_arg.port
							+ "/camera_control.cgi?param=0&value=0&user="
							+ M_arg.username + "&pwd="
							+ M_arg.password;
					if(M_arg.series.equals(seriesflag)){
					HttpGet get = new HttpGet(stripport);
					try {
						httpClient.execute(get);
					} catch (ClientProtocolException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					}
					else{
						HttpGet get = new HttpGet(stripport2);
						try {
							httpClient.execute(get);
						} catch (ClientProtocolException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				
				}
				
				}
				 if(pos==2){
					BasicHttpParams httpParams;
					httpParams = new BasicHttpParams();
					HttpConnectionParams.setConnectionTimeout(httpParams, 1000);
					HttpConnectionParams.setSoTimeout(httpParams, 1000);
					DefaultHttpClient httpClient = new DefaultHttpClient();
					String stripport = "http://" + M_arg.ip + ":"
							+ M_arg.port
							+ "/camera_control.cgi?param=0&value=32&user="
							+ M_arg.username + "&pwd="
							+ M_arg.password;
					String stripport2 = "http://" + M_arg.ip + ":"
							+ M_arg.port
							+ "/camera_control.cgi?param=0&value=1&user="
							+ M_arg.username + "&pwd="
							+ M_arg.password;
					if(M_arg.series.equals(seriesflag)){
					HttpGet get = new HttpGet(stripport);
					try {
						httpClient.execute(get);
					} catch (ClientProtocolException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					}
					else{
						HttpGet get = new HttpGet(stripport2);
						try {
							httpClient.execute(get);
						} catch (ClientProtocolException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				
				}
				
				}
				 
				
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});
		
		
		
		ImageButton imagebutton = (ImageButton) findViewById(R.id.back_btn);
		imagebutton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(SettingsActivity.this,
						"您选择的是" + spinner2.getSelectedItem().toString(),
						Toast.LENGTH_SHORT).show();
				finish();	//返回
			}
		});
		
		linearletori = (LinearLayout) findViewById(R.id.letori);
		linearletori.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				BasicHttpParams httpParams;
				httpParams = new BasicHttpParams();
				HttpConnectionParams.setConnectionTimeout(httpParams, 1000);
				HttpConnectionParams.setSoTimeout(httpParams, 1000);
				DefaultHttpClient httpClient = new DefaultHttpClient();
				String stripport = "http://" + M_arg.ip + ":"
						+ M_arg.port
						+ "/decoder_control.cgi?command=10&user="
						+ M_arg.username + "&pwd="
						+ M_arg.password;
				String stripport1 = "http://" + M_arg.ip + ":"
						+ M_arg.port
						+ "/decoder_control.cgi?command=28&user="
						+ M_arg.username + "&pwd="
						+ M_arg.password;
				Vibrator vibrator = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);// 这段代码是实现震动效果
				vibrator.vibrate(new long[] { 0, 200 }, -1);
				
				if(M_arg.series.equals(seriesflag)){
					HttpGet get = new HttpGet(stripport1);
					try {
						httpClient.execute(get);
					} catch (ClientProtocolException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				else{
				HttpGet get = new HttpGet(stripport);
				try {
					httpClient.execute(get);
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
			}
		});
		linearuptodo = (LinearLayout) findViewById(R.id.uptodo);
		linearuptodo.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Vibrator vibrator = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);// 这段代码是实现震动效果
				vibrator.vibrate(new long[] { 0, 200 }, -1);
				BasicHttpParams httpParams;
				httpParams = new BasicHttpParams();
				HttpConnectionParams.setConnectionTimeout(httpParams, 1000);
				HttpConnectionParams.setSoTimeout(httpParams, 1000);
				DefaultHttpClient httpClient = new DefaultHttpClient();
				String stripport = "http://" + M_arg.ip + ":"
						+ M_arg.port
						+ "/decoder_control.cgi?command=11&user="
						+ M_arg.username + "&pwd="
						+ M_arg.password;
				String stripport1 = "http://" + M_arg.ip + ":"
						+ M_arg.port
						+ "/decoder_control.cgi?command=26&user="
						+ M_arg.username + "&pwd="
						+ M_arg.password;
				if(M_arg.series.equals(seriesflag)){
					HttpGet get = new HttpGet(stripport1);
					try {
						httpClient.execute(get);
					} catch (ClientProtocolException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				else{
				HttpGet get = new HttpGet(stripport);
				try {
					httpClient.execute(get);
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
			}
		});
		linearupdo = (LinearLayout) findViewById(R.id.updo);
		linearupdo.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Vibrator vibrator = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);// 这段代码是实现震动效果
				vibrator.vibrate(new long[] { 0, 200 }, -1);
				BasicHttpParams httpParams;
				httpParams = new BasicHttpParams();
				HttpConnectionParams.setConnectionTimeout(httpParams, 1000);
				HttpConnectionParams.setSoTimeout(httpParams, 1000);
				DefaultHttpClient httpClient = new DefaultHttpClient();
				String stripport = "http://" + M_arg.ip + ":"
						+ M_arg.port
						+ "/camera_control.cgi?param=5&value=0&user="
						+ M_arg.username + "&pwd="
						+ M_arg.password;
				String stripport1 = "http://" + M_arg.ip + ":"
						+ M_arg.port
						+ "/camera_control.cgi?param=5&value=1&user="
						+ M_arg.username + "&pwd="
						+ M_arg.password;
				String stripport2 = "http://" + M_arg.ip + ":"
						+ M_arg.port
						+ "/camera_control.cgi?param=5&value=2&user="
						+ M_arg.username + "&pwd="
						+ M_arg.password;
					
				if (flagupdo) {
					flagupdo = false;				
					if(M_arg.series.equals(seriesflag)){
						HttpGet get = new HttpGet(stripport1);
						try {
							httpClient.execute(get);
						} catch (ClientProtocolException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						}
						else{
							HttpGet get = new HttpGet(stripport2);
							try {
								httpClient.execute(get);
							} catch (ClientProtocolException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
				}
				else{
					flagupdo = true;
					HttpGet get = new HttpGet(stripport);
					try {
						httpClient.execute(get);
					} catch (ClientProtocolException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
					
				
		});
		linearleri = (LinearLayout) findViewById(R.id.leri);
		linearleri.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				// TODO Auto-generated method stub
				Vibrator vibrator = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);// 这段代码是实现震动效果
				vibrator.vibrate(new long[] { 0, 200 }, -1);
				
				BasicHttpParams httpParams;
				httpParams = new BasicHttpParams();
				HttpConnectionParams.setConnectionTimeout(httpParams, 1000);
				HttpConnectionParams.setSoTimeout(httpParams, 1000);
				DefaultHttpClient httpClient = new DefaultHttpClient();
				String stripport = "http://" + M_arg.ip + ":"
						+ M_arg.port
						+ "/camera_control.cgi?param=5&value=0&user="
						+ M_arg.username + "&pwd="
						+ M_arg.password;
				String stripport1 = "http://" + M_arg.ip + ":"
						+ M_arg.port
						+ "/camera_control.cgi?param=5&value=1&user="
						+ M_arg.username + "&pwd="
						+ M_arg.password;
				String stripport2 = "http://" + M_arg.ip + ":"
						+ M_arg.port
						+ "/camera_control.cgi?param=5&value=2&user="
						+ M_arg.username + "&pwd="
						+ M_arg.password;
					
				if (flagleri) {
					flagleri = false;				
					if(M_arg.series.equals(seriesflag)){
						HttpGet get = new HttpGet(stripport2);
						try {
							httpClient.execute(get);
						} catch (ClientProtocolException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						}
						else{
							HttpGet get = new HttpGet(stripport1);
							try {
								httpClient.execute(get);
							} catch (ClientProtocolException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
				}
				else{
					flagleri = true;
					HttpGet get = new HttpGet(stripport);
					try {
						httpClient.execute(get);
					} catch (ClientProtocolException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
					
		});
		
		linearstuptodo = (LinearLayout) findViewById(R.id.stuptodo);// 拍照
        linearstuptodo.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Vibrator vibrator = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);// 这段代码是实现震动效果
				vibrator.vibrate(new long[] { 0, 200 }, -1);
				BasicHttpParams httpParams;
				httpParams = new BasicHttpParams();
				HttpConnectionParams.setConnectionTimeout(httpParams, 1000);
				HttpConnectionParams.setSoTimeout(httpParams, 1000);
				DefaultHttpClient httpClient = new DefaultHttpClient();
				String stripport = "http://" + M_arg.ip + ":"
						+ M_arg.port
						+ "/decoder_control.cgi?command=0&user="
						+ M_arg.username + "&pwd="
						+ M_arg.password;
				String stripport1 = "http://" + M_arg.ip + ":"
						+ M_arg.port
						+ "/decoder_control.cgi?command=27&user="
						+ M_arg.username + "&pwd="
						+ M_arg.password;
				if(M_arg.series.equals(seriesflag)){
					HttpGet get = new HttpGet(stripport1);
					try {
						httpClient.execute(get);
					} catch (ClientProtocolException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				else{
				HttpGet get = new HttpGet(stripport);
				try {
					httpClient.execute(get);
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
			}
		});
        linearstletori = (LinearLayout) findViewById(R.id.stletori);
		linearstletori.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Vibrator vibrator = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);// 这段代码是实现震动效果
				vibrator.vibrate(new long[] { 0, 200 }, -1);
				
				BasicHttpParams httpParams;
				httpParams = new BasicHttpParams();
				HttpConnectionParams.setConnectionTimeout(httpParams, 1000);
				HttpConnectionParams.setSoTimeout(httpParams, 1000);
				DefaultHttpClient httpClient = new DefaultHttpClient();
				String stripport = "http://" + M_arg.ip + ":"
						+ M_arg.port
						+ "/decoder_control.cgi?command=0&user="
						+ M_arg.username + "&pwd="
						+ M_arg.password;
				String stripport1 = "http://" + M_arg.ip + ":"
						+ M_arg.port
						+ "/decoder_control.cgi?command=29&user="
						+ M_arg.username + "&pwd="
						+ M_arg.password;
				if(M_arg.series.equals(seriesflag)){
					HttpGet get = new HttpGet(stripport1);
					try {
						httpClient.execute(get);
					} catch (ClientProtocolException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				else{
				HttpGet get = new HttpGet(stripport);
				try {
					httpClient.execute(get);
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
			}
		});
		linearalarm = (LinearLayout) findViewById(R.id.alarm);
		linearalarm.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					if (flagalarm) {
						linearalarm
								.setBackgroundResource(R.drawable.lin72);
				    flagalarm = false;
					Vibrator vibrator = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);// 这段代码是实现震动效果
					vibrator.vibrate(new long[] { 0, 200 }, -1);
					BasicHttpParams httpParams;
					httpParams = new BasicHttpParams();
					HttpConnectionParams.setConnectionTimeout(httpParams, 1000);
					HttpConnectionParams.setSoTimeout(httpParams, 1000);
					DefaultHttpClient httpClient = new DefaultHttpClient();
					String stripport = "http://" + M_arg.ip + ":"
							+ M_arg.port
							+ "/set_alarm.cgi?input_armed=0&user="
							+ M_arg.username + "&pwd="
							+ M_arg.password;
					HttpGet get = new HttpGet(stripport);
					try {
						httpClient.execute(get);
					} catch (ClientProtocolException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				    }
					else
					{
						flagalarm = false;
						linearalarm
						.setBackgroundResource(R.drawable.lin71);
						Vibrator vibrator = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);// 这段代码是实现震动效果
						vibrator.vibrate(new long[] { 0, 200 }, -1);
						BasicHttpParams httpParams;
						httpParams = new BasicHttpParams();
						HttpConnectionParams.setConnectionTimeout(httpParams, 1000);
						HttpConnectionParams.setSoTimeout(httpParams, 1000);
						DefaultHttpClient httpClient = new DefaultHttpClient();
						String stripport = "http://" + M_arg.ip + ":"
								+ M_arg.port
								+ "/set_alarm.cgi?input_armed=1&user="
								+ M_arg.username + "&pwd="
								+ M_arg.password;
						HttpGet get = new HttpGet(stripport);
						try {
							httpClient.execute(get);
						} catch (ClientProtocolException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			});
		 linearreboot = (LinearLayout) findViewById(R.id.reboot);
		 linearreboot.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Vibrator vibrator = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);// 这段代码是实现震动效果
						vibrator.vibrate(new long[] { 0, 200 }, -1);
						
						BasicHttpParams httpParams;
						httpParams = new BasicHttpParams();
						HttpConnectionParams.setConnectionTimeout(httpParams, 1000);
						HttpConnectionParams.setSoTimeout(httpParams, 1000);
						DefaultHttpClient httpClient = new DefaultHttpClient();
						String stripport = "http://" + M_arg.ip + ":"
								+ M_arg.port
								+ "/reboot.cgi?"
								+ M_arg.username + "&pwd="
								+ M_arg.password;
						HttpGet get = new HttpGet(stripport);
						try {
							httpClient.execute(get);
						} catch (ClientProtocolException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});
		 
		 Button button=(Button)findViewById(R.id.save);
		 button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//brightness.setText(M_arg.light);
				light = Double.parseDouble(brightness.getText().toString());
				pic = Double.parseDouble(contrast.getText().toString());
				if(M_arg.series.equals(seriesflag)){
				lightness=light*15+1;
				BasicHttpParams httpParams;
				httpParams = new BasicHttpParams();
				HttpConnectionParams.setConnectionTimeout(httpParams, 1000);
				HttpConnectionParams.setSoTimeout(httpParams, 1000);
				DefaultHttpClient httpClient = new DefaultHttpClient();
				String stripport = "http://" + M_arg.ip + ":"
						+ M_arg.port
						+ "/camera_control.cgi?param=1&value="+lightness+"&user="
						+ M_arg.username + "&pwd="
						+ M_arg.password;
				String stripport1 = "http://" + M_arg.ip + ":"
						+ M_arg.port
						+ "/camera_control.cgi?param=2&value="+pic+"&user="
						+ M_arg.username + "&pwd="
						+ M_arg.password;
				
					HttpGet get = new HttpGet(stripport);
					HttpGet get1 = new HttpGet(stripport1);
					try {
						httpClient.execute(get);
						httpClient.execute(get1);
					} catch (ClientProtocolException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else{
					lightness=light*2+1;
					pict=pic*9;
					BasicHttpParams httpParams;
					httpParams = new BasicHttpParams();
					HttpConnectionParams.setConnectionTimeout(httpParams, 1000);
					HttpConnectionParams.setSoTimeout(httpParams, 1000);
					DefaultHttpClient httpClient = new DefaultHttpClient();
					String stripport = "http://" + M_arg.ip + ":"
							+ M_arg.port
							+ "/camera_control.cgi?param=1&value="+lightness+"&user="
							+ M_arg.username + "&pwd="
							+ M_arg.password;
					String stripport1 = "http://" + M_arg.ip + ":"
							+ M_arg.port
							+ "/camera_control.cgi?param=2&value="+pict+"&user="
							+ M_arg.username + "&pwd="
							+ M_arg.password;
						HttpGet get = new HttpGet(stripport);
						HttpGet get1 = new HttpGet(stripport1);
						try {
							httpClient.execute(get);
							httpClient.execute(get1);
							
						} catch (ClientProtocolException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				Toast.makeText(SettingsActivity.this,"已保存" ,Toast.LENGTH_SHORT).show();
			}

			});
		 SharedPreferences setingData = getSharedPreferences(SETING_DATA, 0);
	        String lighth = setingData.getString("Light", "");
	        String cont = setingData.getString("Cont", "");
	        // ����SharedPreferences�ж�ȡ�������ΪEditText������
	        brightness.setText(lighth);
	        contrast.setText(cont);
	}
	@Override
	protected void onStop() {
		SharedPreferences setingData = getSharedPreferences(SETING_DATA, 0);
		Editor mEditor = setingData.edit();
		mEditor.putString("Light", brightness.getText().toString());
		mEditor.putString("Cont", contrast.getText().toString());
		mEditor.commit();
		Log.v(TAG, "onStop");
		super.onStop();
	}
}


	


