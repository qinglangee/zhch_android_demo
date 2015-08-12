package com.zhch.andex.syswidget;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.MotionEvent;

import com.zhch.andex.R;
import com.zhch.andex.common.activity.BaseAct;
import com.zhch.andex.common.fragment.BaseFrag;

/**
 * Created by zhch on 15-7-30.
 */
public class ActionBarTabDemo extends BaseAct {

    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.syswidget_actionbar_tab_demo);


        actionBar = getActionBar();
        if (actionBar != null) {
            addTabs();
        }
    }

    private void addTabs() {
        ActionBar.Tab tab = actionBar.newTab().setText("Tab1");
        tab.setTabListener(new MyTabListener(new ActionBarTabFrag01()));
        actionBar.addTab(tab);
        ActionBar.Tab tab2 = actionBar.newTab().setText("Tab2");
        tab2.setTabListener(new MyTabListener(new ActionBarTabFrag02()));
        actionBar.addTab(tab2);

        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        // remove the activity title to make space for tabs
        actionBar.setDisplayShowTitleEnabled(false);
    }

    private class MyTabListener implements ActionBar.TabListener {
        BaseFrag frag;
        public MyTabListener(BaseFrag frag){
            this.frag = frag;
        }
        @Override
        public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
            showToast("选了: " + tab.getText());
            ft.add(R.id.frame, frag);
        }

        @Override
        public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
            ft.remove(frag);
        }

        @Override
        public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

        }
    }

    //点击显示or隐藏ActionBar
    public boolean onTouchEvent(MotionEvent event) {
        ActionBar bar = getActionBar();
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                if (bar.isShowing()) bar.hide();
                else bar.show();
                break;
            default:
                break;
        }
        return true;
    }
}
