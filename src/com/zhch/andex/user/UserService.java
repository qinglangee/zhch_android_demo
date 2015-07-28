package com.zhch.andex.user;

import android.util.Log;

import com.zhch.andex.app.MyApp;
import com.zhch.andex.user.vo.User;
import com.zhch.andex.util.GsonUtils;
import com.zhch.andex.util.PreferencesUtils;

public class UserService {

	public static final String LOGIN_USER = "login_user";

	/**
	 * 取出登录用户数据
	 * @return
	 */
	public static User getLoginUser() {
		String userStr = PreferencesUtils.getString(MyApp.getInstance(), LOGIN_USER);
		if (userStr == null) {
			return null;
		}
		User user = null;
		try {
			user = GsonUtils.fromJson(userStr, User.class);
		} catch (Exception e) {
			Log.e("UserService", e.getMessage());
		}
		return user;
	}

	/**
	 * 保存登录用户数据
	 * @param user
	 */
	public static void setLoginUser(User user) {
		String userStr = null;
		if (user != null) {
			userStr = GsonUtils.toJson(user);
		}
		PreferencesUtils.putString(MyApp.getInstance(), LOGIN_USER, userStr);
	}
}
