package com.qAndroid.ipcup1;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	private EditText username, password, ip, port ;
	private CheckBox rem_pw, auto_login;
	private Button btn_exit;
	public RadioButton mRadio0,mRadio1;
	private String usernameValue, passwordValue, ipValue, portValue ,seriesValue;
	private SharedPreferences sp;
	private Binder binder;
	public static final int STARTACTIVITY = 1;
	public String series="F"; 
	public static Handler handlerstart = new Handler(){
		public void handleMessage(Message msg){
			if(msg.what == STARTACTIVITY){
					Context context = (Context)msg.obj;
					Intent intent = new Intent(context,ListenCall.class);
					intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					context.startActivity(intent);
					//播放音乐
					
			}
			 super.handleMessage(msg);  
		}
	};

	public void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);

		// 去除标题
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.login);

		// 获得实例对象
		sp = getSharedPreferences("userInfo", Context.MODE_WORLD_READABLE);// 创造实例化对象
		ip = (EditText) findViewById(R.id.editText1);// IP地址
		port = (EditText) findViewById(R.id.editText2);// 端口号	
		username = (EditText) findViewById(R.id.editText3);// 用户名
		password = (EditText) findViewById(R.id.editText4);// 密码
		//series = (EditText) findViewById(R.id.editText5);// 密码
		rem_pw = (CheckBox) findViewById(R.id.cb_mima);// 记住密码
		auto_login = (CheckBox) findViewById(R.id.cb_auto);// 自动登录
		mRadio0 = (RadioButton) findViewById(R.id.radio0);
		mRadio1 = (RadioButton) findViewById(R.id.radio1); 
		RadioGroup se = (RadioGroup) findViewById(R.id.radioGroup1);
		se.setOnCheckedChangeListener(ser);
		
		Intent intentbind = new Intent();
		intentbind.setClass(MainActivity.this, SecondService.class);
		//绑定服务由该函数来完成，由第二个函数可知道第二部要做什么
		bindService(intentbind, conn, BIND_AUTO_CREATE);
		// btnQuit = (ImageButton)findViewById(R.id.img_btn);//退出
		btn_exit = (Button) findViewById(R.id.exit);// 退出 文字

		// 判断记住密码多选框状态
		if (sp.getBoolean("ISCHECK", false)) {
			// 设置默认是记录密码状态״̬
			rem_pw.setChecked(true);
			ip.setText(sp.getString("ip", ""));// 可能会有问题
			port.setText(sp.getString("port", ""));//
			username.setText(sp.getString("USER_NAME", ""));
			password.setText(sp.getString("password", ""));
					
					
				
					
				}

			//series.setText(sp.getString("series", ""));
			// 判断自动登录多选框状态״̬
			if (sp.getBoolean("AUTO_ISCHECK", false)) {
				// 设置默认是自动登录状态״̬
				auto_login.setChecked(true);
				// 跳转界面跳转到logoactivity
				M_arg.ip = ipValue;
				M_arg.port=portValue;
				M_arg.username=usernameValue;
				M_arg.password=passwordValue;
				M_arg.series=seriesValue;
				Intent intent = new Intent(MainActivity.this,
						indexcontent.class);
				/*Bundle b = new Bundle();
				// 调用Bundle对象的putString方法，采用Key-value的形式存放数据
				b.putString("ip", ip.getText().toString());
				b.putString("port", port.getText().toString());
				b.putString("username", username.getText().toString());
				b.putString("password", password.getText().toString());
				// 将数据载体Bundle对象放入Intent对象中。
				intent.putExtras(b);*/
				MainActivity.this.startActivity(intent);
			}
		
		//记录监听事件，现在默认用户名是admim 密码空
		Button button=(Button)findViewById(R.id.login);
		button.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Parcel data = Parcel.obtain();
				Parcel reply = Parcel.obtain();
				data.writeString("from activity :data");//给parcel方数据
				//以下这段函数是实现在登录的时候启动一个服务，如果是在外网里面会出现系统崩溃，需要将以下内容注释掉，而如果是在内网里面就会不存在这种问题
				try {
					//一执行transact 就会去执行secondservice的ontransact，并且将相应的四个参数传递到ontransact中
					binder.transact(0, data, reply,0);
					String s = reply.readString();
					//Log.e("msg接收", s); 
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ipValue = ip.getText().toString();
				portValue = port.getText().toString();
				usernameValue = username.getText().toString();
				passwordValue = password.getText().toString();
				seriesValue = series;
				M_arg.ip = ipValue;
				M_arg.port=portValue;
				M_arg.username=usernameValue;
				M_arg.password=passwordValue;
				M_arg.series=seriesValue;
								
				//if (usernameValue.equals("admin") && passwordValue.equals("")) {
					// 登录成功和记住密码框为选中状态才保存用户信息
					if (rem_pw.isChecked()) {
						// 记住用户名和密码
						Editor editor = sp.edit();
						editor.putString("ip", ipValue);
						editor.putString("port", portValue);
						editor.putString("USER_NAME", usernameValue);
						editor.putString("password", passwordValue);
						editor.putString("series", seriesValue);
						editor.commit();
						// 检测当前网络是否连接。如果没有连接则弹出对话框要求连接
						if (isNetworkAvailable()) {
							Toast.makeText(MainActivity.this, "登录成功",
									Toast.LENGTH_SHORT).show();
							Intent intent = new Intent(MainActivity.this,
									indexcontent.class);
							// 创建Buddle对象用来存放数据，Buddle对象可以理解为数据的载体
							/*Bundle b = new Bundle();
							// 调用Bundle对象的putString方法，采用Key-value的形式存放数据
							b.putString("ip", ip.getText().toString());
							b.putString("port", port.getText().toString());
							b.putString("username", username.getText()
									.toString());
							b.putString("password", password.getText()
									.toString());
							// 将数据载体Bundle对象放入Intent对象中。
							intent.putExtras(b);*/
							MainActivity.this.startActivity(intent);
						} else {
							setNetwork();
						}
					}
				//} else {

					//Toast.makeText(MainActivity.this, "用户名或密码错误",
							//Toast.LENGTH_LONG).show();
				//}

			}

		});

		// 监听记住密码多选框状态
		rem_pw.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (rem_pw.isChecked()) {
					System.out.println("记住密码已选中");
					sp.edit().putBoolean("ISCHECK", true).commit();

				} else {

					System.out.println("记住密码未选中");
					sp.edit().putBoolean("ISCHECK", false).commit();

				}

			}
		});

		// 监听自动登录多选框事件
		auto_login.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (auto_login.isChecked()) {
					System.out.println("自动登录已选中");
					sp.edit().putBoolean("AUTO_ISCHECK", true).commit();

				} else {
					System.out.println("自动登录没有选中");
					sp.edit().putBoolean("AUTO_ISCHECK", false).commit();
				}
			}
		});

		/*
		 * btnQuit.setOnClickListener(new OnClickListener() {
		 * 
		 * @Override public void onClick(View v) { System.exit(0);
		 * ActivityManager
		 * activityMgr=(ActivityManager)MainActivity.this.getSystemService
		 * (ACTIVITY_SERVICE); activityMgr.restartPackage(getPackageName()); }
		 * });
		 */
		btn_exit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				System.exit(0);
				ActivityManager activityMgr = (ActivityManager) MainActivity.this
						.getSystemService(ACTIVITY_SERVICE);
				activityMgr.restartPackage(getPackageName());
			}
		});

	}
	
	

	// 当调用这个函数后会出现对话框然后点击设置就会进入相应的无限网络设置界面
	// 由于Android的SDK版本不同所以里面的API和设置方式也是有少量变化的,尤其是在Android 3.0
	// 及后面的版本,UI和显示方式也发生了变化.
	public void setNetwork() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setIcon(android.R.drawable.ic_dialog_alert);
		builder.setTitle(R.string.netstate);
		builder.setMessage(R.string.setnetwork);
		builder.setPositiveButton(R.string.set,
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						Intent intent = null;
						// 首先判断android版本，在高于android 3.0版本时
						if (android.os.Build.VERSION.SDK_INT > 10) {
							intent = new Intent(
									android.provider.Settings.ACTION_WIRELESS_SETTINGS);
						} else {
							intent = new Intent();
							ComponentName component = new ComponentName(
									"com.android.settings",
									"com.android.settings.WirelessSettings");
							intent.setComponent(component);
							intent.setAction("android.intent.action.VIEW");
						}
						startActivity(intent);
					}
				});
		builder.setNegativeButton(R.string.cancel,
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();
					}
				});
		builder.create();
		builder.show();
	}

	// NETWORK
	public boolean isNetworkAvailable() {
		Context context = getApplicationContext();
		ConnectivityManager connect = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connect == null) {
			return false;
		} else// get all network info
		{
			NetworkInfo[] info = connect.getAllNetworkInfo();
			if (info != null) {
				for (int i = 0; i < info.length; i++) {
					if (info[i].getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
				}
			}
		}
		return false;
	}

	// onkeydown这个函数是检测是不是返回键按下，一旦按下就会有对话框问提示信息问是否退出程序
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("提示");
			builder.setMessage("亲！您确定要退出程序吗？");
			builder.setPositiveButton("确定",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							System.exit(0);
						}
					});
			builder.setNegativeButton("取消", null);
			builder.show();
		}
		return true;
	}
	
	 private RadioGroup.OnCheckedChangeListener ser = new 
	           RadioGroup.OnCheckedChangeListener()
	  { 
	    @Override 
	    public void onCheckedChanged(RadioGroup group, int checkedId)
	    { 
	      // TODO Auto-generated method stub 
	      if(checkedId==mRadio0.getId())
	      { 
	        /*��mRadio1�����ݴ���mTextView1*/
	    	  series="F";
	      } 
	      else if(checkedId==mRadio1.getId()) 
	      { 
	        /*��mRadio2�����ݴ���mTextView1*/
	    	  series="H";
	      } 
	      Log.i("series", "选中的为" + series);
	    } 
	  };
	ServiceConnection conn = new ServiceConnection() {
		
		// 成功连接服务，该方法被执行。在该方法中可以通过IBinder对象取得onBind方法的返回值，一般通过向下转型
		@Override
		public void onServiceConnected(ComponentName name, IBinder binder) {
			MainActivity.this.binder = (Binder)binder;//绑定成功之后，MyBinder()传过来的binder对象赋值给mainacitvity的binder
		    Log.i("SERVICE","Connection OK");
		}
		@Override
		public void onServiceDisconnected(ComponentName componentName) {
			// TODO Auto-generated method stub
			 Log.i("SERVICE","Disconnection OK");
		}

	};
}