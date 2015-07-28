package com.zhch.andex.pulltorefresh;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhch.andex.R;
import com.zhch.andex.adapter.CustomAdapter;

public class MediaSimpleAdapter extends CustomAdapter<MediaSimple> {

	public MediaSimpleAdapter(Context context) {
		super(context);
	}

	@Override
	public View newView(Context context, MediaSimple data, ViewGroup parent, int type) {
		MediaSimpleItem item = (MediaSimpleItem) LayoutInflater.from(context).inflate(R.layout.content_item_title,
				null);
		item.initResource();
		return item;
	}

	@Override
	public void bindView(View view, int position, MediaSimple data) {
		if (data == null)
			return;
		((MediaSimpleItem) view).initData(position, data);
	}
}