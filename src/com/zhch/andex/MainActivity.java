package com.zhch.andex;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;

import com.zhch.andex.activitydemo.ActionBarDemo;
import com.zhch.andex.activitydemo.LifeCycleDemo;
import com.zhch.andex.customview.demo.UseCustomDrawable;
import com.zhch.andex.customview.demo.UseCustomDrawableFromXml;
import com.zhch.andex.drawer.DrawerDemo;
import com.zhch.andex.image.RountImageUseXml;
import com.zhch.andex.shape.TwoRoundCorner;
import com.zhch.andex.widget.demo.ProTextViewDemo;

public class MainActivity extends Activity {

	LinearLayout mButtonBox;
	ButtonItem[] items;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mButtonBox = (LinearLayout) findViewById(R.id.button_box);
		items = new ButtonItem[]{
				new ButtonItem(LifeCycleDemo.class, "生命周期")
				,new ButtonItem(ActionBarDemo.class)
				,new ButtonItem(DrawerDemo.class)
				,new ButtonItem(RountImageUseXml.class)
				,new ButtonItem(TwoRoundCorner.class)
				,new ButtonItem(UseCustomDrawable.class)
				,new ButtonItem(UseCustomDrawableFromXml.class)
				,new ButtonItem(ProTextViewDemo.class)
		};
		addButtons();
		
		Intent intent = new Intent(this, ProTextViewDemo.class);
		startActivity(intent);
	}


	private void addButtons() {
		for(final ButtonItem item : items){
			Button btn = new Button(this);
			btn.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
			btn.setText(item.name);
			btn.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(MainActivity.this, item.cla);
					startActivity(intent);
				}
			});
			mButtonBox.addView(btn);
		}
	}
	
	public class ButtonItem{
		public Class cla;
		public String name;
		public ButtonItem(Class cla){
			this(cla, cla.getSimpleName());
		}
		public ButtonItem(Class cla, String name){
			this.cla = cla;
			this.name = name;
		}
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

}
