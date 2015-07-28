package com.zhch.andex.image;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import com.zhch.andex.R;
import com.zhch.andex.common.activity.BaseAct;

public class RountImageUseXmlDemo extends BaseAct {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.image_activity_rount_image_use_xml);
		ImageView imageView = (ImageView)findViewById(R.id.first_image);
		imageView.setScaleType(ScaleType.CENTER_INSIDE);
	}
}
