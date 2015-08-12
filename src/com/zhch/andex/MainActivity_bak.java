package com.zhch.andex;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;

import com.zhch.andex.activitydemo.ActionBarDemo;
import com.zhch.andex.activitydemo.LifeCycleDemo;
import com.zhch.andex.animation.BasicPropertyAnimation;
import com.zhch.andex.common.activity.BaseAct;
import com.zhch.andex.customview.demo.UseCircleProgressBar;
import com.zhch.andex.customview.demo.UseCustomDrawable;
import com.zhch.andex.customview.demo.UseCustomDrawableFromXml;
import com.zhch.andex.database.sqlite.SqliteDemo;
import com.zhch.andex.dialog.DialogDemo;
import com.zhch.andex.drawer.DrawerDemo;
import com.zhch.andex.http.demo.UseAsynHttpClient;
import com.zhch.andex.image.FrescoSimpleDemo;
import com.zhch.andex.image.RountImageUseXmlDemo;
import com.zhch.andex.media.TakePhotoDemo;
import com.zhch.andex.shape.TwoRoundCorner;
import com.zhch.andex.system.SystemDataDemo;
import com.zhch.andex.syswidget.SpinnerDemo;
import com.zhch.andex.widget.demo.ProTextViewDemo;

public class MainActivity_bak extends BaseAct {

	LinearLayout mButtonBox;
	ButtonItem[] items;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.app_menu_page);
		mButtonBox = (LinearLayout) findViewById(R.id.button_box);
		items = new ButtonItem[]{
				new ButtonItem(LifeCycleDemo.class, "生命周期")
				,new ButtonItem(ActionBarDemo.class)
				,new ButtonItem(DrawerDemo.class, "抽屉菜单")
				,new ButtonItem(RountImageUseXmlDemo.class, "用xml画圆角图片")
				,new ButtonItem(TwoRoundCorner.class, "两个圆角")
				,new ButtonItem(UseCustomDrawable.class, "自定义Drawable")
				,new ButtonItem(UseCustomDrawableFromXml.class)
				,new ButtonItem(ProTextViewDemo.class, "加强型Text")
				,new ButtonItem(UseCircleProgressBar.class, "圆形进度条")
				,new ButtonItem(UseAsynHttpClient.class, "AsynHttp 示例")
				,new ButtonItem(TakePhotoDemo.class, "照片／拍照")
				,new ButtonItem(BasicPropertyAnimation.class, "属性动画")
				,new ButtonItem(DialogDemo.class, "对话框")
				,new ButtonItem(SqliteDemo.class, "原生数据库操作")
				,new ButtonItem(SystemDataDemo.class, "系统数据")
				,new ButtonItem(FrescoSimpleDemo.class, "Fresco图片加载")
				,new ButtonItem(SpinnerDemo.class, "Spinner 示例")
		};
		addButtons();
		
		Intent intent = new Intent(this, SpinnerDemo.class);
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
					Intent intent = new Intent(MainActivity_bak.this, item.cla);
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
