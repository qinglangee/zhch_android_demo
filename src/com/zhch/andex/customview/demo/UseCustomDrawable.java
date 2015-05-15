package com.zhch.andex.customview.demo;

import android.app.Activity;
import android.os.Bundle;

import com.zhch.andex.customview.CustomDrawableView;

public class UseCustomDrawable extends Activity{

	CustomDrawableView mCustomDrawableView;

	protected void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);
	  mCustomDrawableView = new CustomDrawableView(this);

	  setContentView(mCustomDrawableView);
	}
	
}
