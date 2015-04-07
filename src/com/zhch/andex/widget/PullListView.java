package com.zhch.andex.widget;

import android.content.Context;
import android.util.AttributeSet;

import com.handmark.pulltorefresh.library.PullToRefreshListView;

public class PullListView  extends PullToRefreshListView  {

	public PullListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public PullListView(Context context, Mode mode, AnimationStyle style) {
		super(context, mode, style);
	}

	public PullListView(Context context, Mode mode) {
		super(context, mode);
	}

	public PullListView(Context context) { 
		super(context);
	}

}
