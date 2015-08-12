package com.zhch.andex.activitydemo;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.zhch.andex.R;
import com.zhch.andex.common.activity.BaseAct;

/**
 * Created by zhch on 15-8-12.
 */
public class KilledActivityDemo extends BaseAct {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view = new RelativeLayout(this);
        setContentView(view);

        Fragment frag = new KilledActivityFrag();
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(view.getId(), frag);

        transaction.commit();
    }
}
