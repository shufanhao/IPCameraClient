package com.qAndroid.ipcup1;


import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageButton;
import android.widget.ImageView;

public class ListenCall extends Activity implements OnGestureListener
{
	private ImageButton accept;
	private ImageButton reject;
	private ImageView acceptgesture;
	private ImageView rejectgeture;
	private AnimationDrawable animationDrawable1;
	private AnimationDrawable animationDrawable2;
	private GestureDetector gestureDetector;// 声明gestureDetector对象
	MediaPlayer mediaPlayer;
	private AnimationDrawable anim;
	private static final String TAG = "ListenCall";
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		this.requestWindowFeature(Window.FEATURE_NO_TITLE); // 去除标题功能
		super.onCreate(savedInstanceState);
		setContentView(R.layout.linsten);
		final ImageView iv=(ImageView)findViewById(R.id.imageView1);	//��ȡҪӦ�ö���Ч���ImageView
		final Animation translateright=AnimationUtils.loadAnimation(this, R.anim.translateright);	//��ȡ�����ұ��ܡ��Ķ�����Դ
		final Animation translateleft=AnimationUtils.loadAnimation(this, R.anim.translateleft);	//��ȡ�������ܡ��Ķ�����Դ
		anim=(AnimationDrawable)iv.getBackground();//��ȡӦ�õ�֡����
		anim.start();	//��ʼ����֡����
		iv.startAnimation(translateright);	//���š����ұ��ܡ��Ķ���
				
		translateright.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {}
			
			@Override
			public void onAnimationRepeat(Animation animation) {}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				iv.setBackgroundResource(R.anim.motionleft);	//��������ImageViewӦ�õ�֡����
				iv.startAnimation(translateleft);		//���š������ܡ��Ķ���	
				anim=(AnimationDrawable)iv.getBackground();//��ȡӦ�õ�֡����
				anim.start();	//��ʼ����֡����
			}
		});
		translateleft.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {}
			
			@Override
			public void onAnimationRepeat(Animation animation) {}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				iv.setBackgroundResource(R.anim.motionright);	//��������ImageViewӦ�õ�֡����
				iv.startAnimation(translateright);	//���š����ұ��ܡ��Ķ���		
				anim=(AnimationDrawable)iv.getBackground();//��ȡӦ�õ�֡����
				anim.start();	//��ʼ����֡����
			}
		});

		gestureDetector = new GestureDetector(this);
	     M_arg.mSingleChoiceID = indexcontent.sp.getInt("mSingleChoiceID", 0);
	     Log.e(TAG, "M_arg.mSingleChoiceID");
		if(M_arg.mSingleChoiceID >= 0){
			switch (M_arg.mSingleChoiceID) {
			case 0:
			break;
			case 1:				
				mediaPlayer=MediaPlayer.create(ListenCall.this, R.raw.apple);
				mediaPlayer.start(); //启动或者恢复播放
				mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {//
			        public void onCompletion(MediaPlayer arg0) {  
			        mediaPlayer.release(); 
			      }
				});  
			break;
			case 2:
				mediaPlayer=MediaPlayer.create(ListenCall.this, R.raw.doorbell_shix_sound);
				mediaPlayer.start(); //启动或者恢复播放
				mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {//
			        public void onCompletion(MediaPlayer arg0) {  
			        mediaPlayer.release(); 
			      }
				});  
			break;
			case 3:
				mediaPlayer=MediaPlayer.create(ListenCall.this, R.raw.houlai);
				mediaPlayer.start(); //启动或者恢复播放
				mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {//
			        public void onCompletion(MediaPlayer arg0) {  
			        mediaPlayer.release(); 
			      }
				});  
			break;
			case 4:
				mediaPlayer=MediaPlayer.create(ListenCall.this, R.raw.onlympic);
				mediaPlayer.start(); //启动或者恢复播放
				mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {//
			        public void onCompletion(MediaPlayer arg0) {  
			        mediaPlayer.release(); 
			      }
				});  
			break;
			default:
				break;
			}
		}	
		accept = (ImageButton)findViewById(R.id.btn_doorbell_ok);
		reject = (ImageButton)findViewById(R.id.btn_doorbell_no);
		acceptgesture=(ImageView)findViewById(R.id.getup_arrow);
		rejectgeture =(ImageView)findViewById(R.id.getup_arrow1);
		acceptgesture.setImageResource(R.anim.doorbell_btn_anim);
		//实现 动画显示
		animationDrawable1 = (AnimationDrawable)acceptgesture.getDrawable();  
		animationDrawable1.start();
		rejectgeture.setImageResource(R.anim.doorbell_btn_no_anim);  
		animationDrawable2 = (AnimationDrawable)rejectgeture.getDrawable();  
		animationDrawable2.start();  
	  
		accept.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				ListenCall.this.gestureDetector.onTouchEvent(event);
				return true;
			}
		});
		reject.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				ListenCall.this.gestureDetector.onTouchEvent(event);
				return true;
			}
		});
	}
	@Override
	public boolean onDown(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		// TODO Auto-generated method stub
		if (e2.getX() - e1.getX() > 60) { // 从左向右滑动（左进右出）
			//启动acitvity
			Intent intent = new Intent(ListenCall.this,indexcontent.class);
	         ListenCall.this.startActivity(intent);	 
	         finish();
			Log.e("msg", "执行滑动1");
			return true;
		} else if (e2.getX() - e1.getX() < -60  ) { // 从右向左滑动（右进左出）
			Log.e("msg", "执行滑动2");
			Intent intent = new Intent();
			intent.setAction(Intent.ACTION_MAIN);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);// 注意，这个地方最重要，关于解释，自己google吧     
			intent.addCategory(Intent.CATEGORY_HOME);           
			startActivity(intent);
			finish();
			return true;
			
		}
		return true;
	}
	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}
	//重写onTounchEvent 实现的是对整个屏幕的
	/*public boolean onTouchEvent(MotionEvent event) {

		return gestureDetector.onTouchEvent(event); // 注册手势事件
	}
*/
	public void onLongPress(MotionEvent e) {

	}
	
}
