package com.zhch.andex.syswidget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhch.andex.R;
import com.zhch.andex.common.fragment.BaseFrag;

/**
 * Created by zhch on 15-7-30.
 */
public class ActionBarTabFrag01 extends BaseFrag {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LinearLayout view = new LinearLayout(getContext());

        view.setBackgroundColor(getResources().getColor(R.color.green));
        TextView text = new TextView(getContext());
        text.setText("ActionBar Tab Frag 01");
        view.addView(text);
        return view;
    }
}
