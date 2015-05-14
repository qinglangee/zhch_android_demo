package com.zhch.andex;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.zhch.andex.activitydemo.ActionBarDemo;
import com.zhch.andex.activitydemo.LifeCycleDemo;
import com.zhch.andex.drawer.DrawerDemo;
import com.zhch.andex.image.RountImageUseXml;
import com.zhch.andex.shape.TwoRoundCorner;

public class MainActivity extends Activity {

	Button button1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		button1 = (Button) findViewById(R.id.button1);
		
		
		Intent intent = new Intent(this, TwoRoundCorner.class);
		startActivity(intent);
	}

	/**
	 * xml中 android:onClick="toLifeCycleDemo" 指定监听方法<br>
	 * 方法要求: 1. public 2. void 3. 有且仅有一个参数, 类型为 View
	 * 
	 * @param view
	 */
	public void toLifeCycleDemo(View view) {
		Intent intent = new Intent(this, LifeCycleDemo.class);
		startActivity(intent);
	}
	public void toActionBarDemo(View view) {
		Intent intent = new Intent(this, ActionBarDemo.class);
		startActivity(intent);
	}
	public void toDrawerBoxDemo(View view) {
		Intent intent = new Intent(this, DrawerDemo.class);
		startActivity(intent);
	}
	public void toRountImageUseXml(View view) {
		Intent intent = new Intent(this, RountImageUseXml.class);
		startActivity(intent);
	}
	public void toTwoRoundCorner(View view) {
		Intent intent = new Intent(this, TwoRoundCorner.class);
		startActivity(intent);
	}

}
