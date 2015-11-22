package com.qAndroid.ipcup1;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Message;

public class MyReceiver extends BroadcastReceiver{

	
	@Override
	public void onReceive(final Context context, Intent intent) {
		//从intent中获得信息
		String msg = intent.getStringExtra("msg");
		//使用toast显示
		if(msg.equals("开门")){
			//Toast.makeText(context, msg, Toast.LENGTH_SHORT).show(); toast一个开门
			//在一个普通类中启动activity http://bbs.csdn.net/topics/370005057
			//在receive里面启动一个activity会存在的问题是 onReceive函数只会存在10s钟左右就会销毁
			//解决方法是：广播里面向handler发送一个消息，在handler里面去打开一个界面。
			/*Intent intentcall = new Intent(context, ListenCall.class);
			intentcall.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        	context.startActivity(intentcall);	*/
			 Message message = new Message();  
             message.what = 1;
             message.obj = context;
            MainActivity.handlerstart.sendMessage(message);
          
		}
		}	
	}	
