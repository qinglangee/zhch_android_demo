package com.zhch.andex.appmenu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.zhch.andex.R;
import com.zhch.andex.activitydemo.ActionBarDemo;
import com.zhch.andex.activitydemo.KilledActivityDemo;
import com.zhch.andex.activitydemo.LifeCycleDemo;
import com.zhch.andex.animation.BasicPropertyAnimation;
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
import com.zhch.andex.util.LL;
import com.zhch.andex.widget.demo.ProTextViewDemo;

/**
 * Created by zhch on 15-7-28.
 */
public class CustomViewMenuFrag extends TypeMenuFrag {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.app_menu_page, container, false);
        mButtonBox = (LinearLayout) view.findViewById(R.id.button_box);

        items = new ButtonItem[]{
                new ButtonItem(LifeCycleDemo.class, "生命周期")
                ,new ButtonItem(KilledActivityDemo.class, "Activity 被回收")
                ,new ButtonItem(DrawerDemo.class, "抽屉菜单")
                ,new ButtonItem(RountImageUseXmlDemo.class, "用xml画圆角图片")
                ,new ButtonItem(TwoRoundCorner.class, "两个圆角")
                ,new ButtonItem(UseCustomDrawable.class, "自定义Drawable")
                ,new ButtonItem(UseCustomDrawableFromXml.class)
                ,new ButtonItem(ProTextViewDemo.class, "加强型Text")
                ,new ButtonItem(UseCircleProgressBar.class, "圆形进度条")
                ,new ButtonItem(UseAsynHttpClient.class, "AsynHttp 示例")
                ,new ButtonItem(TakePhotoDemo.class, "照片／拍照")
                ,new ButtonItem(BasicPropertyAnimation.class, "属性动画")
                ,new ButtonItem(DialogDemo.class, "对话框")
                ,new ButtonItem(SqliteDemo.class, "原生数据库操作")
                ,new ButtonItem(SystemDataDemo.class, "系统数据")
                ,new ButtonItem(FrescoSimpleDemo.class, "Fresco图片加载")
        };
        LL.d("3 items is null:" + (items == null));
        addButtons();
        return view;
    }
}
