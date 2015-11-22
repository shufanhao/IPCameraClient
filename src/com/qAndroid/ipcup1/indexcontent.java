package com.qAndroid.ipcup1;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.net.HttpURLConnection;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.PopupWindow;
import android.widget.Button;
import android.widget.ImageButton;
import android.view.View.OnTouchListener;

public class indexcontent extends Activity implements OnGestureListener {

	private final int MSG_REFRESH = 0;
	private ImageView mImageView;
	private PopupWindow popupwindow;
	private ImageButton imagebutton;
	private String mSaveMessage; 
	private String seriesflag="F"; 
	private boolean mStopStream = true;
	private final String flag = "Content-Length: ";
	private final String flag1 = "\r\n";
	private Bitmap bitmap;
	private boolean flagopen = true;
	private LinearLayout linearexit;
	private LinearLayout linearaudio;
	private LinearLayout linearopen;
	private LinearLayout linearpic;
	private LinearLayout linearhz;
	private LinearLayout linearset;
	public String ip = "";
	public String port = "";
	public String password = "";
	public String username = "";
	private String filename;
	private Handler myHandler;
	private MediaPlayer mediaPlayer;
	private GestureDetector gestureDetector;// 声明gestureDetector对象
	// 计时timer 参数声明
	private long mlCount = 0;
	private long mlTimerUnit = 1000;
	private TextView tvTime;
	private Timer timer = null;
	private TimerTask task = null;
	private Message msgtimer = null;
	static SharedPreferences sp;
	AlertDialog.Builder builder;
	private final static String ALBUM_PATH  
    = Environment.getExternalStorageDirectory() + "/Pictures/"; 
	private ProgressDialog mSaveDialog = null;  
	private static final String mFileName= System.currentTimeMillis() + ".jpg";  
	final String[] mItems = {"静音","小苹果","我为你歌唱","后来","青奥会主题曲"};
	ArrayList <Integer>MultiChoiceID = new ArrayList <Integer>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.jiankong);
		gestureDetector = new GestureDetector(this);
		builder = new AlertDialog.Builder(indexcontent.this);
		// 获取数据
		/*mIntent = getIntent();
		Bundle b = mIntent.getExtras();
		this.ip = b.getString("ip");
		this.port = b.getString("port");
		this.username = b.getString("username");
		this.password = b.getString("password");*/
		tvTime = (TextView) findViewById(R.id.tvTime);
		mImageView = (ImageView) findViewById(R.id.urlImg);
		sp = getSharedPreferences("userInfo", Context.MODE_WORLD_READABLE);// 创造实例化对象
		imagebutton = (ImageButton) findViewById(R.id.list_btn);
		imagebutton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				
					if (popupwindow != null&&popupwindow.isShowing()) {
						popupwindow.dismiss();
						return;
					} else {
						initmPopupWindowView();
						popupwindow.showAsDropDown(v, 0, 5);
					}
								}
		});
		linearexit = (LinearLayout) findViewById(R.id.ly_doorbell_cancel);
		linearexit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Vibrator vibrator = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);// 这段代码是实现震动效果
				vibrator.vibrate(new long[] { 0, 200 }, -1);
				/*
				 * Intent intent=new Intent();
				 * intent.setClass(indexcontent.this,MainActivity.class);
				 * startActivity(intent);
				 */
				finish();
			}
		});
		linearopen = (LinearLayout) findViewById(R.id.ly_doorbell_vq);
		linearopen.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (flagopen) {
					linearopen
							.setBackgroundResource(R.drawable.button_32);
					Vibrator vibrator = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);// 这段代码是实现震动效果
					vibrator.vibrate(new long[] { 0, 200 }, -1);
					BasicHttpParams httpParams;
					httpParams = new BasicHttpParams();
					HttpConnectionParams.setConnectionTimeout(httpParams, 1000);
					HttpConnectionParams.setSoTimeout(httpParams, 1000);
					DefaultHttpClient httpClient = new DefaultHttpClient();
					// HttpGet get = new
					// HttpGet("http://192.168.1.104:82/decoder_control.cgi?command=94&onestep=0&user=admin&pwd=");//继电器闭合，开门
					/*String stripport = "http://" + indexcontent.this.ip + ":"
							+ indexcontent.this.port
							+ "/decoder_control.cgi?command=94&onestep=0&user="
							+ indexcontent.this.username + "&pwd="
							+ indexcontent.this.password;
					String stripport1 = "http://" + indexcontent.this.ip + ":"
							+ indexcontent.this.port
							+ "/decoder_control.cgi?command=95&onestep=0&user="
							+ indexcontent.this.username + "&pwd="
							+ indexcontent.this.password;*/
					String stripport = "http://" + M_arg.ip + ":"
							+ M_arg.port
							+ "/decoder_control.cgi?command=94&onestep=0&user="
							+ M_arg.username + "&pwd="
							+ M_arg.password;
					String stripport1 = "http://" + M_arg.ip + ":"
							+ M_arg.port
							+ "/decoder_control.cgi?command=95&onestep=0&user="
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
					// handler是显示在延时4s后关门。
					Handler openHandler = new Handler();
					openHandler.postDelayed(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							linearopen
									.setBackgroundResource(R.drawable.button_3);
						}
					}, 4000);
					HttpGet get1 = new HttpGet(stripport1);
					try {
						httpClient.execute(get1);
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
		linearaudio = (LinearLayout) findViewById(R.id.ly_doorbell_sound);
		linearaudio.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
							
					Vibrator vibrator = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);// 这段代码是实现震动效果
					vibrator.vibrate(new long[] { 0, 200 }, -1);
					myHandler = new MyHandler();
					MyThread myThread = new MyThread();
					myThread.start();
			}
					
				
		});
		linearhz = (LinearLayout) findViewById(R.id.ly_doorbell_hz);
		linearhz.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				linearhz
				.setBackgroundResource(R.drawable.button_54);
				CreatDialog();
				Vibrator vibrator = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);// 这段代码是实现震动效果
				vibrator.vibrate(new long[] { 0, 200 }, -1);

			}
		});
		
		linearpic = (LinearLayout) findViewById(R.id.ly_doorbell_pic);// 拍照
        linearpic.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				linearpic.setBackgroundResource(R.drawable.button_22);
				Vibrator vibrator = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);// 这段代码是实现震动效果
				vibrator.vibrate(new long[] { 0, 200 }, -1);
				mediaPlayer = MediaPlayer.create(indexcontent.this, R.raw.photo);
				mediaPlayer.start(); // 启动或者恢复播放
				mediaPlayer
						.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {//
							public void onCompletion(MediaPlayer arg0) {
								mediaPlayer.release();
								
							}
						});
				Handler photoHandler = new Handler();
				photoHandler.postDelayed(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						linearpic
								.setBackgroundResource(R.drawable.button2);
					}
				}, 100);
				if(bitmap!=null){
				mSaveDialog = ProgressDialog.show(indexcontent.this, "保存图片", "图片正在保存中，请稍等...", true);  
                new Thread(saveFileRunnable).start();  }
			}
		});
        linearset = (LinearLayout) findViewById(R.id.ly_doorbell_set);
		linearset.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Vibrator vibrator = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);// 这段代码是实现震动效果
				vibrator.vibrate(new long[] { 0, 200 }, -1);
				Intent intent = new Intent(indexcontent.this,SettingsActivity.class);
	            startActivity(intent);

			}
		});
		     
		myHandler = new MyHandler();
		MyThread myThread = new MyThread();
		myThread.start();
	}
	
	public void CreatDialog(){
		
	     builder.setIcon(R.drawable.set);
            builder.setTitle("doorbell铃声选择");
            M_arg.mSingleChoiceID = sp.getInt("mSingleChoiceID", 0);
            builder.setSingleChoiceItems(mItems, M_arg.mSingleChoiceID, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                	M_arg.mSingleChoiceID = whichButton;
                	Editor editor = sp.edit();
     				editor.putInt("mSingleChoiceID", M_arg.mSingleChoiceID);
     				editor.commit();
                    //showDialog("你选择的id为" + whichButton + " , " + mItems[whichButton]);
                }
            });
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    if(M_arg.mSingleChoiceID > 0) {
               	    //showDialog("你选择的是" + mSingleChoiceID);
                    	linearhz
        				.setBackgroundResource(R.drawable.button_53);
                    }
                }
            });
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                	linearhz
    				.setBackgroundResource(R.drawable.button_53);
                }
            });
          builder.create().show();//显示
	}
	
	
	private void showDialog(String str) {
		 new AlertDialog.Builder(indexcontent.this)
	         .setMessage(str)
	         .show();
	    }
	
	public void initmPopupWindowView() {

		// // ��ȡ�Զ��岼���ļ�pop.xml����ͼ
		View customView = getLayoutInflater().inflate(R.layout.popview_item,
				null, false);
		// ����PopupWindowʵ��,200,150�ֱ��ǿ�Ⱥ͸߶�
		popupwindow = new PopupWindow(customView, 180, 280);
		// ���ö���Ч�� [R.style.AnimationFade ���Լ����ȶ���õ�]
		popupwindow.setAnimationStyle(R.style.AnimationFade);
		// �Զ���view��Ӵ����¼�
		customView.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (popupwindow != null && popupwindow.isShowing()) {
					popupwindow.dismiss();
					popupwindow = null;
				}

				return false;
			}
		});

	
		Button btton2 = (Button) customView.findViewById(R.id.button2);
		Button btton3 = (Button) customView.findViewById(R.id.button3);
		Button btton4 = (Button) customView.findViewById(R.id.button4);
		btton2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(indexcontent.this,AlbumActivity.class);
	            startActivity(intent);
			}

		});
		btton3.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent3 = new Intent(indexcontent.this,SqliteActivity.class);
	            startActivity(intent3);
			}
		});
		btton4.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent4 = new Intent(indexcontent.this,UserActivity.class);
	            startActivity(intent4);
			}
		});

	}
	
	public boolean onTouchEvent(MotionEvent event) {

		return gestureDetector.onTouchEvent(event); // 注册手势事件
	}

	public boolean onDown(MotionEvent e) {

		return false;
	}

	public void onShowPress(MotionEvent e) {

	}

	public boolean onSingleTapUp(MotionEvent e) {// 用户（轻触触摸屏后）松开，由一个1个MotionEvent
													// ACTION_UP触发

		return false;
	}

	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {

		return false;
	}

	public void onLongPress(MotionEvent e) {

	}

	// 以下这个函数的作用是实现左右上下移动画面
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		// handler.removeCallbacks(runnable);//当滑动图片时，停止自动播放
		if (e2.getX() - e1.getX() > 120) { // 从左向右滑动（左进右出）

			Log.e("msg", "由左向右");
			BasicHttpParams httpParams;
			httpParams = new BasicHttpParams();
			HttpConnectionParams.setConnectionTimeout(httpParams, 1000);
			HttpConnectionParams.setSoTimeout(httpParams, 1000);
			DefaultHttpClient httpClient = new DefaultHttpClient();
			// HttpGet get = new
			// HttpGet("http://192.168.1.104:82/decoder_control.cgi?command=6&onestep=0&user=admin&pwd=");//左
			String stripport1 = "http://" + M_arg.ip + ":"
					+ M_arg.port
					+ "/decoder_control.cgi?command=4&onestep=0&user="
					+ M_arg.username + "&pwd="
					+ M_arg.password;
			String stripport2 = "http://" + M_arg.ip + ":"
					+ M_arg.port
					+ "/decoder_control.cgi?command=3&onestep=0&user="
					+ M_arg.username + "&pwd="
					+ M_arg.password;
			if(M_arg.series.equals(seriesflag))
			{
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
			return true;
			}
			else
			{
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
				return true;
			}
		} else if (e2.getX() - e1.getX() < -120) { // 从右向左滑动（右进左出）
			Log.e("msg", "由右向左");
			BasicHttpParams httpParams;
			httpParams = new BasicHttpParams();
			HttpConnectionParams.setConnectionTimeout(httpParams, 1000);
			HttpConnectionParams.setSoTimeout(httpParams, 1000);
			DefaultHttpClient httpClient = new DefaultHttpClient();
			// HttpGet get = new
			// HttpGet("http://192.168.1.104:82/decoder_control.cgi?command=4&onestep=0&user=admin&pwd=");//右
			String stripport = "http://" + M_arg.ip + ":"
					+ M_arg.port
					+ "/decoder_control.cgi?command=6&onestep=0&user="
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
			return true;
		} else if (e2.getY() - e1.getY() < -120) { //
			Log.e("msg", "由下向上");
			BasicHttpParams httpParams;
			httpParams = new BasicHttpParams();
			HttpConnectionParams.setConnectionTimeout(httpParams, 1000);
			HttpConnectionParams.setSoTimeout(httpParams, 1000);
			DefaultHttpClient httpClient = new DefaultHttpClient();
			String stripport = "http://" + M_arg.ip + ":"
					+ M_arg.port
					+ "/decoder_control.cgi?command=2&onestep=0&user="
					+ M_arg.username + "&pwd="
					+ M_arg.password;
			HttpGet get = new HttpGet(stripport);// 龙视安的 上
			try {
				httpClient.execute(get);
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;
		} else if (e2.getY() - e1.getY() > 120) { // 从右向左滑动（右进左出）
			Log.e("msg", "由上向下");
			BasicHttpParams httpParams;
			httpParams = new BasicHttpParams();
			HttpConnectionParams.setConnectionTimeout(httpParams, 1000);
			HttpConnectionParams.setSoTimeout(httpParams, 1000);
			DefaultHttpClient httpClient = new DefaultHttpClient();
			String stripport1 = "http://" + M_arg.ip + ":"
					+ M_arg.port
					+ "/decoder_control.cgi?command=0&onestep=0&user="
					+ M_arg.username + "&pwd="
					+ M_arg.password;
			String stripport2 = "http://" + M_arg.ip + ":"
					+ M_arg.port
					+ "/decoder_control.cgi?command=1&onestep=0&user="
					+ M_arg.username + "&pwd="
					+ M_arg.password;
			if(M_arg.series.equals(seriesflag))
			{
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
			return true;
			}
			else
			{
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
				return true;
			}
		}
		return true;
	}

	// 播放cameraStream（）函数，这个函数禁止在UI线程中直接调用
	private void cameraStream() throws IOException {
		/*InputStream inputStream = null; 
		//BufferedInputStream bis = null; 
		ByteArrayOutputStream bos = null;
		String stripport = "http://" + M_arg.ip + ":"
				+ M_arg.port + "/snapshot.cgi?user="
				+ M_arg.username + "&pwd="
				+ M_arg.password ;
        URL url = new URL(stripport);  
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();  
        conn.setRequestMethod("GET");   //设置请求方法为GET  
        conn.setReadTimeout(5*1000);    //设置请求过时时间为5秒  
        inputStream = conn.getInputStream();   //通过输入流获得图片数据  
        mStopStream = false;
        byte[] buffer = new byte[1024];  
        int len = 0;  
         bos = new ByteArrayOutputStream();  
        while((len = inputStream.read(buffer)) != -1) {  
            bos.write(buffer, 0, len);  
        }  
        
        //byte[] data = StreamTool.readInputStream(inputStream);     //获得图片的二进制数据  
        byte[] data = bos.toByteArray();
        bitmap = BitmapFactory
				.decodeByteArray(data, 0, data.length);
        if (bitmap != null) {
			myHandler.sendEmptyMessage(MSG_REFRESH);
        //return data;  
          
    } 
        bos.reset();
        bos.close(); 
     // 下面再加一段显示通话时长的
		if (null == timer) {
			if (null == task) {
				task = new TimerTask() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						if (null == msgtimer) {
							msgtimer = new Message();
						} else {
							msgtimer = Message.obtain();
						}
						msgtimer.what = 1;
						myHandler.sendMessage(msgtimer);
					}
				};
			}
			timer = new Timer(true);
			timer.schedule(task, mlTimerUnit, mlTimerUnit);
		}*/

		URL aURL;
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();// 捕获缓冲区数据转换成字节数组
		String stripport = "http://" + M_arg.ip + ":"
				+ M_arg.port + "/videostream.cgi?user="
				+ M_arg.username + "&pwd="
				+ M_arg.password + "&resolution=32";
		// aURL = new
		// URL("http://192.168.1.104:82/videostream.cgi?user=admin&pwd=&resolution=32");//龙视安的可以，已经成功
		aURL = new URL(stripport);
		URLConnection conn = aURL.openConnection();// 打开连接
		conn.connect();
		InputStream input = conn.getInputStream();// 获得它的输入流
		// System.out.println("input = " + input);//input =
		// libcore.net.http.UnknownLengthHttpInputStream@42340ec0
		if (input != null)
			mStopStream = false;// 获取视频流后可以看到input内容，然后把mstopstream赋值为false
		int readLength = -1;
		String strDate;// 把读取的数据转化为string类型，以便判别包头
		while (!mStopStream) {
			byte[] buffer = new byte[1024];// 创建buffer缓冲区大小为1KB
											// 这个地方有个问题是Buffer是多少呢感觉没有把input存入到Buffer中
			readLength = input.read(buffer, 0, 1024);// readlength本次读取的数据的长度
			if (readLength > 0) {
				strDate = new String(buffer, readLength);// 将字节型转换成String型，将获得的图片信息用于传输
				// System.out.println("StrDate"+strDate);//这个地方返回的是IPcam264
				// index标记“content-lenghth: "的起始位置
				// index1标记“\r\n”的位置，注意是“content-lenght: ”之后的第一个位置
				int index = strDate.indexOf(flag);// 搜索这个字符串的第一个索引指定的字符串。搜索字符串开头开始,走向这个字符串的结束
				System.out.println("index" + index);
				int index1 = strDate.indexOf(flag1, index);// 函数是指字符flag1在index后出现的第一个位置如果没有找到则返回-1
				int streamLength = 0;
				if (index1 != -1) {
					// 计算本次streamlength的长度
					streamLength = Integer.parseInt(strDate.substring(index
							+ flag.length(), index1));// 这一行有问题。？？？、
					// substring(begingindex,endindex)返回一个新字符串，它是此字符串的一个子字符串。该子字符串从指定的
					// beginIndex 处开始，直到索引 endIndex - 1 处的字符。因此，该子字符串的长度为
					// endIndex-beginIndex。
					System.out.println("streamLength" + streamLength);// 这个地方返回的是IPcam264
				}
				if (streamLength > 0) {
					if ((index1 + 4) < readLength) {
						outStream.write(buffer, index1 + 4, readLength - index1
								- 4);
						streamLength = streamLength - readLength + index1 + 4;
					}
					// 将剩下的读取的视频流存储到Buffer1中
					byte[] buffer1 = new byte[streamLength];
					int length = 0;
					while (length < streamLength) {
						length += input.read(buffer1, length, streamLength
								- length);
					}
					outStream.write(buffer1, 0, streamLength); // 将剩余的stream写入
					byte[] data = outStream.toByteArray();
					bitmap = BitmapFactory
							.decodeByteArray(data, 0, data.length);
					if (bitmap != null) {
						myHandler.sendEmptyMessage(MSG_REFRESH); // 报告到UI界面，更新图。
																	// 用sendEmptyMessage（）向Handler发送消息
						// 下面再加一段显示通话时长的
						if (null == timer) {
							if (null == task) {
								task = new TimerTask() {

									@Override
									public void run() {
										// TODO Auto-generated method stub
										if (null == msgtimer) {
											msgtimer = new Message();
										} else {
											msgtimer = Message.obtain();
										}
										msgtimer.what = 1;
										myHandler.sendMessage(msgtimer);
									}
								};
							}
							timer = new Timer(true);
							timer.schedule(task, mlTimerUnit, mlTimerUnit);

						}
					} // 发送的消息是MSG_REFRESH
					outStream.reset();
				}
			}
		}
		outStream.close();
	}
	
	private void getImage() throws IOException { 
		InputStream inputStream = null; 
		//BufferedInputStream bis = null; 
		ByteArrayOutputStream bos = null;
		String stripport = "http://" + M_arg.ip + ":"
				+ M_arg.port + "/snapshot.cgi?user="
				+ M_arg.username + "&pwd="
				+ M_arg.password ;
        URL url = new URL(stripport);  
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();  
        conn.setRequestMethod("GET");   //设置请求方法为GET  
        conn.setReadTimeout(5*1000);    //设置请求过时时间为5秒  
        inputStream = conn.getInputStream();   //通过输入流获得图片数据  
        mStopStream = false;
        byte[] buffer = new byte[1024];  
        int len = 0;  
         bos = new ByteArrayOutputStream();  
        while((len = inputStream.read(buffer)) != -1) {  
            bos.write(buffer, 0, len);  
        }  
        
        //byte[] data = StreamTool.readInputStream(inputStream);     //获得图片的二进制数据  
        byte[] data = bos.toByteArray();
        bitmap = BitmapFactory
				.decodeByteArray(data, 0, data.length);
        if (bitmap != null) {
			mHandler.sendEmptyMessage(MSG_REFRESH);
        //return data;  
          
    } 
        bos.reset();
        bos.close(); 
     // 下面再加一段显示通话时长的
		if (null == timer) {
			if (null == task) {
				task = new TimerTask() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						if (null == msgtimer) {
							msgtimer = new Message();
						} else {
							msgtimer = Message.obtain();
						}
						msgtimer.what = 1;
						myHandler.sendMessage(msgtimer);
					}
				};
			}
			timer = new Timer(true);
			timer.schedule(task, mlTimerUnit, mlTimerUnit);
		}
	}

	public void saveBitmap() {
		filename = System.currentTimeMillis() + ".jpg";// 获取当前的系统时间
		File f = new File(Environment.getExternalStorageDirectory(), filename);// 在sd卡中创建文件
		FileOutputStream fOut = null;
		try {
			f.createNewFile();
			fOut = new FileOutputStream(f);
			bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
			Log.e("saveBitmap", "运行到savebitmap这一步");
			fOut.flush();
			fOut.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	class MyHandler extends Handler {
		public void handleMessage(Message msg) { // handler用handleMessage方法去接收消息，然后根据消息的不同执行不同的操作
			// TODO Auto-generated method stub
			switch (msg.what) { // 判断what值
			case MSG_REFRESH: // what等于MSG_REFRESH时
				mImageView.setImageBitmap(bitmap);// 设置ImageView所显示的内容为制定的Bitmap，就是获得了bitmap解码成功的
				break;
			case 1:
				mlCount++;// 计数
				int totalSec = 0;
				// 设定显示时间
				totalSec = (int) (mlCount);
				int min = (totalSec / 60);
				int sec = (totalSec % 60);
				try {
					tvTime.setText(String.format("%1$02d:%2$02d  ", min, sec));
				} catch (Exception e) {
					tvTime.setText("" + min + ":" + sec);
					e.printStackTrace();
					Log.e("MyTimer onCreate", "Format string error.");
				}
				break;
			default:
				break;
			}
			super.handleMessage(msg);
		}
	}

	private Handler mHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			switch (msg.what) {
			case MSG_REFRESH:
				mImageView.setImageBitmap(bitmap);
				//urlImgView.setImageResource(0);
				VideoTask videoTask = new VideoTask();
				videoTask.execute("video");
				break;

			default:
				break;
			}
			super.handleMessage(msg);
		}
		
	};
		
	class VideoTask extends AsyncTask<String, String, String> {

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			String type = params[0];
			if("video".equals(type)) {
				try {
					getImage();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return null;
		}
		
	}
	
	
	 /** 
     * 保存文件 
     * @param bm 
     * @param fileName 
     * @throws IOException 
     */  
    public void saveFile(Bitmap bm, String fileName) throws IOException {  
        File dirFile = new File(ALBUM_PATH);  
        if(!dirFile.exists()){  
            dirFile.mkdir();  
        }  
        File myCaptureFile = new File(ALBUM_PATH + fileName);  
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));  
        bm.compress(Bitmap.CompressFormat.JPEG, 80, bos);  
        bos.flush();  
        bos.close();  
    }  
  
    private Runnable saveFileRunnable = new Runnable(){  
        @Override  
        public void run() {  
            try {  
                saveFile(bitmap, mFileName);  
                mSaveMessage = "图片保存成功！";  
            } catch (IOException e) {  
                mSaveMessage = "图片保存失败！";  
                e.printStackTrace();  
            }  
            messageHandler.sendMessage(messageHandler.obtainMessage());  
        }  
  
    };  
    
       private Handler messageHandler = new Handler() {  
        @Override  
        public void handleMessage(Message msg) {  
            mSaveDialog.dismiss();  
            //Log.d(TAG, mSaveMessage);  
            Toast.makeText(indexcontent.this, mSaveMessage, Toast.LENGTH_SHORT).show();  
        }  
    };

	class MyThread extends Thread {
		public void run() {
			try {
				//if(M_arg.series.equals(seriesflag)){
					//Log.e("MyTimer", M_arg.series);
					//cameraStream();
					//getImage();
					//Log.e("MyTimer", M_arg.series);
				//}
				//else{
				   getImage();
				//}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			/*
			 * Intent intent=new Intent();
			 * intent.setClass(indexcontent.this,MainActivity.class);
			 * startActivity(intent);
			 */
			finish();
		}
		return true;
	}

}
