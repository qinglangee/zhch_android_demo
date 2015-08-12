package com.zhch.andex.appmenu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.zhch.andex.R;
import com.zhch.andex.activitydemo.ActionBarDemo;
import com.zhch.andex.syswidget.ActionBarTabDemo;
import com.zhch.andex.syswidget.SmallButtonsDemo;
import com.zhch.andex.syswidget.SpinnerDemo;
import com.zhch.andex.syswidget.TabHostDemo;
import com.zhch.andex.util.LL;

/**
 * Created by zhch on 15-7-28.
 */
public class WidgetMenuFrag extends TypeMenuFrag {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.app_menu_page, container, false);
        mButtonBox = (LinearLayout) view.findViewById(R.id.button_box);

        items = new ButtonItem[]{
                new ButtonItem(SpinnerDemo.class, "Spinner 示例")
                , new ButtonItem(TabHostDemo.class, "TabHost 示例")
                , new ButtonItem(ActionBarTabDemo.class, "ActionBarTab 示例")
                , new ButtonItem(ActionBarDemo.class, "ActionBar 示例")
                , new ButtonItem(SmallButtonsDemo.class, "小控件集合 示例")
        };
        addButtons();
        return view;
    }
}
