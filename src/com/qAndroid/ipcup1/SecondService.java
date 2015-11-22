package com.qAndroid.ipcup1;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;

public class SecondService extends Service {

	IBinder binder = new MyBinder();
	private static final String TAG = "MyService";
	byte[] datahtml;
	String htmlcode;
	byte[] datahtmlafter;
	String htmlcodeafter;
	String alarm;
	String alarmafter;
	String textenter;
	String textenterafter;
	private boolean checkalarm = false;
	private String seriesflag="F";  
	// 当service与activity成功调用时调用这个方法
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return binder;
	}

	class MyBinder extends Binder {

		//定义一个action常量,定义一个隐士的Intent
		private static final String MY_ACTION = "com.qAndroid.ipcup1.MY_ACTION";
		// data是activity 发往service的。reply是service发往activity
		@Override
		protected boolean onTransact(int code,  Parcel data,  Parcel reply,
				int flags) throws RemoteException {
			// TODO Auto-generated method stub
			final  Handler whilehandler = new Handler();
			Runnable runnable = new Runnable() {
				@Override
				public void run() {
					// TODO Auto-generated method stub				
					checkalarm = checkalarm();
					whilehandler.postDelayed(this, 10000);//每隔1s钟
					if (checkalarm) {
						// 获得了这个alarm然后再每隔2s钟再检测一次是不是还是同样的alarm如果不是或者是就返回给activity
						Intent intent = new Intent();
						//设置avtion属性
						intent.setAction(MY_ACTION);
						intent.putExtra("msg", "开门");
						if(M_arg.count==0){
							sendBroadcast(intent);//发出广播
							 M_arg.count++;
							 try
							 {
							 Thread.sleep(5000);
							 }
							 catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
						}
						else{
							 Handler delayhandler = new Handler();
								delayhandler.postDelayed(new Runnable() {
										@Override
										public void run() {
											// TODO Auto-generated method stub
											 M_arg.count=0;
										}
									}, 10000);	
								}
						}
						//Log.e("data", s);//程序在这个地方崩掉
					}
			};
			String s = data.readString();
			reply.writeString("from service: reply");
			whilehandler.postDelayed(runnable, 10000);// 打开定时器，执行操作 实现每隔2s钟重复执行checkalarm()一次
			return super.onTransact(code, data, reply, flags);
		}
	}

	public boolean checkalarm() {
		
		 try {
				datahtml=getHtml();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	   
			try {
				htmlcode = new String(datahtml,"gb2312");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   textenter = htmlcode.replaceAll("\n", "");//去掉回车符
		   if(M_arg.series.equals(seriesflag))
			{
		   alarm = textenter.substring(128,142);//取出123到137位
		   System.out.println("index" + alarm);
			}
		   else
		   {
			   alarm = textenter.substring(69,83);//取出123到137位
			   System.out.println("index" + alarm);
		   }
		   if(alarm.equals("alarm_status=2")){//正常情况下alarm是alarm_status=0
			   checkalarm = true;
			   Log.e("msg1", "第一次执行true");
		   }
		   else {
			   checkalarm = false;
			   Log.e("msg1", "第一次执行false");
		   }
		return checkalarm;
	}

	public byte[] getHtml() throws Exception {
		String stripportService = "http://" + M_arg.ip + ":"
				+ M_arg.port
				+ "/get_status.cgi";
		URL url = new URL(stripportService);//我是在局域网里面试的可以访问改地址。
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setConnectTimeout(5 * 1000);
		InputStream inStream = conn.getInputStream();

		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = inStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, len);
		}
		byte[] data = outStream.toByteArray();// 网页的二进制数据
		outStream.close();
		inStream.close();
		return data;
	}

	// service 创建时被调用
	@Override
	public void onCreate() {
		Log.e(TAG, "start onCreate~~~");
		super.onCreate();
	}

	// 客户端调用startservice()方法启动service时，该方法被调用
	@Override
	public void onStart(Intent intent, int startId) {
		Log.e(TAG, "start onStart~~~");
		super.onStart(intent, startId);
	}

	// 当service不再使用是被调用
	@Override
	public void onDestroy() {
		Log.e(TAG, "start onDestroy~~~");
		super.onDestroy();
	}
}
