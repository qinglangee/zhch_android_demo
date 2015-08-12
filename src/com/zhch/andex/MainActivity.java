package com.zhch.andex;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;

import com.zhch.andex.R;
import com.zhch.andex.activitydemo.ActionBarDemo;
import com.zhch.andex.activitydemo.LifeCycleDemo;
import com.zhch.andex.animation.BasicPropertyAnimation;
import com.zhch.andex.appmenu.AppMenuFrag;
import com.zhch.andex.common.activity.BaseAct;
import com.zhch.andex.common.fragment.BaseFrag;
import com.zhch.andex.customview.demo.UseCircleProgressBar;
import com.zhch.andex.customview.demo.UseCustomDrawable;
import com.zhch.andex.customview.demo.UseCustomDrawableFromXml;
import com.zhch.andex.database.sqlite.SqliteDemo;
import com.zhch.andex.dialog.DialogDemo;
import com.zhch.andex.drawer.DrawerDemo;
import com.zhch.andex.http.demo.UseAsynHttpClient;
import com.zhch.andex.image.FrescoSimpleDemo;
import com.zhch.andex.image.RountImageUseXmlDemo;
import com.zhch.andex.media.TakePhotoDemo;
import com.zhch.andex.shape.TwoRoundCorner;
import com.zhch.andex.system.SystemDataDemo;
import com.zhch.andex.syswidget.SpinnerDemo;
import com.zhch.andex.util.PreferencesUtils;
import com.zhch.andex.widget.demo.ProTextViewDemo;

public class MainActivity extends BaseAct {
    private final String TAG = getClass().getSimpleName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);

        if(savedInstanceState != null){
            return;
        }

        BaseFrag frag = new AppMenuFrag();
        getFragmentManager().beginTransaction().add(R.id.fragment_container, frag).commit();


        String className = PreferencesUtils.getString(this, CURRENT_ACTIVITY);
        if(className != null && !className.equals(getClass().getName())){
            Log.i(TAG, "first Activity is : " + className);
            try {
                Class cla = Class.forName(className);
                startActivity(new Intent(this, cla));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
	}
}
