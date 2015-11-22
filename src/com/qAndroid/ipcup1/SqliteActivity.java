package com.qAndroid.ipcup1;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.AdapterView.AdapterContextMenuInfo;

public class SqliteActivity extends ListActivity {
	private static final int INSERT_ID = Menu.FIRST;
	private static final int DELETE_ID = Menu.FIRST + 1;
	
	private static final int CODE_ADD = 0;
	private static final int CODE_EDIT = 1;
	
	private IPCameraInfoAdapter mStuInfoAdapter;
	private Cursor mStuInfoCursor;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.sqlite);
        
        ImageButton imagebutton = (ImageButton) findViewById(R.id.back_btn);
		imagebutton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();	//返回
			}
		});
		
        mStuInfoAdapter = new IPCameraInfoAdapter(this);
        mStuInfoAdapter.open();
        displayStudentList(); 
        registerForContextMenu(getListView());
    }
    // ��ʾѧ����Ϣ��ListView
    @SuppressWarnings("deprecation")
	private void displayStudentList() {
    	System.out.println("display1");
    	mStuInfoCursor = mStuInfoAdapter.getAllStudentInfo();
    	System.out.println("display2");
    	startManagingCursor(mStuInfoCursor);
    	System.out.println("display3");
    	String[] from = new String[] {mStuInfoAdapter.KEY_STUDENT_IP,
    			mStuInfoAdapter.KEY_STUDENT_PORT, mStuInfoAdapter.KEY_STUDENT_USERNAME, mStuInfoAdapter.KEY_STUDENT_PASSWORD, mStuInfoAdapter.KEY_STUDENT_SERIES};
    	int[] to = new int[] {R.id.ip, R.id.port, R.id.username,R.id.password,R.id.series};
    	SimpleCursorAdapter stuInfo = new SimpleCursorAdapter(this, R.layout.ipcamera,
    			mStuInfoCursor, from, to);
    	setListAdapter(stuInfo);
    }
    // ����ѡ��˵�
    public boolean onCreateOptionsMenu(Menu menu) {
    	super.onCreateOptionsMenu(menu);
    	menu.add(0, INSERT_ID, 0, "添加一条ipcamera信息");
    	//menu.add(0, DELETE_ID, 0, "删除一条ipcamera信息");
    	return true;
    }
	// ѡ��˵��Ĳ˵�����
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch(item.getItemId()) {
		case INSERT_ID:
			System.out.println("insert");
			addStudentInfo();
			System.out.println("insert");
			return true;
		case DELETE_ID:
			mStuInfoAdapter.deleteStuInfo(getListView().getSelectedItemId());
			displayStudentList();
			return true;
		}
		return super.onMenuItemSelected(featureId, item);
	}
    
	// ���List���༭�������ѧ����Ϣ
    @Override
	protected void onListItemClick(ListView l, View v, int position, long id) {		
		super.onListItemClick(l, v, position, id);
		editStudentInfo(position, id);
	}

    // �����Ĳ˵��Ķ���
    @Override
	public boolean onContextItemSelected(MenuItem item) {
    	AdapterContextMenuInfo menuInfo = (AdapterContextMenuInfo)item.getMenuInfo();
    	int position = menuInfo.position;
    	long id = menuInfo.id;
    	switch(item.getItemId()) {
    	case Menu.FIRST + 2:
    		editStudentInfo(position, id);
    		return true;
    	case Menu.FIRST + 3:
			mStuInfoAdapter.deleteStuInfo(id);
			displayStudentList();    		
    		return true;
    	}
    	return super.onContextItemSelected(item);
	}
	// ���������Ĳ˵�
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		menu.setHeaderTitle("摄像头账户操作");
		menu.add(0, Menu.FIRST + 2, 0, "编辑");
		menu.add(0, Menu.FIRST + 3, 0, "删除");
		super.onCreateContextMenu(menu, v, menuInfo);
	}
	// ����һ��Activity����
	protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
    	super.onActivityResult(requestCode, resultCode, intent);
    	displayStudentList();
    }
  // ���һ��ѧ����Ϣ��¼  
	private void addStudentInfo() {
    	Intent intent = new Intent(this, IPCameraInfoEdit.class);
    	startActivityForResult(intent, CODE_ADD);
    }
	// �༭ѧ����Ϣ
	private void editStudentInfo(int position, long id) {
		Cursor c = mStuInfoCursor;
		c.moveToPosition(position);
		Intent i = new Intent(this, IPCameraInfoEdit.class);
		i.putExtra(IPCameraInfoAdapter.KEY_ID, id);
		i.putExtra(IPCameraInfoAdapter.KEY_STUDENT_IP, 
				c.getString(c.getColumnIndexOrThrow(IPCameraInfoAdapter.KEY_STUDENT_IP)));
		i.putExtra(IPCameraInfoAdapter.KEY_STUDENT_PORT, 
				c.getInt(c.getColumnIndexOrThrow(IPCameraInfoAdapter.KEY_STUDENT_PORT)));
		i.putExtra(IPCameraInfoAdapter.KEY_STUDENT_USERNAME, 
				c.getString(c.getColumnIndexOrThrow(IPCameraInfoAdapter.KEY_STUDENT_USERNAME)));
		i.putExtra(IPCameraInfoAdapter.KEY_STUDENT_PASSWORD, 
				c.getString(c.getColumnIndexOrThrow(IPCameraInfoAdapter.KEY_STUDENT_PASSWORD)));
		i.putExtra(IPCameraInfoAdapter.KEY_STUDENT_SERIES, 
				c.getString(c.getColumnIndexOrThrow(IPCameraInfoAdapter.KEY_STUDENT_SERIES)));
		startActivityForResult(i, CODE_EDIT);
	}
}
