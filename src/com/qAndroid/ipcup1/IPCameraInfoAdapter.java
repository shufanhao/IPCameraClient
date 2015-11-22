package com.qAndroid.ipcup1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class IPCameraInfoAdapter {
	public static final String KEY_ID = "_id";
	public static final String KEY_STUDENT_IP = "student_ip";
	public static final String KEY_STUDENT_PORT = "student_port";
	public static final String KEY_STUDENT_USERNAME = "student_username";
	public static final String KEY_STUDENT_PASSWORD = "student_password";
	public static final String KEY_STUDENT_SERIES = "student_series";
	
	private static final String STUDENT_INFO_DATABASE = "StudentInfoDatabase";
	private static final int VERSION = 1;
	private static final String STUDENT_TABLE = "StudentInfoTbl";
	
	private StudentInfoDatabaseHelper mHelper;
	private SQLiteDatabase dbStudentInfo;
	
	private final Context mCtnx;
	
	public IPCameraInfoAdapter(Context ctnx) {
		this.mCtnx = ctnx;
		System.out.println("mctx=ctx");
	}
	// 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷菘锟�
	public IPCameraInfoAdapter open() {
		mHelper = new StudentInfoDatabaseHelper(mCtnx);
		dbStudentInfo = mHelper.getWritableDatabase();
		//System.out.println("锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷菘锟�);
		return this;
		
	}
	// 锟斤拷锟斤拷菘锟斤拷锟斤拷一位学锟斤拷锟斤拷息锟斤拷录
	public long addStuInfo(String ip, int port, String username, String password, String series) {
		ContentValues val = new ContentValues();
		val.put(KEY_STUDENT_IP, ip);
		val.put(KEY_STUDENT_PORT, port);
		val.put(KEY_STUDENT_USERNAME, username);
		val.put(KEY_STUDENT_PASSWORD, password);
		val.put(KEY_STUDENT_SERIES, series);
		
		return dbStudentInfo.insert(STUDENT_TABLE, null, val);
	}
	// 锟斤拷锟斤拷锟斤拷菘锟斤拷卸锟接ρэ拷锟斤拷锟斤拷息锟斤拷录
	public boolean updateStuInfo(Long mId, String ip, int port, String username, String password, String series) {
		ContentValues val = new ContentValues();
		val.put(KEY_STUDENT_IP, ip);
		val.put(KEY_STUDENT_PORT, port);
		val.put(KEY_STUDENT_USERNAME, username);
		val.put(KEY_STUDENT_PASSWORD,password);
		val.put(KEY_STUDENT_SERIES, series);
		
		return dbStudentInfo.update(STUDENT_TABLE, val, KEY_ID + "=" + mId, null) > 0;
	}
	// 删锟斤拷一锟斤拷学锟斤拷锟斤拷息锟斤拷录
	public boolean deleteStuInfo(long mId) {
		return dbStudentInfo.delete(STUDENT_TABLE, KEY_ID + "=" + mId, null) > 0;
	}
	// 锟斤拷取锟斤拷菘锟斤拷锟斤拷锟斤拷锟窖э拷锟斤拷锟较拷锟铰�
	public Cursor getAllStudentInfo() {
		System.out.println("streamLength99");
		return dbStudentInfo.query("StudentInfoTbl", new String[] {KEY_ID, 
				KEY_STUDENT_IP, KEY_STUDENT_PORT, KEY_STUDENT_USERNAME , KEY_STUDENT_PASSWORD , KEY_STUDENT_SERIES}, 
				null, null, null, null, null);
	}
	// 锟斤拷锟斤拷SQLiteOpenHelper锟斤拷锟斤拷锟斤拷StudentInfoDatabaseHelper
	private static class StudentInfoDatabaseHelper extends SQLiteOpenHelper {
		public StudentInfoDatabaseHelper(Context context) {
			super(context, STUDENT_INFO_DATABASE, null, VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			String sql = " create table StudentInfoTbl (_id integer primary key autoincrement, "+
		                 "student_ip, student_port integer, student_username, student_password, student_series);";
			db.execSQL(sql);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {	
					
		}		
	}
}