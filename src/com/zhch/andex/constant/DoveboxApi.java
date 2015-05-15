package com.zhch.andex.constant;

import com.android.volley.Request.Method;

public class DoveboxApi extends Api{
	private static final String prefix = "http://dbapi.l99.com/dovebox/";
	public DoveboxApi(String url, int method, boolean needLogin){
		super(prefix + url, method, needLogin);
	}
	
	public static Api PINTIMES_CONTENT_SIMPLE = new DoveboxApi("pintimes/content_simple", Method.GET, false);
}
