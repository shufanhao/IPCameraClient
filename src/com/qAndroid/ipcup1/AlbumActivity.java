package com.qAndroid.ipcup1;

import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;
import android.widget.ViewSwitcher.ViewFactory;

import java.util.ArrayList;
import java.util.List;
import java.io.File;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.view.View.OnClickListener;
import android.widget.AdapterView.OnItemClickListener;

public class AlbumActivity extends Activity 
                       implements OnItemSelectedListener,ViewFactory{
	
	    private List<String> imagePathList;
	    private String[] list;
	    private ImageSwitcher mSwitcher;
	    private Gallery mGallery;
	    private Dialog listDialog = null;
	    public String photoURL;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.photo);
        
        imagePathList=getImagePathFromSD();
        list = imagePathList.toArray(new String[imagePathList.size()]);
        
        ImageButton imagebutton = (ImageButton) findViewById(R.id.back_btn);
		imagebutton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();	//返回
			}
		});
        // ��ȡImageSwitcherʵ��
        mSwitcher = (ImageSwitcher) findViewById(R.id.switcher);
        mSwitcher.setFactory(this);
        // ����ͼƬ���뻻���Ķ���Ч��
        mSwitcher.setInAnimation(AnimationUtils.loadAnimation(this, 
        		android.R.anim.fade_in));
        mSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this, 
        		android.R.anim.fade_out));
        mSwitcher.setOnClickListener(new OnClickListener() {
        	 
            public void onClick(View v) {
               // Toast.makeText(AlbumActivity.this, "你点击了ImageSwitch上的图片",
                       // Toast.LENGTH_SHORT).show();
            	final CharSequence[] items = {"分享给朋友", "分享到朋友圈","删除"};
            	
				AlertDialog.Builder listbuilder = new AlertDialog.Builder(
						AlbumActivity.this);
				listbuilder.setTitle("更多");
				listbuilder.setItems(items, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						Toast.makeText(AlbumActivity.this, items[which], 
								Toast.LENGTH_LONG).show();
						
						//String imagePath = Environment.getExternalStorageDirectory().toString()+"/download_test/";
						File dir = Environment
		        				.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
						String filename=photoURL.substring(photoURL.lastIndexOf('/')+1,photoURL.length());
		        		File file = new File(dir,filename);
		        		
						if(which==0)
						{
							shareToFriend(file);
							 
						}
						else if(which==1)
						{
							shareToTimeLine(file);
						}
						else
						{
							file.delete();
						}
					}					
				});
				listDialog = listbuilder.create();
				listDialog.show();
            }
 
        });
        // ��ȡGallery����
        mGallery = (Gallery) findViewById(R.id.mygallery);
        // ����������
        mGallery.setAdapter(new ImageAdapter(this, getImagePathFromSD()));
        // �����¼�����
        mGallery.setOnItemSelectedListener(this);
        
        /* 设定一个itemclickListener事件 */
        mGallery.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                    int position, long id) {
               // Toast.makeText(AlbumActivity.this, "你点击了Gallery上的图片",
                        //Toast.LENGTH_SHORT).show();
            }
        });

			
    }
    
   /* @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.album, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		File dir = Environment
				.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
		
		File file = new File(dir, "1.png");
		
		switch (item.getItemId()) {
		case R.id.action_share1:
			
			
			shareToFriend(file);
			return true;
		case R.id.action_share2:
			
			
			shareToTimeLine(file);
			return true;
		default:
			return super.onOptionsItemSelected(item);

		}
	}*/

	private void shareToFriend(File file) {
		
		Intent intent = new Intent();
		ComponentName comp = new ComponentName("com.tencent.mm",
				"com.tencent.mm.ui.tools.ShareImgUI");
		intent.setComponent(comp);
		intent.setAction("android.intent.action.SEND");
		intent.setType("image/*");
		//intent.setFlags(0x3000001);
		intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
		startActivity(intent);
	}
	private void shareToTimeLine(File file) {
		Intent intent = new Intent();
		ComponentName comp = new ComponentName("com.tencent.mm",
				"com.tencent.mm.ui.tools.ShareToTimeLineUI");
		intent.setComponent(comp);
		intent.setAction("android.intent.action.SEND");
		intent.setType("image/*");
		//intent.setFlags(0x3000001);
		intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
		startActivity(intent);
	}

	    /** 从SD卡中获取资源图片的路径 */
    private List<String> getImagePathFromSD() {
        /* 设定目前所在路径 */
        List<String> it = new ArrayList<String>();
         
        //根据自己的需求读取SDCard中的资源图片的路径
        String imagePath = Environment.getExternalStorageDirectory().toString()+"/Pictures/";
         
        File mFile = new File(imagePath);
        File[] files = mFile.listFiles();
 
        /* 将所有文件存入ArrayList中 */
        for (int i = 0; i < files.length; i++) {
            File file = files[i];
            if (checkIsImageFile(file.getPath()))
                it.add(file.getPath());
        }
        return it;
    }
 
    /** 判断是否相应的图片格式  */
    private boolean checkIsImageFile(String fName) {
        boolean isImageFormat;
 
        /* 取得扩展名 */
        String end = fName
                .substring(fName.lastIndexOf(".") + 1, fName.length())
                .toLowerCase();
 
        /* 按扩展名的类型决定MimeType */
        if (end.equals("jpg") || end.equals("gif") || end.equals("png")
                || end.equals("jpeg") || end.equals("bmp")) {
            isImageFormat = true;
        } else {
            isImageFormat = false;
        }
        return isImageFormat;
    }
 
    /* 改写BaseAdapter自定义一ImageAdapter class */
    public class ImageAdapter extends BaseAdapter {
        /* 声明变量 */
        int mGalleryItemBackground;
        private Context mContext;
        private List<String> lis;
 
        /* ImageAdapter的构造符 */
        public ImageAdapter(Context c, List<String> li) {
            mContext = c;
            lis = li;
            /*
             * 使用res/values/attrs.xml中的<declare-styleable>定义 的Gallery属性.
             */
            TypedArray mTypeArray = obtainStyledAttributes(R.styleable.Gallery);
            /* 取得Gallery属性的Index id */
            mGalleryItemBackground = mTypeArray.getResourceId(
                    R.styleable.Gallery_android_galleryItemBackground, 0);
            /* 让对象的styleable属性能够反复使用 */
            mTypeArray.recycle();
        }
 
        /* 重写的方法getCount,传回图片数目 */
        public int getCount() {
            return lis.size();
        }
 
        /* 重写的方法getItem,传回position */
        public Object getItem(int position) {
            return position;
        }
 
        /* 重写的方法getItemId,传并position */
        public long getItemId(int position) {
            return position;
        }
 
        /* 重写方法getView,传并几View对象 */
        public View getView(int position, View convertView, ViewGroup parent) {
            /* 产生ImageView对象 */
            ImageView i = new ImageView(mContext);
            /* 设定图片给imageView对象 */
            Bitmap bm = BitmapFactory.decodeFile(lis.get(position).toString());
            i.setImageBitmap(bm);
            /* 重新设定图片的宽高 */
            i.setScaleType(ImageView.ScaleType.FIT_XY);
            /* 重新设定Layout的宽高 */
            i.setLayoutParams(new Gallery.LayoutParams(200, 150));
            /* 设定Gallery背景图 */
            i.setBackgroundResource(mGalleryItemBackground);
            /* 传回imageView对象 */
            return i;
        }
    }
 
    @Override
    public View makeView() {
        ImageView iv = new ImageView(this);
        iv.setBackgroundColor(0xFF000000);
        iv.setScaleType(ImageView.ScaleType.FIT_CENTER);
        iv.setLayoutParams(new ImageSwitcher.LayoutParams(
            LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT));
        return iv;
    }
 
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position,
            long id) {
        // TODO Auto-generated method stub
        photoURL = list[position];
        //String end = fName;
        String filename=photoURL.substring(photoURL.lastIndexOf('/')+1,photoURL.length());
        Log.i("A", filename);
         
        mSwitcher.setImageURI(Uri.parse(photoURL));
    }
 
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // TODO Auto-generated method stub
         
    }
    
   
          
      
}
