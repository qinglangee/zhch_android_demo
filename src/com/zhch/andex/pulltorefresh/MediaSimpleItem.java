package com.zhch.andex.pulltorefresh;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.zhch.andex.R;
import com.zhch.andex.util.net.VolleyWrapper;

public class MediaSimpleItem extends RelativeLayout implements View.OnClickListener {
	private TextView mName;
	private RoundedImageView mImage;

	public MediaSimpleItem(Context context) {
		this(context, null);
	}

	public MediaSimpleItem(Context context, AttributeSet attrs) {

		super(context, attrs);
	}

	public void initResource() {

		this.mName = null; // (TextView) findViewById(R.id.name);
		this.mImage = (RoundedImageView) findViewById(R.id.image);
	}

	public void initData(int position, MediaSimple user) {
		// mName.setText(user.getTitle());

		LayoutParams params = (RelativeLayout.LayoutParams) mImage.getLayoutParams();
		if (position % 2 == 1) {
			params.removeRule(RelativeLayout.ALIGN_PARENT_START);
			params.addRule(RelativeLayout.ALIGN_PARENT_END, RelativeLayout.TRUE);
		}else{
			params.removeRule(RelativeLayout.ALIGN_PARENT_END);
			params.addRule(RelativeLayout.ALIGN_PARENT_START, RelativeLayout.TRUE);
		}
		if (!TextUtils.isEmpty(user.getImage())) {

			mImage.setScaleType(ScaleType.CENTER_CROP);
			testImage = user.getImage();
			String imageUrl = user.getImage().replace("bigger", "common");
			VolleyWrapper.loadImage(this.getContext(), imageUrl, mImage);
		}
		this.setOnClickListener(this);
	}

	private String testImage;
	@Override
	public void onClick(View v) {
		Log.d("zhch", testImage);
	}

}