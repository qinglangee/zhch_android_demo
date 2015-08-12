package com.zhch.andex.activitydemo;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.imagepipeline.animated.base.AnimatedImageResult;
import com.zhch.andex.R;
import com.zhch.andex.common.activity.BaseAct;

public class ActionBarDemo extends BaseAct {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_layout_action_bar_demo);
		ActionBar actionBar = getActionBar();
		actionBar.setTitle("Action 标题");
        actionBar.setDisplayHomeAsUpEnabled(true);


        LinearLayout linear = (LinearLayout)findViewById(R.id.linear);
        for (int i=0;i<10;i++){
            TextView t = new TextView(this);
            t.setText("Content " + i);
            linear.addView(t);
        }
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    // Inflate the menu items for use in the action bar
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.activity_action_bar_demo, menu);
	    return super.onCreateOptionsMenu(menu);
	}

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_search01:
                showToast("menu s1");
                return true;
            case R.id.action_search02:
                showToast("menu s2");
                return true;
            case R.id.action_search03:
                showToast("menu s3");
                return true;
            case R.id.action_compose:
                showToast("menu s4");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    //点击显示or隐藏ActionBar
    public boolean onTouchEvent(MotionEvent event) {
        ActionBar bar = getActionBar();
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                if (bar.isShowing()) bar.hide();
                else bar.show();
                break;
            default:
                break;
        }
        return true;
    }
}
