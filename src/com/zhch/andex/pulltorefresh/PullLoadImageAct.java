package com.zhch.andex.pulltorefresh;

import java.lang.reflect.Type;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.reflect.TypeToken;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnLastItemVisibleListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.zhch.andex.R;
import com.zhch.andex.util.net.GsonRequest;
import com.zhch.andex.util.net.VolleyWrapper;
import com.zhch.andex.widget.PullListView;

public class PullLoadImageAct  extends FragmentActivity implements OnRefreshListener<ListView>, OnLastItemVisibleListener{

	PullListView mList;
	MediaSimpleAdapter mAdapter;
	int count = 0;
	private Long startId;

	static Type listType = new TypeToken<List<MediaSimple>>() {
	}.getType();

	/**
	 * Called when the activity is first created.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.content_layout_list);
		// View view = inflater.inflate(R.layout.frag_list, null);
		mList = (PullListView) findViewById(R.id.title_list);
		mList.setOnRefreshListener(this);
		mList.setOnLastItemVisibleListener(this);
//		mList.setDividerDrawable(null);
//		mList.setDividerHeight(0);
		mAdapter = new MediaSimpleAdapter(PullLoadImageAct.this);
		mList.setAdapter(mAdapter);

		loadData(false);
	}
	
	private void loadData(boolean isAppend){
		String url = "http://dbapi.l99.com/dovebox/pintimes/content_simple";
		if(isAppend && startId != null && startId > 0){
			url += "?start_id=" + startId;
		}
		GsonRequest<MediaSimpleResp> request = new GsonRequest<MediaSimpleResp>(url, MediaSimpleResp.class, null, getResponseListener(isAppend),
				getErrorListener());
		VolleyWrapper.getInstance(this).addToRequestQueue(request);
		
	}

	private Response.Listener<MediaSimpleResp> getResponseListener(final boolean isAppend) {
		return new Response.Listener<MediaSimpleResp>() {
			@Override
			public void onResponse(MediaSimpleResp res) {
				if(res != null && res.data != null){
					startId = res.data.startId;
					List<MediaSimple> list = res.data == null ? null : res.data.pindashboards;
					if(isAppend){
						mAdapter.addAll(list);
					}else{
						mAdapter.updateData(list);
					}
				}
				mList.onRefreshComplete();
			}
		};
	}

	private Response.ErrorListener getErrorListener() {
		return new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
//				 mTextView.setText("That didn't work!");
			}
		};
	}

	@Override
	public void onRefresh(PullToRefreshBase<ListView> refreshView) {
		Log.d("zhch", "onRefresh is :" + count ++);
		loadData(false);
	}

	@Override
	public void onLastItemVisible() {
		Log.d("zhch", "onLastItemVisible is :" + count ++);
		loadData(true);
	}

}