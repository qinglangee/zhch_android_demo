package com.zhch.andex.syswidget;

import android.app.TabActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.TabHost;

import com.zhch.andex.R;

/**
 * Created by zhch on 15-7-30.
 */
public class TabHostDemo extends TabActivity {
    private TabHost tab = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        tab = this.getTabHost();
        LayoutInflater.from(this).inflate(R.layout.syswidget_tab_host_demo, tab.getTabContentView(), true);
        tab.addTab(tab.newTabSpec("选项卡一").setIndicator(null,
                getResources().getDrawable(R.drawable.invite_num_1)).setContent(R.id.tab01));
        tab.addTab(tab.newTabSpec("选项卡二").setIndicator("选项卡二",
                null).setContent(R.id.tab02));
        tab.addTab(tab.newTabSpec("选项卡三").setIndicator("选项卡三",
                getResources().getDrawable(R.drawable.invite_num_3)).setContent(R.id.tab03));
    }
}
