package com.zhch.andex.activitydemo;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

import com.zhch.andex.R;
import com.zhch.andex.common.activity.BaseAct;

public class ActionBarDemo extends BaseAct {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_layout_action_bar_demo);
		ActionBar actionBar = getActionBar();
		actionBar.setTitle(R.string.act_action_bar_title);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    // Inflate the menu items for use in the action bar
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.activity_action_bar_demo, menu);
	    return super.onCreateOptionsMenu(menu);
	}	
}
