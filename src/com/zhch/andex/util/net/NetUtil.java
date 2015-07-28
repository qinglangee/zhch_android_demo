package com.zhch.andex.util.net;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import android.content.Context;
import android.util.Base64;
import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.zhch.andex.R;
import com.zhch.andex.app.MyApp;
import com.zhch.andex.user.UserService;
import com.zhch.andex.user.vo.User;
import com.zhch.andex.util.CipherUtil;
import com.zhch.andex.util.ToastUtil;

public class NetUtil {

	/**
	 * 基本的网络错误处理程序
	 * 
	 * @return
	 */
	public static Response.ErrorListener getErrorListener(final Context context) {
		return new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				if(error != null && error.getMessage() != null){
					Log.e("NetUtil", error.getMessage());
				}else{
					Log.e("NetUtil", "VolleyError is null or error.getMessage is null.");
				}
				int errRes = R.string.common_err_connection_broken;
				if(error.networkResponse != null){
					NetworkResponse resp = error.networkResponse;
					if(resp.statusCode == 401){
						errRes = R.string.common_err_connection_401;
					}else if(resp.statusCode == 500){
						errRes = R.string.common_err_connection_500;
					}
				}
				ToastUtil.show(context, context.getString(errRes));
			}
		};
	}

	/**
	 * 参数类型转换
	 * 
	 * @param params
	 * @return
	 */
	public static List<ParamPair<String>> convertParam(Map<String, String> params) {
		List<ParamPair<String>> result = new ArrayList<ParamPair<String>>();
		for (Entry<String, String> e : params.entrySet()) {
			ParamPair<String> pair = new ParamPair<String>(e.getKey(), e.getValue());
			result.add(pair);
		}
		return result;
	}

	/**
	 * 创建用户basic验证信息
	 * 
	 * @return
	 */
	public static String createUserAuth() {
		MyApp pin = MyApp.getInstance();
		User user = UserService.getLoginUser();
		if (user == null) {
			return null;
		}
		String version = ":1.0";
		String authName = null;
		String authPass = null;
		String authentication = null;
		// 已登录的编辑使用用户名密码验证
		if (user.isCommission()) {
			authName = CipherUtil.getInstance().aesEncode("PASS:" + user.user_login);
			authPass = CipherUtil.getInstance().aesEncode(user.user_phone_pass + version);
			
		} else { // 非编辑用户用deviceId 和 userId 验证
			authName = CipherUtil.getInstance().aesEncode("DEVICE:" + pin.getDeviceId());
			authPass = CipherUtil.getInstance().aesEncode(user.user_id + version);
		}
		
		String basecode = null;
		try {
			basecode = new String(Base64.encode((authName + ":" + authPass).getBytes(), Base64.NO_WRAP), "ASCII");
		} catch (UnsupportedEncodingException e) {
			Log.e(MyApp.getInstance().getPackageName(), e.getMessage());
		}
		authentication = "Basic " + basecode;

		return authentication;
	}
	
	/**
	 * 创建参数map Map<String, String>
	 * @param key
	 * @param value
	 * @return
	 */
	public static Map<String, String> createParams(String key, String value){
		Map<String, String> map = new HashMap<String, String>();
		map.put(key, value);
		return map;
	}
}
