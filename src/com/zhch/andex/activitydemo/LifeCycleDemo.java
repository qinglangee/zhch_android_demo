package com.zhch.andex.activitydemo;

import android.app.ActionBar;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.hardware.Camera;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;

import com.zhch.andex.R;

/**
 * 生命周期图示 http://developer.android.com/training/basics/activity-lifecycle/starting.html
 * 
 * @author lifeix
 *
 */
public class LifeCycleDemo extends Activity {

	Button btn1;
	Camera mCamera;

	static final String STATE_SCORE = "playerScore";
	static final String STATE_LEVEL = "playerLevel";
	private int mCurrentScore;
	private int mCurrentLevel;

	/**
	 * onCreate() 方法中是在Activity整个生命周期中只执行一次的代码, 在Activity启动时执行. <br>
	 * 如定义用户界面, 设置类全局变量等
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); // Always call the superclass first
		setContentView(R.layout.activity_layout_lifecycledemo);

		// Initialize member TextView so we can manipulate it later
		btn1 = (Button) findViewById(R.id.button1);

		// Make sure we're running on Honeycomb or higher to use ActionBar APIs
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			// For the main activity, make sure the app icon in the action bar
			// does not behave as a button
			ActionBar actionBar = getActionBar();
			actionBar.setHomeButtonEnabled(false);
		}

		// 状态恢复
		// 这里的Bundle可能为空, 新建的activity没有保存的Bundle
		if (savedInstanceState != null) {
			// Restore value of members from saved state
			mCurrentScore = savedInstanceState.getInt(STATE_SCORE);
			mCurrentLevel = savedInstanceState.getInt(STATE_LEVEL);
		} else {
			// Probably initialize members with default values for a new instance
		}

	}

	/**
	 * onPause()是activity显示部分, 但不是最前时调用, 主要做:<br>
	 * 1. 停止动画或其它正进行耗费cpu的动作 <br>
	 * 2. 提交还未保存的修改, 如草稿之类的(用户确实需要自动保存的) <br>
	 * 3. 释放系统资源, 如 roadcast receivers, handles to sensors(like GPS), 任何耗电的都释放掉<br>
	 * 耗时操作不应该在onPause()中进行, 如保存数据库等, 可放在onStop()中时行
	 */
	@Override
	public void onPause() {
		super.onPause(); // Always call the superclass method first

		// Release the Camera because we don't need it when paused
		// and other activities might need to use it.
		if (mCamera != null) {
			mCamera.release();
			mCamera = null;
		}
	}

	/**
	 * 每次activity进入前台时都会调用 onResume(), 包括创建时<br>
	 * 在 onResume()中应该初始化 在 onPause()中释放了的资源, 并且创建任何 resumed 状态必需的初始化<br>
	 * 例如 动画, 获得用户焦点时需要的组件等
	 */
	@Override
	public void onResume() {
		super.onResume(); // Always call the superclass method first

		// Get the Camera instance as the activity achieves full user focus
		if (mCamera == null) {
			initializeCamera(); // Local method to handle camera init
		}
	}

	private void initializeCamera() {
	}

	/**
	 * onStop() 用来做 CPU 耗时的清理工作<br>
	 * http://developer.android.com/training/basics/activity-lifecycle/stopping. html
	 */
	@Override
	protected void onStop() {
		super.onStop(); // Always call the superclass method first

		// Save the note's current draft, because the activity is stopping
		// and we want to be sure the current note progress isn't lost.
		ContentValues values = new ContentValues();
		values.put("key", "value");
		values.put("key2", "value2");

		Uri mUri = null;
		if(getContentResolver() != null && mUri != null){
			
			getContentResolver().update(mUri, // The URI for the note to update.
					values, // The map of column names and new values to apply to
					// them.
					null, // No SELECT criteria are used.
					null // No WHERE columns are used.
					);
		}
	}

	/**
	 * onStart() 用来做 onStop() 对应的工作
	 */
	@Override
	protected void onStart() {
		super.onStart(); // Always call the superclass method first

		// The activity is either being restarted or started for the first time
		// so this is where we should make sure that GPS is enabled
		LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		boolean gpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

		if (!gpsEnabled) {
			// Create a dialog here that requests the user to enable GPS, and
			// use an intent with the
			// android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS action
			// to take the user to the Settings screen to enable GPS when they
			// click "OK"
		}
	}

	/**
	 * onRestart() 基本上不需要用到的<br>
	 * http://developer.android.com/training/basics/activity-lifecycle/stopping. html
	 */
	@Override
	protected void onRestart() {
		super.onRestart(); // Always call the superclass method first
		// Activity being restarted from stopped state
	}

	/**
	 * onSaveInstanceState() 在activity被销毁时保存额外的状态(view相关的系统会保存)
	 */
	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
		// Save the user's current game state
		savedInstanceState.putInt(STATE_SCORE, mCurrentScore);
		savedInstanceState.putInt(STATE_LEVEL, mCurrentLevel);

		// Always call the superclass so it can save the view hierarchy state
		super.onSaveInstanceState(savedInstanceState);
	}

	/**
	 * 恢复保存的额外的状态
	 */
	public void onRestoreInstanceState(Bundle savedInstanceState) {
		// Always call the superclass so it can restore the view hierarchy
		super.onRestoreInstanceState(savedInstanceState);

		// 这里的Bundle不会为空
		// Restore state members from saved instance
		mCurrentScore = savedInstanceState.getInt(STATE_SCORE);
		mCurrentLevel = savedInstanceState.getInt(STATE_LEVEL);
	}

	/**
	 * onDestroy() 基本上不用实现, 除非有在OnCreate创建了线程或其它长期运行资源.<br>
	 * 大多数清理工作应该在onPause() 和 onStop()中进行
	 */
	@Override
	public void onDestroy() {
		super.onDestroy();
	}
}
