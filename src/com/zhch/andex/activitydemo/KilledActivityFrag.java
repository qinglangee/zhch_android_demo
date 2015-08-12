package com.zhch.andex.activitydemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhch.andex.R;
import com.zhch.andex.common.fragment.BaseFrag;

/**
 * Created by zhch on 15-8-12.
 */
public class KilledActivityFrag extends BaseFrag {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        TextView text = new TextView(getContext());
        String str = getResources().getString(R.string.app_name);

        text.setText("哈哈哈哈哈哈哈哈, 我是 fragment." + str);

        return text;
    }
}
