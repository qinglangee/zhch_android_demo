package com.zhch.andex.constant;
import static com.android.volley.Request.Method.GET;

public class BaiduImageApi extends Api{
	private static final String prefix = "http://image.baidu.com/";
//	private static final String prefix = "http://api.xy.pintimes.com/";
	public BaiduImageApi(String url, int method, boolean needLogin){
		super(prefix + url, method, needLogin);
	}
	
	/** 图片搜索
     * get from http://goodbai.com/api/ImageSearchAPI.html
     * 主要参数： word:查询关键词, rn:每页显示图片数量, pn:图片显示的页码
     * */
	public static Api IMAGE_SEARCH = new BaiduImageApi("i", GET, false);

}
